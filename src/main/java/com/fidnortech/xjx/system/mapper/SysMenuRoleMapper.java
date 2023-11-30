package com.fidnortech.xjx.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fidnortech.xjx.system.entity.SysMenu;
import com.fidnortech.xjx.system.entity.SysMenuRole;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @Entity
 */
public interface SysMenuRoleMapper extends BaseMapper<SysMenuRole> {

    List<SysMenu> getMenuByRoleId(@Param("id") String id);
}




