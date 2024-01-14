package com.potato.docker.demo1.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.potato.docker.demo1.domain.po.Employees;
import com.potato.docker.demo1.domain.query.EmployeeQueryParam;
import com.potato.docker.demo1.service.IEmployeesService;
import com.potato.docker.demo1.validate.CustomerValidGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lr
 * @since 2023-12-14
 */
@Api(tags = "员工-接口")
@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeesController {

    @Autowired
    private IEmployeesService employeesService;

    @ApiOperation("获取所有员工信息")
    @GetMapping("list")
    public ResponseEntity<Object> list(WebRequest request) {
        System.out.println(request.getDescription(true));
        List<Employees> list = employeesService.listAllEmployees();
        HashMap<String, List<Employees>> stringListHashMap = new HashMap<>();
        stringListHashMap.put("data", list);
        return new ResponseEntity<>(stringListHashMap, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "employeeQueryParam", value = "员工查询参数", required = true, paramType = "body", dataType = "EmployeeQueryParam对象")
    })
    @ApiOperation("获取所有符合条件的员工信息")
    @PostMapping("list")
    public Page<Employees> listByConditions(@RequestBody EmployeeQueryParam employeeQueryParam) {
        return employeesService.listByConditions(employeeQueryParam);
    }

    @PostMapping("save")
    public String saveEmployee(@RequestBody @Validated(CustomerValidGroup.Crud.Insert.class) Employees employees) {
        for (int i = 0; i < 100; i++) {
            log.error("error信息!!");
            log.warn("warn信息!!");
            log.info("info信息!!");
            log.debug("debug信息!!");
            log.trace("trace信息!!");
        }
        System.out.println(log.getClass().getName());
        System.out.println(log.getName());
//        logger.info("入参{}",  employees);
//        boolean save = employeesService.save(employees);
//        if (save) {
//            return "success";
//        }
        return "fail";
    }
}
