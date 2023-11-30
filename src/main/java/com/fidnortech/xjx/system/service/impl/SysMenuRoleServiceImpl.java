package com.fidnortech.xjx.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysMenu;
import com.fidnortech.xjx.system.entity.SysMenuRole;
import com.fidnortech.xjx.system.mapper.SysMenuRoleMapper;
import com.fidnortech.xjx.system.service.SysMenuRoleService;
import com.fidnortech.xjx.system.service.SysMenuService;
import com.fidnortech.xjx.utils.MenuUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Service
public class SysMenuRoleServiceImpl extends ServiceImpl<SysMenuRoleMapper, SysMenuRole>
        implements SysMenuRoleService {


    @Autowired
    private SysMenuRoleMapper sysMenuRoleMapper;


    @Autowired
    private SysMenuService SysMenuService;


    @Override
    public ResponseMessage getMenuByRoleId(String id) {
        List<SysMenu> list = sysMenuRoleMapper.getMenuByRoleId(id);
        for (SysMenu obj : list) {
            String roleId = obj.getRoleId();
            if (roleId != null && roleId.equals(id)) {
                //已配置菜单
                obj.setStatus(1);
            } else {
                //未配置菜单
                obj.setStatus(0);
                obj.setRoleId(id);
            }
        }
        return ResponseMessage.success(MenuUtil.menuListToTree(list));
    }

    @Override
    public ResponseMessage saveMenuRoleInfo(SysMenu sysMenu) {

        //菜单配置标识符：1配置 0未配置
        Integer status = sysMenu.getStatus();
        String roleId = sysMenu.getRoleId();
        String menuId = sysMenu.getMenuId();


        if (status == null || roleId == null || menuId == null) {
            return ResponseMessage.error("修改失败。");
        } else {
            QueryWrapper<SysMenuRole> queryWrapper = new QueryWrapper();
            queryWrapper.eq("role_id", roleId);
            queryWrapper.eq("menu_id", menuId);
            List<SysMenuRole> list = this.list(queryWrapper);
            SysMenuRole menuRoleObj = null;
            if (status.intValue() == 0) {
                if (CollectionUtils.isNotEmpty(list)) {
                    menuRoleObj = list.get(0);
                    sysMenuRoleMapper.deleteById(menuRoleObj.getId());
                    //sysMenuRoleMapper.de
                }
            } else if (status.intValue() == 1) {
                if (CollectionUtils.isEmpty(list)) {
                    menuRoleObj = new SysMenuRole();
                    menuRoleObj.setRoleId(roleId);
                    menuRoleObj.setMenuId(menuId);
                    menuRoleObj.setIsDel(0);
                    super.saveOrUpdate(menuRoleObj);
                }
            }
        }
        if (StringUtils.isNotBlank(sysMenu.getChildrenIds())) {
            String[] childrenIds = sysMenu.getChildrenIds().split(",");

            if (childrenIds.length > 0) {
                if (status.intValue() == 0) {
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("role_id", roleId);
                    queryWrapper.in("menu_id", Arrays.asList(childrenIds));
                    sysMenuRoleMapper.delete(queryWrapper);
                } else if (status.intValue() == 1) {

                    List<SysMenuRole> updateList = new ArrayList<SysMenuRole>();
                    for (String childrenMenuId : childrenIds) {
                        SysMenuRole update = new SysMenuRole();
                        update.setRoleId(roleId);
                        update.setMenuId(childrenMenuId);
                        update.setIsDel(0);
                        updateList.add(update);
                    }
                    super.saveOrUpdateBatch(updateList);
                }
            }
        }
        return ResponseMessage.success();
    }

    @Override
    public boolean deleteByMenuId(String menuId) {
        QueryWrapper<SysMenuRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq("menu_id", menuId);
        List<SysMenuRole> list = this.list(queryWrapper);
        List<String> ids = new ArrayList<>(list.size());
        for (SysMenuRole obj : list) {
            ids.add(obj.getId());
        }
        if (CollectionUtils.isNotEmpty(list)) {
            return super.removeByIds(ids);
        }
        return true;
    }
}




