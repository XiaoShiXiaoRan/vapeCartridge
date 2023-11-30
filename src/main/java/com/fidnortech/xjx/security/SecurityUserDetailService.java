package com.fidnortech.xjx.security;


import com.fidnortech.xjx.common.UserDetailsInfo;
import com.fidnortech.xjx.system.entity.SysUser;
import com.fidnortech.xjx.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 用户登录操作
 *
 * @author lCheng
 */
@Component
public class SecurityUserDetailService implements UserDetailsService {


    @Autowired
    private SysUserService sysUserService;


    @Override
    public UserDetailsInfo loadUserByUsername(String accountName) throws UsernameNotFoundException {

        SysUser sysUser = this.sysUserService.findUserByName(accountName);
        if(sysUser==null && sysUser.getIsDel() == 1){
            throw new UsernameNotFoundException("用户名不存在");
        }
        UserDetailsInfo userDetailsInfo = new UserDetailsInfo();
        userDetailsInfo.setId(sysUser.getId());
        userDetailsInfo.setUsername(sysUser.getUserName());
        userDetailsInfo.setPassword(sysUser.getPassword());

        userDetailsInfo.setAuthorities(AuthorityUtils.createAuthorityList("admin"));

        return userDetailsInfo;
    }
}
