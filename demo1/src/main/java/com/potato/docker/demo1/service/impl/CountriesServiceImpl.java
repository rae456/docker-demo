package com.potato.docker.demo1.service.impl;

import com.potato.docker.demo1.domain.po.Countries;
import com.potato.docker.demo1.mapper.CountriesMapper;
import com.potato.docker.demo1.service.ICountriesService;
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
public class CountriesServiceImpl extends ServiceImpl<CountriesMapper, Countries> implements ICountriesService {

}
