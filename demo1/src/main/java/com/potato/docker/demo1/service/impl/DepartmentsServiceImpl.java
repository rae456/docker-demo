package com.potato.docker.demo1.service.impl;

import com.potato.docker.demo1.domain.po.Departments;
import com.potato.docker.demo1.mapper.DepartmentsMapper;
import com.potato.docker.demo1.service.IDepartmentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lr
 * @since 2023-12-14
 */
@Service
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments> implements IDepartmentsService {

}
