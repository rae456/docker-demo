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
 * 
 * </p>
 *
 * @author lr
 * @since 2023-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("countries")
@ApiModel(value="Countries对象", description="")
public class Countries implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "country_id", type = IdType.AUTO)
    private String countryId;

    private String countryName;

    private Integer regionId;


}
