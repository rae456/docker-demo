package com.potato.docker.demo1.service.impl;

import com.potato.docker.demo1.domain.po.EmpDetailsView;
import com.potato.docker.demo1.mapper.EmpDetailsViewMapper;
import com.potato.docker.demo1.service.IEmpDetailsViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author lr
 * @since 2023-12-14
 */
@Service
public class EmpDetailsViewServiceImpl extends ServiceImpl<EmpDetailsViewMapper, EmpDetailsView> implements IEmpDetailsViewService {

}
