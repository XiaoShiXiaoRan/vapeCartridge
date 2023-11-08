package com.fidnortech.xjx.system.mapper;

import com.fidnortech.xjx.system.entity.SysMenu;
import com.fidnortech.xjx.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fidnortech.xjx.system.entity.vo.SysRoleUserVo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linyq
 * @since 2021-09-15
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    public Integer selectMaxSort();

    List<SysRoleUserVo> getTreeRole(@Param("name") String name);

}
