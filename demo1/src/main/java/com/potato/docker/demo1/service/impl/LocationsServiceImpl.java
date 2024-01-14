package com.potato.docker.demo1.service.impl;

import com.potato.docker.demo1.domain.po.Locations;
import com.potato.docker.demo1.mapper.LocationsMapper;
import com.potato.docker.demo1.service.ILocationsService;
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
public class LocationsServiceImpl extends ServiceImpl<LocationsMapper, Locations> implements ILocationsService {

}
