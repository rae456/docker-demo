package com.potato.docker.demo1.service.impl;

import com.potato.docker.demo1.domain.po.SysUser;
import com.potato.docker.demo1.mapper.SysUserMapper;
import com.potato.docker.demo1.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lr
 * @since 2024-01-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean encodeAndSave(SysUser sysUser) {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        return save(sysUser);
    }
}
