package com.fidnortech.xjx.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.base.BaseController;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysMenu;
import com.fidnortech.xjx.system.entity.SysMenuRole;
import com.fidnortech.xjx.system.service.SysMenuRoleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;


/**
 * @author pangguangyu
 * @since 2021-10-28
 */
@Controller
@RequestMapping("/system/sysMenuRole")
@Api(tags = "运维管理-角色菜单管理")
public class SysMenuRoleController extends BaseController {

    @Autowired
    private SysMenuRoleService sysMenuRoleService;


    @RequestMapping(value="/selectListPage",method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value="获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "分页大小", dataType = "Integer"),
    })
    @LogRecord(modular = "运维管理",value = "查询")
    public ResponseMessage getList(Long current, Long size) {
        Page page = new Page<>(current, size);
        QueryWrapper<SysMenuRole> queryWrapper = new QueryWrapper();
        IPage<SysMenu> sysMenuList = sysMenuRoleService.page(page, queryWrapper);
        return ResponseMessage.success(sysMenuList);
    }


    @RequestMapping(value = "/getMenuByRoleId",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="根据角色id获取菜单")
    @LogRecord(modular = "运维管理",value = "查询")
    public ResponseMessage getMenuByRoleId(@RequestParam("id") String id) {
        return sysMenuRoleService.getMenuByRoleId(id);
    }

    @RequestMapping(value = "/saveMenuRoleInfo",method = RequestMethod.POST)
    @ResponseBody
    @LogRecord(modular = "运维管理",value = "保存")
    @ApiOperation(value="保存菜单角色信息")
    public ResponseMessage saveMenuRoleInfo(SysMenu SysMenu) {
        return sysMenuRoleService.saveMenuRoleInfo(SysMenu);
    }

    @RequestMapping(value = "/delete",method  = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value="根据ids批量删除")
    @LogRecord(modular = "角色", value = "删除")
    public ResponseMessage delete(String ids){
        String[] str = ids.split(",");
        final boolean b = sysMenuRoleService.removeByIds(Arrays.asList(str));
        if (!b){
            return ResponseMessage.success("删除失败");
        }
        return ResponseMessage.success("删除成功");
    }


}
