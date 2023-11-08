package com.fidnortech.xjx.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fidnortech.xjx.base.EntityBase;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableCharset;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableEngine;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;

/**
 * <p>
 * 用户表
 * </p>
 *
*
 * @since 2021-08-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_user")
@ApiModel(value="SysUser对象", description="用户表")
@TableCharset(MySqlCharsetConstant.UTF8)
@TableEngine(MySqlEngineConstant.InnoDB)
public class SysUser extends EntityBase {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    @Pattern(regexp = "^admin$",message = "admin用户无法删除")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "生日")
    @TableField("birth_day")
    private String birthDay;



    @ApiModelProperty(value = "部门id")
    @TableField("depart_id")
    private String  departId;


    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String  name;


    @ApiModelProperty(value = "部门名称")
    @TableField(exist = false)
    private String  departName;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String  email;


    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private String  roleId;

//    @ApiModelProperty(value = "角色名称")
//    private String  roleName;

}
