package com.fidnortech.xjx.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : LCheng
 * @date : 2020-11-26 16:56
 * description : 无访问权限处理类
 */
@Component
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //根据系统要求开发功能代码
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "您的权限不足。");
    }
}
