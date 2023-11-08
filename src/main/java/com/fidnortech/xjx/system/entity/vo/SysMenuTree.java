package com.fidnortech.xjx.system.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * SysMenuTree
 *
 * @author Administrator
 * @version 1.0
 * @date 2022/11/4 16:13
 */
@Data
public class SysMenuTree {
    private String menuId;
    private String menuName;
    private String path;
    private String icon;
    private String parentId;
    private List<SysMenuTree> children;
}
