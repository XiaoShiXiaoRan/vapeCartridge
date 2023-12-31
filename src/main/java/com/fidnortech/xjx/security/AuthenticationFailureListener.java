package com.fidnortech.xjx.security;




import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.Component;

import java.util.Date;


@Slf4j
@Component
public class AuthenticationFailureListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {


    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        String message;
        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            //提供的凭据是错误的，用户名或者密码错误
            message = "提供的凭据是错误的，用户名或者密码错误";
        } else if (event instanceof AuthenticationFailureCredentialsExpiredEvent) {
            //验证通过，但是密码过期
            message = "验证通过，但是密码过期";
        } else if (event instanceof AuthenticationFailureDisabledEvent) {
            //验证过了但是账户被禁用
            message = "验证过了但是账户被禁用";
        } else if (event instanceof AuthenticationFailureExpiredEvent) {
            //验证通过了，但是账号已经过期
            message = "验证通过了，但是账号已经过期";
        } else if (event instanceof AuthenticationFailureLockedEvent) {
            //账户被锁定
            message = "账户被锁定";
        } else if (event instanceof AuthenticationFailureProviderNotFoundEvent) {
            //配置错误，没有合适的AuthenticationProvider来处理登录验证
            message = "配置错误";
        } else if (event instanceof AuthenticationFailureProxyUntrustedEvent) {
            // 代理不受信任，用于Oauth、CAS这类三方验证的情形，多属于配置错误
            message = "代理不受信任";
        } else if (event instanceof AuthenticationFailureServiceExceptionEvent) {
            // 其他任何在AuthenticationManager中内部发生的异常都会被封装成此类
            message = "内部发生的异常";
        } else {
            message = "其他未知错误";
        }





    }


}
