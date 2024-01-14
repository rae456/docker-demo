package com.potato.docker.demo1.service.impl;

import com.potato.docker.demo1.domain.po.JobHistory;
import com.potato.docker.demo1.mapper.JobHistoryMapper;
import com.potato.docker.demo1.service.IJobHistoryService;
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
public class JobHistoryServiceImpl extends ServiceImpl<JobHistoryMapper, JobHistory> implements IJobHistoryService {

}
