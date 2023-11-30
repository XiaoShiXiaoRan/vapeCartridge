package com.fidnortech.xjx.system.entity.vo;


import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author pgy
 * @since 2022-09-19
 */
@Data
public class SysDepartVo {

    private String id;

    private String name;

    private String parentId;

    private String createBy;

    private Date createTime;

    private Integer isDel;

    private Date updateTime;

    private String updateBy;

    private String descInfo;

    private List<SysDepartVo> children;


}
