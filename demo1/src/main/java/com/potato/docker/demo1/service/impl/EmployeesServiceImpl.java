package com.potato.docker.demo1.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.potato.docker.demo1.constants.RedisConstants;
import com.potato.docker.demo1.domain.po.Employees;
import com.potato.docker.demo1.domain.query.EmployeeQueryParam;
import com.potato.docker.demo1.mapper.EmployeesMapper;
import com.potato.docker.demo1.service.IEmployeesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lr
 * @since 2023-12-14
 */
@Service
@Slf4j
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees> implements IEmployeesService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Page<Employees> listByConditions(EmployeeQueryParam queryParam) {
        Long currentPage = queryParam.getPageIndex() == null ? 1 : queryParam.getPageIndex();
        Long pageSize = queryParam.getPageSize() == null ? 5 : queryParam.getPageSize();
        LocalDate hireDateStart = queryParam.getHireDateStart() == null ? LocalDate.of(1900, 1, 1) : queryParam.getHireDateStart();
        LocalDate hireDateEnd = queryParam.getHireDateEnd() == null ? LocalDate.of(2099, 1, 1) : queryParam.getHireDateEnd();
        LocalDateTime createTimeStart = queryParam.getCreateTimeStart() == null ?
                LocalDateTime.of(1900, 1, 1, 0, 0, 0) : queryParam.getCreateTimeStart();
        LocalDateTime createTimeEnd = queryParam.getCreateTimeEnd() == null ?
                LocalDateTime.of(2099, 1, 1, 0, 0, 0) : queryParam.getCreateTimeEnd();
        LambdaQueryWrapper<Employees> eq = new LambdaQueryWrapper<Employees>()
                .eq(Objects.nonNull(queryParam.getEmployeeId()), Employees::getEmployeeId, queryParam.getEmployeeId())
                .eq(Objects.nonNull(queryParam.getManagerId()), Employees::getManagerId, queryParam.getManagerId())
                .like(Objects.nonNull(queryParam.getFirstName()), Employees::getFirstName, queryParam.getFirstName())
                .between(Employees::getHireDate, hireDateStart, hireDateEnd);
        Page<Employees> page = new Page<>(currentPage, pageSize);
        page.addOrder(new OrderItem("employee_id", false));
        return baseMapper.selectPage(page, eq);
    }

    public List<Employees> listAllEmployees() {
        String employeesData = redisTemplate.opsForValue().get(RedisConstants.REDIS_PREFIX + "employees");
        if (StringUtils.hasText(employeesData)) {
            log.info("来自Redis数据");
            return JSON.parseArray(employeesData, Employees.class);
        } else {
            //return getAllEmployeesFromData();
            return getAllEmployeesFromDataViaRedisLock();
        }
    }

    private List<Employees> getAllEmployeesFromData() {
        synchronized (this) {
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            String data = ops.get(RedisConstants.REDIS_PREFIX + "employees");
            if (StringUtils.hasText(data)) {
                log.info("DCL...已有其他线程将数据存入Redis");
                return JSON.parseArray(data, Employees.class);
            } else {
                List<Employees> employees = list();
                log.info("来自数据库数据");
                ops.set(RedisConstants.REDIS_PREFIX + "employees", JSON.toJSONString(employees));
                return employees;
            }
        }
    }

    public List<Employees> getAllEmployeesFromDataViaRedisLock() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        while (true) {
            String uuid = UUID.randomUUID().toString();
            Boolean lockFlag = ops
                .setIfAbsent(RedisConstants.REDIS_PREFIX + "employees:lock", uuid, 30, TimeUnit.SECONDS);
            log.info("{}获取redis锁成功", Thread.currentThread().getName());
            if (lockFlag) {
                String data = ops.get(RedisConstants.REDIS_PREFIX + "employees");
                if (StringUtils.hasText(data)) {
                    log.info("其他线程已经存入数据，返回redis数据");
                    return JSON.parseArray(data, Employees.class);
                } else {
                    List<Employees> list = list();
                    ops.set(RedisConstants.REDIS_PREFIX + "employees", JSON.toJSONString(list));
                    String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
                    redisTemplate.execute(new DefaultRedisScript<>(script, Long.class),
                            Arrays.asList(RedisConstants.REDIS_PREFIX + "employees:lock"), uuid);
                    log.info("从数据库返回数据");
                    return list;
                }
            }
            try {
                log.info("自旋获取锁...");
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
