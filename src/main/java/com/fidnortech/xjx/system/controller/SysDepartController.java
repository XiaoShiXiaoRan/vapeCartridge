package com.fidnortech.xjx.system.controller;


import com.fidnortech.xjx.annotation.LogRecord;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysDepart;
import com.fidnortech.xjx.system.service. SysDepartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import com.fidnortech.xjx.base.BaseController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author linyq
 * @since 2021-11-04
 */
@Controller
@RequestMapping("/system/sys-depart")
@Api(tags = "运维管理-部门管理")
public class SysDepartController extends BaseController {


    @Autowired
    private SysDepartService sysDepartService;

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    @ApiOperation(value = "获取部门列表")
    @LogRecord(modular = "运维管理", value = "查询")
    public ResponseMessage getTree() {
        return ResponseMessage.success(sysDepartService.getTree());
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "保存")
    @LogRecord(modular = "运维管理", value = "保存")
    public ResponseMessage saveDepart(SysDepart sysDepart) {
        return sysDepartService.saveDepart(sysDepart);

    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据id删除")
    @LogRecord(modular = "运维管理", value = "删除")
    public ResponseMessage delete(String id) {
//        final boolean removeById = sysDepartService.removeById(id);
        //逻辑删除 将is_del改成1
        SysDepart sysDepart = new SysDepart();
        sysDepart.setId(id);
        sysDepart.setIsDel(1);
        final boolean b = sysDepartService.updateById(sysDepart);
        return ResponseMessage.success(b);
    }
}
