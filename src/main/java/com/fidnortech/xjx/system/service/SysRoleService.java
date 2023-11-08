package com.fidnortech.xjx.system.service;

import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysMenu;
import com.fidnortech.xjx.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fidnortech.xjx.system.entity.vo.SysRoleUserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linyq
 * @since 2021-09-15
 */
public interface SysRoleService extends IService<SysRole> {

    public Integer selectMaxSort();

    public List<SysRoleUserVo> getTreeRole(String name);

    ResponseMessage deleteByIds(String ids);

}
