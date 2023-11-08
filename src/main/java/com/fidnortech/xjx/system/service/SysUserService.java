package com.fidnortech.xjx.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fidnortech.xjx.system.entity.vo.UserRoleVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author linyq
 * @since 2021-08-11
 */
public interface SysUserService extends IService<SysUser> {

    SysUser findUserByName(String userName);

    ResponseMessage getUserById(String id);


    Map<String,SysUser> getUserMap();

    ResponseMessage getUserListPage(Page page,  SysUser sysUser );

    List<UserRoleVo> getUserRolePage(String name, Long pageNum, Integer pageSize);
}
