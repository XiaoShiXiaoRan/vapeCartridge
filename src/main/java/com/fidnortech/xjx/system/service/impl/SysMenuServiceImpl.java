package com.fidnortech.xjx.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysMenu;
import com.fidnortech.xjx.system.entity.SysRole;
import com.fidnortech.xjx.system.entity.SysUser;
import com.fidnortech.xjx.system.entity.vo.SysMenuTree;
import com.fidnortech.xjx.system.mapper.SysMenuMapper;
import com.fidnortech.xjx.system.service.SysMenuRoleService;
import com.fidnortech.xjx.system.service.SysMenuService;
import com.fidnortech.xjx.system.service.SysRoleService;
import com.fidnortech.xjx.system.service.SysUserService;
import com.fidnortech.xjx.utils.MenuUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Log4j2
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {


    private static String[] defaultMenu;

    static {
        defaultMenu = new String[0];
        // defaultMenu[0] = "系统运维";
        // defaultMenu[1] = "功能管理";
    }


    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;


    @Override
    public ResponseMessage getMenuList(Page page, QueryWrapper<SysMenu> queryWrapper) {
        queryWrapper.eq("is_del", 0);
        List<SysMenu> list = this.list(queryWrapper);
        List<SysMenu> retlist = getMenuTree(list);
        return ResponseMessage.success(retlist);
    }

    @Override
    public ResponseMessage deleteMenuById(String id) {

        SysMenu obj = this.getById(id);
        if (obj == null) {
            return ResponseMessage.success();
        }

        if (isExistChildrenMenu(obj)) {
            return ResponseMessage.error("存在子级菜单，无法删除。");
        }
        sysMenuRoleService.deleteByMenuId(obj.getId());
        obj.setIsDel(1);
        // this.saveOrUpdate(obj);
        this.removeById(obj.getId());
        return ResponseMessage.success();
    }

    @Override
    public ResponseMessage saveMenuInfo(SysMenu sysMenu) {
        String id = sysMenu.getId();
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper();
        queryWrapper.eq("name", sysMenu.getName());
        List<SysMenu> list = this.list(queryWrapper);
        if (id != null) {
            if (list.size() > 0) {
                SysMenu menuObj = list.get(0);
                String presentId = menuObj.getId();
                if (!presentId.equals(id)) {
                    return ResponseMessage.error("菜单名称重复");
                }
            }
        } else {
            if (list.size() > 0) {
                return ResponseMessage.error("菜单名称重复");
            }
        }
        sysMenu.setIsDel(0);
        this.saveOrUpdate(sysMenu);
        return ResponseMessage.success();
    }


    /**
     * @return com.springboot.bus.common.RFesponseMessage
     *
     * @Description 根据用户id获取用户菜单（用户菜单 = 角色菜单 + 平台审批申请的菜单）
     * @Date 2022/6/13 15:53
     * @Method getMenuByUserId
     * @Param * @param userId
     **/
    @Override
    public ResponseMessage getMenuByUserId(String userId) {
        //根据用户sd查询角色菜单

        //通过用户id 查询 用户角色
        SysUser user = sysUserService.getById(userId);
        //通过角色id 去查询角色 的 菜单列表
        SysRole role = sysRoleService.getById(user.getRoleId());

        String menuids = role.getMenuList();

        String[] menulist = menuids.split(",");

        List<SysMenu> menuList = new ArrayList<>();

        for (int i = 0; i < menulist.length; i++) {

            menuList.add(sysMenuService.getById(menulist[i]));

        }




//        List<SysMenu> menulist = sysMenuMapper.getMenuByUserId(userId);

        //根据用户id查询申请通过菜单
//        List<SysMenu> applyList = sysMenuMapper.userMenuList(userId);
        //合并菜单
//        List<SysMenu> list = mergeMenu(menulist, applyList);
//        List<SysMenu> tree = MenuUtil.menuListToTree(menuList);
//        List<SysMenu> retList = getUserMenu(tree);
        List<SysMenuTree> menutree = MenuUtil.menuListTree(menuList);
        return ResponseMessage.success(menutree);
//        return  null;
    }

    /**
     * 合并菜单  把角色菜单加上审批通过的菜单
     * @param roleMenulist 角色菜单集合
     * @param userApplyMenuList 用户申请菜单集合
     * @return java.util.List<com.springboot.bus.system.entity.SysMenu>
     * @author Administrator
     * @date 2022/9/21 17:15
     */
    private List<SysMenu> mergeMenu(List<SysMenu> roleMenulist, List<SysMenu> userApplyMenuList) {

        //合并菜单
        for (SysMenu applyMenu : userApplyMenuList) {
            if (StringUtils.isNotBlank(applyMenu.getMenuId())) {
                for (SysMenu roleMenu : roleMenulist) {
                    if (roleMenu.getId().equals(applyMenu.getId())) {
                        roleMenu.setMenuId(applyMenu.getMenuId());
                    }
                }
            }
        }
        //设置默认菜单
        for (SysMenu sysMenu : roleMenulist) {
            for (String menuName : defaultMenu) {
                if (sysMenu.getName().equals(menuName)) {
                    sysMenu.setMenuId(sysMenu.getId());
                }
            }
        }
        return roleMenulist;
    }


    /**
     * @return
     *
     * @Description //TODO
     * @Date 2022/6/13 15:47
     * @Param * @param
     **/
    @Override
    public ResponseMessage getAllMenu() {
        List<SysMenu> list = this.list();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setStatus(1);
        }
        List<SysMenuTree> menutree = MenuUtil.menuListTree(list);
        return ResponseMessage.success(menutree);
    }

    @Override
    public String getRootMenu() {
        List<SysMenu> menuList = sysMenuMapper.getRootMenu();

        List ids = new ArrayList();
        for (int i = 0; i < menuList.size(); i++) {
            ids.add(menuList.get(i).getMenuId());
        }
//        String str = ids.toString();
//        if (str != null && str.length() > 0){
//            str = str.substring(1,str.length() - 1);
//        }
//        str.trim(); //去掉首尾空格
//        str.replace(" ",""); //去除所有空格，包括首尾、中间
//        str.replaceAll(" ", ""); //去掉所有空格，包括首尾、中间
//        str.replaceAll(" +","");//去掉所有空格，包括首尾、中间
//        str.replaceAll("\\s*", ""); //可以替换大部分空白字符， 不限于空格 ；

        return StringUtils.join(ids, ",");
    }

    @Override
    public List getRootMenuTist() {
        List<SysMenu> menuList = sysMenuMapper.getRootMenu();

        List ids = new ArrayList();
        for (int i = 0; i < menuList.size(); i++) {
            ids.add(menuList.get(i).getMenuId());
        }

        return ids;
    }


    private List<SysMenu> getUserMenu(List<SysMenu> list) {
        List<SysMenu> retList = new ArrayList<SysMenu>();
        for (SysMenu obj : list) {
            if (obj.getIsShow().intValue() == 0) {
                List<SysMenu> children = obj.getChildren();
                if (CollectionUtils.isNotEmpty(children)) {
                    List<SysMenu> byMenu = getUserMenu(children);
                    if (CollectionUtils.isNotEmpty(byMenu)) {
                        obj.setChildren(byMenu);
                        retList.add(obj);
                    } else {
                        if (obj.getMenuId() != null) {
                            retList.add(obj);
                        }
                    }
                } else {
                    if (obj.getMenuId() != null) {
                        retList.add(obj);
                    }
                }
            }
        }

        return retList;
    }

    /*****
     *
     * 判断是否存在叶子节点
     * @return
     */
    private boolean isExistChildrenMenu(SysMenu menuObj) {

        boolean tag = false;

        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", menuObj.getId());
        queryWrapper.eq("is_del", 0);
        List<SysMenu> list = this.list(queryWrapper);
        if (list.size() > 0) {
            return true;
        }
        return tag;
    }

    /*****
     *
     * @return将列表数据转树形数据
     */
    private List<SysMenu> getMenuTree(List<SysMenu> list) {
        List<SysMenu> retList = new ArrayList<SysMenu>();
        for (SysMenu obj : list) {
            //默认设置 parentId null为根节点
            if (obj.getParentId().equals("0")) {
                List<SysMenu> children = getAllChildren(obj.getId(), list);
                if (children.size() > 0) {
                    obj.setChildren(children);
                }
                retList.add(obj);
            }
        }
        return retList;
    }

    @Override
    public ResponseMessage menuApplyList(String userId) {
        List<SysMenu> list = sysMenuMapper.menuApplyList(userId);
        List<SysMenu> retlist = getMenuTree(list);
        return ResponseMessage.success(retlist);
    }

    /*****
     *
     * @return根据父级id获取所有子节点列表
     */
    private List<SysMenu> getAllChildren(String parentId, List<SysMenu> list) {
        List<SysMenu> retList = new ArrayList<SysMenu>();
        for (SysMenu objMenu : list) {
            String presentParentId = objMenu.getParentId();
            if (!presentParentId.equals("0") && presentParentId.equals(parentId)) {
                List<SysMenu> children = this.getAllChildren(objMenu.getId(), list);
                if (children.size() > 0) {
                    objMenu.setChildren(children);
                }
                retList.add(objMenu);
            }
        }
        return retList;
    }
}
