package com.fidnortech.xjx.commodity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@TableName("t_commodity")
@ApiModel(value="Commodity对象", description="")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Commodity implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @TableId(value = "id",type = IdType.ID_WORKER_STR )
    private String id;

    @ApiModelProperty(value = "商品类别id")
    @TableField("type_id")
    private Integer typeId;

    @ApiModelProperty(value = "商品名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "商品信息描述")
    @TableField("information")
    private String information;

    @ApiModelProperty(value = "商品图片")
    @TableField("image")
    private byte[] image;

    @ApiModelProperty(value = "商品详情页路由")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "热度排行")
    @TableField("heat")
    private Integer heat;

    @ApiModelProperty(value = "删除标识 0：否，1：是")
    @TableField("is_del")
    private Integer isDel;
}
