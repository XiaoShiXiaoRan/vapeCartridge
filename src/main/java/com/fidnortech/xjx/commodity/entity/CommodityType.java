package com.fidnortech.xjx.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @author xjx
 * @since 2023-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_commodity_type")
@ApiModel(value="CommodityType对象", description="")
public class CommodityType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品类型id")
    @TableId("type_id")
    private Integer typeId;

    @ApiModelProperty(value = "商品类型名称")
    @TableField("type_name")
    private String typeName;


}
