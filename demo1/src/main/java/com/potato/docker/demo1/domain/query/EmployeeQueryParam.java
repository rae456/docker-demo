package com.potato.docker.demo1.domain.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "EmployeeQueryParam对象", description = "员工查询参数实体的描述")
public class EmployeeQueryParam extends QueryBase {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "员工ID", name = "员工的ID")
    private Integer employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDateStart;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDateEnd;

    private String jobId;

    private Double salary;

    private Double commissionPct;

    private Integer managerId;

    private Integer departmentId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTimeEnd;
}
