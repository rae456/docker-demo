package com.potato.docker.demo1.controller;


import com.potato.docker.demo1.common.ResponseVo;
import com.potato.docker.demo1.domain.po.SysUser;
import com.potato.docker.demo1.service.ISysUserService;
import com.potato.docker.demo1.validate.CustomerValidGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lr
 * @since 2024-01-04
 */
@RestController
@RequestMapping("/sys-user")
@Slf4j
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("save")
    public ResponseVo saveUser(@Validated(value = {CustomerValidGroup.Crud.Insert.class}) @RequestBody SysUser sysUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(fieldError -> Objects.nonNull(fieldError.getField()) ? fieldError.getField() : "",
                            fieldError -> fieldError.getDefaultMessage(), (oldValue, newValue) -> oldValue));
            return ResponseVo.fail("参数错误").setData(errorMap);
        }
        boolean save = sysUserService.encodeAndSave(sysUser);
        if (!save) {
            return ResponseVo.fail("创建账户失败").setData(bindingResult);
        }
        return ResponseVo.ok().setMessage("创建账户成功");
    }

    @GetMapping("list")
    public ResponseVo<List<SysUser>> list() {
        return ResponseVo.ok(sysUserService.list());
    }
}
