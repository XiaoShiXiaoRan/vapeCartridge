package com.fidnortech.xjx.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserVO {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户角色")
    private String roles;

    @ApiModelProperty(value = "用户部门")
    private String departId;
}
