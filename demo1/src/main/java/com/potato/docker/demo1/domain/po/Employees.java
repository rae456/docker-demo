package com.potato.docker.demo1.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.potato.docker.demo1.validate.CustomerValidGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * <p>
 *
 * </p>
 *
 * @author lr
 * @since 2023-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("employees")
@ApiModel(value = "Employees对象", description = "员工对象的描述")
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "employee_id", type = IdType.INPUT)
    @Null(message = "id必须为空", groups = CustomerValidGroup.Crud.Update.class)
    @NotNull(message = "id不能为空", groups = CustomerValidGroup.Crud.Insert.class)
    @ApiModelProperty(value = "主键id", name = "员工ID号")
    private Integer employeeId;

    @NotBlank(groups = CustomerValidGroup.Crud.Insert.class, message = "名不能为空")
    @Null(groups = CustomerValidGroup.Crud.Update.class, message = "名不可以修改")
    private String firstName;

    @NotBlank(message = "姓不能为空")
    @ApiModelProperty(value = "姓", name = "员工姓氏")
    private String lastName;

    @Email(message = "邮件格式不正确")
    private String email;

    @Length(message = "电话号码应为11位有效数字", min = 11, max = 11)
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    private String jobId;

    private Double salary;

    private Double commissionPct;

    private Integer managerId;

    private Integer departmentId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
