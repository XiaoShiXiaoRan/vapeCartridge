package com.fidnortech.xjx.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

import com.fidnortech.xjx.base.EntityBase;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableCharset;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableEngine;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 */
@Data
@Accessors(chain = true)
@TableName("t_sys_depart")
@ApiModel(value="SysDepart对象", description="")
@TableCharset(MySqlCharsetConstant.UTF8)
@TableEngine(MySqlEngineConstant.InnoDB)
public class SysDepart extends EntityBase {

    private static final long serialVersionUID = 1L;




    @ApiModelProperty(value="部门名称")
    @TableField("name")
    private String name;


    @ApiModelProperty(value="排序信息")
    @TableField("desc_info")
    private String descInfo;


    @ApiModelProperty(value="父ID")
    @TableField("parent_id")
    private String parentId;

    @TableField(exist = false)
    @ApiModelProperty(value = "子集合")
    private List<SysDepart> children;



}
