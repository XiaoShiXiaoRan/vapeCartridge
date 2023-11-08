package com.fidnortech.xjx.system.entity.vo;

import com.fidnortech.xjx.base.EntityBase;
import com.fidnortech.xjx.system.entity.SysUserRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SysUserRoleVo  extends EntityBase {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "地址")
    private String phone;

    @ApiModelProperty(value = "地址")
    private String sex;

    @ApiModelProperty(value = "地址")
    private String birthDay;

    @ApiModelProperty(value = "角色")
    private List<SysUserRole> roleList;

    @ApiModelProperty(value = "部门id")
    private String  departId;


}
