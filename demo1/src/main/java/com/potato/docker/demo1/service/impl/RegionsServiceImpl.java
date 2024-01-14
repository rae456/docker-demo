package com.potato.docker.demo1.service.impl;

import com.potato.docker.demo1.domain.po.Regions;
import com.potato.docker.demo1.mapper.RegionsMapper;
import com.potato.docker.demo1.service.IRegionsService;
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
public class RegionsServiceImpl extends ServiceImpl<RegionsMapper, Regions> implements IRegionsService {

}
