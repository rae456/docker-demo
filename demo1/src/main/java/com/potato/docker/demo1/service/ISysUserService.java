package com.potato.docker.demo1.service;

import com.potato.docker.demo1.domain.po.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lr
 * @since 2024-01-04
 */
public interface ISysUserService extends IService<SysUser> {

    boolean encodeAndSave(SysUser sysUser);
}
