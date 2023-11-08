package com.fidnortech.xjx.system.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysMenu;

import java.util.List;

/**
*
*/
public interface SysMenuService extends IService<SysMenu> {


    ResponseMessage menuApplyList(String userId);

    public  ResponseMessage getMenuList(Page page
    , QueryWrapper<SysMenu> queryWrapper);

    ResponseMessage deleteMenuById(String id);

    ResponseMessage saveMenuInfo(SysMenu sysMenu);

    ResponseMessage getMenuByUserId(String id);

    ResponseMessage getAllMenu();

    String getRootMenu();

    List getRootMenuTist();


}
