package com.fidnortech.xjx.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fidnortech.xjx.base.EntityBase;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableCharset;
import com.gitee.sunchenbin.mybatis.actable.annotation.TableEngine;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author linyq
 * @since 2021-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sys_role")
@ApiModel(value="SysRole对象", description="")
@TableCharset(MySqlCharsetConstant.UTF8)
@TableEngine(MySqlEngineConstant.InnoDB)
public class SysRole extends EntityBase {

    private static final long serialVersionUID = 1L;


    @TableField("name")
    private String name;

    @TableField("sort")
    private Integer sort;

    @TableField("desc_info")
    private String descInfo;

    @TableField("status")
    private Integer status;

    @TableField("menu_list")
    private String menuList;

}
