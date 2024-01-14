package com.potato.docker.demo1.service.impl;

import com.potato.docker.demo1.domain.po.Jobs;
import com.potato.docker.demo1.mapper.JobsMapper;
import com.potato.docker.demo1.service.IJobsService;
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
public class JobsServiceImpl extends ServiceImpl<JobsMapper, Jobs> implements IJobsService {

}
