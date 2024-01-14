package com.potato.docker.demo1.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.potato.docker.demo1.validate.CustomerValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * <p>
 *
 * </p>
 *
 * @author lr
 * @since 2024-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录账户id")
    @TableId(value = "id", type = IdType.AUTO)
    @Null(groups = {CustomerValidGroup.Crud.Insert.class}, message = "id必须为空")
    private Integer id;

    @ApiModelProperty(value = "登录账户用户名")
    @Length(groups = {CustomerValidGroup.Crud.Insert.class}, min = 2, max = 20, message = "用户名长度为2-20位")
    private String username;

    @ApiModelProperty(value = "登录账户密码")
    //@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-z\\d]{8,20}$", groups = {CustomerValidGroup.Crud.Insert.class}, message = "密码必须为8-20位数字或字母")
    @Pattern(regexp = "^\\w{6,18}$", message = "密码必须为6-18位数字、字母或_")
    private String password;

    @ApiModelProperty(value = "登录账户手机号")
    @Pattern(regexp = "(1[38]\\d|14[014-9]|1[59][0-35-9]|16[2567]|17[0-8])\\d{8}", groups = {CustomerValidGroup.Crud.Insert.class}, message = "手机号必须为11位数字")
    private String phone;

    @ApiModelProperty(value = "账户创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "账号上次登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "账号角色id")
    @Positive(groups = {CustomerValidGroup.Crud.Insert.class}, message = "角色id必须大于0")
    private Integer roleId;

}
