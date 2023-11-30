package com.fidnortech.xjx.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fidnortech.xjx.base.EntityBase;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableCharset;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableEngine;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName t_sys_menu_role
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value ="t_sys_menu_role")
@Accessors(chain = true)
@ApiModel(value="SysMenuRole对象", description="")
@TableCharset(MySqlCharsetConstant.UTF8)
@TableEngine(MySqlEngineConstant.InnoDB)
public class SysMenuRole  extends EntityBase {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableField(value = "role_id")
    private String roleId;


    @ApiModelProperty(value = "菜单ID")
    @TableField(value = "menu_id")
    private String menuId;


    @TableField(exist = false)
    @ApiModelProperty(value = "菜单名称")
    private String name;


    @ApiModelProperty(value = "父级id")
    @TableField(exist = false)
    private String parentId;






}