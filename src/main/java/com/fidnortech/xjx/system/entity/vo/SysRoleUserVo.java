package com.fidnortech.xjx.system.entity.vo;

import com.fidnortech.xjx.base.EntityBase;
import com.fidnortech.xjx.system.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SysRoleUserVo extends EntityBase {

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String desc;

    @ApiModelProperty(value = "角色")
    private List<SysUser> userList;

}
