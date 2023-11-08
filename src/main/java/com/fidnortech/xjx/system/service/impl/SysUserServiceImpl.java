package com.fidnortech.xjx.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.system.entity.SysRole;
import com.fidnortech.xjx.system.entity.SysUser;
import com.fidnortech.xjx.system.entity.vo.SysUserRoleVo;
import com.fidnortech.xjx.system.entity.vo.UserRoleVo;
import com.fidnortech.xjx.system.mapper.SysUserMapper;
import com.fidnortech.xjx.system.service.SysRoleService;
import com.fidnortech.xjx.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author linyq
 * @since 2021-08-11
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public SysUser findUserByName(String userName) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        sysUser.setIsDel(0);
        return this.getOne(new QueryWrapper(sysUser));
    }

    @Override
    public ResponseMessage getUserById(String id) {
        SysUserRoleVo retObj = sysUserMapper.getbyId(id);
        if (retObj != null) {
            retObj.setPassword(null);
            return   ResponseMessage.success(retObj);
        }else{
            return ResponseMessage.error("请求错误。");
        }
    }

    @Override
    @Cacheable(value = "sysUser" ,key = "targetClass + methodName")
    public Map<String,SysUser> getUserMap(){
        List<SysUser> list = this.list();
        Map<String,SysUser> resultMap = new HashMap<>();
        for(SysUser sysUser:list){
            resultMap.put(sysUser.getId()+"",sysUser);
        }
        return resultMap;
    }

    @Override
    public ResponseMessage getUserListPage(Page page, SysUser sysUser  ) {
        return ResponseMessage.success(sysUserMapper.getUserListPage(page,  sysUser  ));
    }

    @Override
    public List<UserRoleVo> getUserRolePage(String name, Long pageNum,Integer pageSize) {
        return sysUserMapper.getUserRolePage(name,pageNum,pageSize);
    }


}
