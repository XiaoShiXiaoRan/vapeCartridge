package com.fidnortech.xjx.utils;

import com.fidnortech.xjx.system.entity.SysMenu;
import com.fidnortech.xjx.system.entity.vo.SysMenuTree;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class MenuUtil {

    /**
     * 递归树
     * @author Administrator
     * @date 2022/11/4 16:55
     * @version 1.0
     */
    public static List<SysMenuTree> getAllMenuChildren(String parentId, List<SysMenu> list) {
        List<SysMenuTree> retList = new ArrayList<SysMenuTree>(2);
        for (SysMenu objMenu : list) {
            SysMenuTree sysMenuTree = new SysMenuTree();
            String presentParentId = objMenu.getParentId();
            if (!presentParentId.equals("0") && presentParentId.equals(parentId)) {
                List<SysMenuTree> children = getAllMenuChildren(objMenu.getId(), list);
                if (!CollectionUtils.isEmpty(children)) {
                    sysMenuTree.setChildren(children);
                }
                sysMenuTree.setMenuId(objMenu.getId());
                sysMenuTree.setMenuName(objMenu.getName());
                sysMenuTree.setPath(objMenu.getRoute());
                sysMenuTree.setIcon(objMenu.getIcon());
                sysMenuTree.setParentId(objMenu.getParentId());
                retList.add(sysMenuTree);
            }
        }
        return retList;
    }
    /**
     * 菜单树
     * @author Administrator
     * @date 2022/11/4 16:55
     * @version 1.0
     */
    public static List<SysMenuTree> menuListTree(List<SysMenu> list) {
        List<SysMenuTree> retList = new ArrayList<SysMenuTree>(10);
        for (SysMenu obj : list) {
            //默认设置 parentId null为根节点
            if ("0".equals(obj.getParentId())) {
                SysMenuTree sysMenuTree = new SysMenuTree();
                List<SysMenuTree> children = getAllMenuChildren(obj.getId(), list);
                if (!CollectionUtils.isEmpty(children)) {
                    sysMenuTree.setChildren(children);
                } else {
                    sysMenuTree.setChildren(new ArrayList<>(2));
                }
                sysMenuTree.setMenuId(obj.getId());
                sysMenuTree.setMenuName(obj.getName());
                sysMenuTree.setPath(obj.getRoute());
                sysMenuTree.setIcon(obj.getIcon());
                sysMenuTree.setParentId(obj.getParentId());
                retList.add(sysMenuTree);
            }else {
                SysMenuTree sysMenuTree = new SysMenuTree();

            }
        }
        return retList;
    }

    public static List<SysMenu> menuListToTree(List<SysMenu> list) {
        List<SysMenu> retList = new ArrayList<SysMenu>(10);
        for (SysMenu obj : list) {
            //默认设置 parentId null为根节点
            if ("0".equals(obj.getParentId())) {
                List<SysMenu> children = getAllChildren(obj.getId(), list);
                if (!CollectionUtils.isEmpty(children)) {
                    obj.setChildren(children);
                }
                retList.add(obj);
            }
        }
        return retList;
    }

    public static List<SysMenu> getAllChildren(String parentId, List<SysMenu> list) {
        List<SysMenu> retList = new ArrayList<SysMenu>(2);
        for (SysMenu objMenu : list) {
            String presentParentId = objMenu.getParentId();
            if (!presentParentId.equals("0") && presentParentId.equals(parentId)) {
                List<SysMenu> children = getAllChildren(objMenu.getId(), list);
                if (!CollectionUtils.isEmpty(children)) {
                    objMenu.setChildren(children);
                }
                retList.add(objMenu);
            }
        }
        return retList;
    }

}
