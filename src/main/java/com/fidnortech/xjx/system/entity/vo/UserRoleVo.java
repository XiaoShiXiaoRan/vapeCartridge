package com.fidnortech.xjx.system.entity.vo;


import com.fidnortech.xjx.system.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserRoleVo extends SysUser {
    @ApiModelProperty(value = "角色名称")
    private String  roleName;
}
