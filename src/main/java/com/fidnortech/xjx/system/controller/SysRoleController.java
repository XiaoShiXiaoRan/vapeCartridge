package com.fidnortech.xjx.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Sets;
import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.base.UserVO;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysMenu;
import com.fidnortech.xjx.system.entity.SysRole;
import com.fidnortech.xjx.system.entity.SysUser;
import com.fidnortech.xjx.system.service.SysMenuService;
import com.fidnortech.xjx.system.service.SysRoleService;
import com.fidnortech.xjx.system.service.SysUserService;
import com.fidnortech.xjx.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.fidnortech.xjx.base.BaseController;

import java.util.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author linyq
 * @since 2021-09-15
 */
@Controller
@RequestMapping("/system/sysRole")
@Api(tags = "运维管理-角色管理")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/getRoleListPage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="获取角色列表分页")
    @LogRecord(modular = "运维管理",value = "查询")
    public ResponseMessage getRoleListPage(String name) {

        Page<SysRole>  page = this.getPagination();

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_del", 0);
        queryWrapper.orderByDesc("sort");
        queryWrapper.like(StringUtils.isNotBlank(name),"name",name);
        IPage<SysRole> sysRoleIPage = sysRoleService.page(page, queryWrapper);
        return ResponseMessage.success(sysRoleIPage);
    }


    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="获取角色列表")
    @LogRecord(modular = "运维管理",value = "查询")
    public ResponseMessage getList(String name) {
        Page page = this.getPagination();
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper();
        queryWrapper.eq("is_del", 0);
        queryWrapper.like(StringUtils.isNotBlank(name),"name",name);
        queryWrapper.orderByDesc("sort");
        IPage<SysRole> sysRoleIPage = sysRoleService.page(page, queryWrapper);
        return ResponseMessage.success(sysRoleIPage);
    }

    @RequestMapping(value = "/getRoleById", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="根据ID获取角色信息")
    @LogRecord(modular = "运维管理",value = "查询")
    public ResponseMessage getByRoleId(@RequestParam("id") String id) {
        return ResponseMessage.success(sysRoleService.getById(id));
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="修改角色信息")
    @LogRecord(modular = "运维管理",value = "保存")
    public ResponseMessage getById(SysRole sysRole) {

        String name = sysRole.getName();

        //校验名称重复
        QueryWrapper<SysRole> queryNameWrapper = new QueryWrapper();
        queryNameWrapper.eq("name", name);
        List<SysRole> queryNameList = sysRoleService.list(queryNameWrapper);
        if (queryNameList.size()>0){
            for (int i = 0; i < queryNameList.size(); i++) {
                if (queryNameList.get(i).getName().equals(name)){
                    if (queryNameList.get(i).getId().equals(sysRole.getId())){
                        sysRole.setIsDel(0);
                        sysRole.setUpdateTime(DateUtil.getToday());

                        //授权
                        sysRoleService.saveOrUpdate(sysRole);

                    }else {
                        return ResponseMessage.error("角色名重复，请重新输入");
                    }
                }

            }
        }else {
            sysRole.setIsDel(0);
            sysRole.setCreateTime(DateUtil.getToday());

            //新增角色默认添加根菜单
            String str = sysMenuService.getRootMenu();

            sysRole.setMenuList(str);

            sysRoleService.saveOrUpdate(sysRole);
            return ResponseMessage.success("角色新增成功");
        }



        return ResponseMessage.success("角色保存成功");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="根据ids批量删除")
    @LogRecord(modular = "运维管理",value = "删除")
    public ResponseMessage deleteByIds(String id) {

        //查询所有用户 遍历是否有用户绑定此角色如果有 就提示 有用户正在使用此角色无法删除
        List<SysUser> userList = sysUserService.list();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getRoleId().equals(id)){
                return  ResponseMessage.error("有用户正在使用此角色无法删除");
            }
        }

        final boolean b = sysRoleService.removeById(id);
        if (b){
            return ResponseMessage.success("删除成功");
        }
        return  ResponseMessage.error("删除失败");
    }

    @RequestMapping(value = "/getTreeRole", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="获取树形角色数据")
    @LogRecord(modular = "运维管理",value = "查询")
    public ResponseMessage getTreeRole(String name) {
        return ResponseMessage.success(sysRoleService.getTreeRole(name));
    }


    /**
     * 由于树形控件 选中父id就会勾选所有子id这里我就不查询根id了
     * @param id
     * @return
     */
    @RequestMapping(value = "/getRoleMenu", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="获取树形角色数据")
    @LogRecord(modular = "运维管理",value = "查询")
    public ResponseMessage getRoleMenu(String id) {


        /**
         * 根据id查询该角色除根菜单的菜单id
         *
         */

        //拿到根菜单id
        List<String> menuid0 = sysMenuService.getRootMenuTist();

        //查询除了根目录 以外的菜单id
        SysRole role = sysRoleService.getById(id);
        String str;
        if (!StringUtils.isBlank(role.getMenuList())){
            str = role.getMenuList();
            String[] menus = str.split(",");
            //拿到角色菜单 id列表
            List<String> menuid1 = Arrays.asList(menus);

            //将角色菜单 id列表 中的 根菜单id  去除
//            menuid1.removeAll(menuid0);
            List datalist = listrem(menuid1,menuid0);

            return ResponseMessage.success(datalist);
        }
        return ResponseMessage.success(new ArrayList());
    }


    @RequestMapping(value = "/empower", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="角色授权")
    @LogRecord(modular = "运维管理",value = "角色授权")
    public ResponseMessage empower(SysRole sysRole) {

//        sysRole.setMenuList(sysRole.getMenuList()+str);
        //权限过滤，必须要有所有根菜单 ，有部分根菜单就和所有根菜单去除重复的菜单 id
        //获取根菜单转换数组
        String str = sysMenuService.getRootMenu();
        String[] menus = str.split(",");
        //拿到 根菜单 id列表
        List<String> menuid1 = Arrays.asList(menus);
        //拿到传过来的 菜单列表
        String str2 = sysRole.getMenuList();
        String[] menus2 = str2.split(",");
        //拿到 角色菜单 id列表
        List<String> menuid2 = Arrays.asList(menus2);

        //menuid2  menuid1  对比  menuid2 一定要有 menuid1    取并集
//        menuid1.addAll(menuid2);

        Collection union = CollectionUtils.union(menuid1, menuid2);

        List<T>   list   =    new  ArrayList<T>(union);

//        // 并集 去重
//        LinkedHashSet<String> hashSet = new LinkedHashSet<>(menuid1);
//        ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);

        List ids = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            ids.add(list.get(i));
        }
        String datalist = StringUtils.join(ids, ",");

        sysRole.setMenuList(datalist);

        boolean b = sysRoleService.saveOrUpdate(sysRole);

        return ResponseMessage.success("授权成功");
    }



    public static List<String> listrem(List<String> listA,List<String> listB){
        HashSet hs1 = new HashSet(listA);
        HashSet hs2 = new HashSet(listB);
        hs1.removeAll(hs2);
        List<String> listC = new ArrayList<String>();
        listC.addAll(hs1);
        return listC;
    }
}
