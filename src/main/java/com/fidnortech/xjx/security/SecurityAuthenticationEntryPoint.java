package com.fidnortech.xjx.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : LCheng
 * @date : 2020-11-26 16:58
 * description : 认证失败处理类
 */
@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //根据系统要求开发功能代码
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token认证失败。");
    }
}
