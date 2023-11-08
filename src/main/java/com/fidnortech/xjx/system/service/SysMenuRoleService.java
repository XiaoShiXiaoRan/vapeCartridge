package com.fidnortech.xjx.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysMenu;
import com.fidnortech.xjx.system.entity.SysMenuRole;

/**
 *
 */
public interface SysMenuRoleService extends IService<SysMenuRole> {

    ResponseMessage getMenuByRoleId(String id);

    ResponseMessage saveMenuRoleInfo(SysMenu sysMenu);

    boolean deleteByMenuId(String menuId);
}
