package com.potato.docker.demo1.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.potato.docker.demo1.domain.po.Employees;
import com.baomidou.mybatisplus.extension.service.IService;
import com.potato.docker.demo1.domain.query.EmployeeQueryParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lr
 * @since 2023-12-14
 */
public interface IEmployeesService extends IService<Employees> {

    Page<Employees> listByConditions(EmployeeQueryParam employeeQueryParam);

    List<Employees> listAllEmployees();
}
