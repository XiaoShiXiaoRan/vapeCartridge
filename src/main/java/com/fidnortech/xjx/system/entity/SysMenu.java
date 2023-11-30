package com.fidnortech.xjx.system.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableCharset;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableEngine;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import com.fidnortech.xjx.base.EntityBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@TableName(value ="t_sys_menu")
@ApiModel(value="SysMenu对象", description="")
@TableCharset(MySqlCharsetConstant.UTF8)
@TableEngine(MySqlEngineConstant.InnoDB)
public class SysMenu extends EntityBase {


    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "父级id")
    @TableField("parent_id")
    private String parentId;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    @TableField("name")
    private String name;

    /**
     * 权限标识符
     */
    @ApiModelProperty(value = "权限标识符")
    @TableField("permission")
    private String permission;

    /**
     * 路由
     */
    @ApiModelProperty(value = "路由")
    @TableField("route")
    private String route;

    /**
     * 是否隐藏 0：显示 1隐藏
     */
    @ApiModelProperty(value = "是否隐藏 ")
    @TableField("is_show")
    private Integer isShow;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;


    @TableField(exist = false)
    @ApiModelProperty(value = "子菜单")
    private List<SysMenu>  children;


    @ApiModelProperty(value = "角色ID")
    @TableField(exist = false)
    private String roleId;


    @ApiModelProperty(value = "菜单ID")
    @TableField(exist = false)
    private String menuId;

    @ApiModelProperty(value = "菜单配置标识符：1配置 0未配置")
    @TableField(exist = false)
    private Integer status;


    @ApiModelProperty(value = "状态 0未申请 1审核通过 2 审核中 3审核未通过 ")
    @TableField(exist = false)
    private Integer applyState;


    @ApiModelProperty(value = "备注")
    @TableField(exist = false)
    private String remark;


    @ApiModelProperty(value = "子类id集合")
    @TableField(exist = false)
    private String childrenIds;


}