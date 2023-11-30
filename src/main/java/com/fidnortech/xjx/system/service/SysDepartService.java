package com.fidnortech.xjx.system.service;

import com.fidnortech.xjx.common.ResponseMessage;

import com.fidnortech.xjx.system.entity.SysDepart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface SysDepartService extends IService<SysDepart> {

    List<SysDepart> getTree();

    //根据id 和范围数据 查找子部门数据
    List<SysDepart> getAllChildren(String parentId, List<SysDepart> list);

    Map<String, SysDepart> getDepartMap();

    ResponseMessage saveDepart(SysDepart sysDepart);
}
