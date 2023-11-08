package com.fidnortech.xjx.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.base.BaseController;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysMenu;
import com.fidnortech.xjx.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author pangguangyu
 * @since 2021-10-28
 */
@Controller
@RequestMapping("/system/sysMenu")
@Api(tags = "运维管理-菜单管理")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据id获取")
    @LogRecord(modular = "运维管理", value = "查询")
    public ResponseMessage getById(@RequestParam("id") String id) {
        return ResponseMessage.success(sysMenuService.getById(id));
    }

    @RequestMapping(value = "/getMenuList", method = RequestMethod.POST)
    @ResponseBody
    @LogRecord(modular = "运维管理", value = "查询")
    @ApiOperation(value = "根据菜单列表")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseMessage getPage() {
        Page page = this.getPagination();
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper();
        return sysMenuService.getMenuList(page, queryWrapper);
    }

    @RequestMapping(value = "/getMenuByUserId", method = RequestMethod.POST)
    @ResponseBody
//    @LogRecord(modular = "运维管理", value = "查询")
    @ApiOperation(value = "跟用户id获取菜单")
//    @PreAuthorize("hasAuthority('admin')")
    public ResponseMessage getMenuByUserId(@RequestParam("id") String id) {
        if (this.getUser().getUserName().equals("admin")) {
            return sysMenuService.getAllMenu();
        }
        return sysMenuService.getMenuByUserId(id);
    }

    @RequestMapping(value = "/getMenuApplyList", method = RequestMethod.POST)
    @ResponseBody
    @LogRecord(modular = "运维管理", value = "查询")
    @ApiOperation(value = "获取用户菜单申请列表")
    public ResponseMessage menuApplyList() {
        String userId = this.getUser().getId();
        return sysMenuService.menuApplyList(userId);
    }

    @RequestMapping(value = "/deleteMenuById", method = RequestMethod.POST)
    @ResponseBody
    @LogRecord(modular = "运维管理", value = "删除")
    @ApiOperation(value = "根据id删除")
    public ResponseMessage deleteMenuById(@RequestParam("id") String id) {
        return sysMenuService.deleteMenuById(id);
    }

    @RequestMapping(value = "/saveMenuInfo", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存菜单信息")
    @LogRecord(modular = "运维管理", value = "查询")
    public ResponseMessage saveMenuInfo(SysMenu sysMenu) {
        return sysMenuService.saveMenuInfo(sysMenu);
    }

    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取菜单列表")
    @LogRecord(modular = "运维管理", value = "查询")
    public ResponseMessage getMapService() {
        return ResponseMessage.success(sysMenuService.getAllMenu());
    }
}
