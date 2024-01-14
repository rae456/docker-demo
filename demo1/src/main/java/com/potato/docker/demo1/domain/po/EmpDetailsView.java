package com.potato.docker.demo1.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author lr
 * @since 2023-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("emp_details_view")
@ApiModel(value="EmpDetailsView对象", description="VIEW")
public class EmpDetailsView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer employeeId;

    private String jobId;

    private Integer managerId;

    private Integer departmentId;

    private Integer locationId;

    private String countryId;

    private String firstName;

    private String lastName;

    private Double salary;

    private Double commissionPct;

    private String departmentName;

    private String jobTitle;

    private String city;

    private String stateProvince;

    private String countryName;

    private String regionName;


}
