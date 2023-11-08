package com.fidnortech.xjx.system.entity.vo;

import com.fidnortech.xjx.base.EntityBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author linyq
 * @since 2021-08-11
 */
@Data
@ApiModel(value="SysUserVO对象")
public class SysUserVO extends EntityBase {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String userName;


    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "地址")
    private String phone;

    @ApiModelProperty(value = "地址")
    private String sex;

    @ApiModelProperty(value = "地址")
    private String birthDay;



    @ApiModelProperty(value = "部门id")
    private String  departId;


    @ApiModelProperty(value = "姓名")
    private String  name;


    @ApiModelProperty(value = "部门名称")
    private String  departName;

}
