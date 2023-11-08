package com.fidnortech.xjx.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fidnortech.xjx.system.entity.SysUser;
import com.fidnortech.xjx.system.entity.vo.SysUserRoleVo;
import com.fidnortech.xjx.system.entity.vo.SysUserVO;
import com.fidnortech.xjx.system.entity.vo.UserRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author linyq
 * @since 2021-08-11
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUserRoleVo getbyId(@Param("id") String id);

    IPage<SysUserVO> getUserListPage(Page page, @Param("sysUser") SysUser sysUser);

    List<UserRoleVo> getUserRolePage(String name, Long pageNum, Integer pageSize);
}
