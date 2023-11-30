package com.fidnortech.xjx.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.fidnortech.xjx.common.ResponseMessage;
import com.fidnortech.xjx.common.UserDetailsInfo;
import com.fidnortech.xjx.utils.RsaUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private JwtUtil jwtUtil = new JwtUtil();

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String password = request.getParameter("password");
        String s = RsaUtils.decryptByPrivateKey(RsaUtils.s, password);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getParameter("username"),s));
    }

    /**
     * 验证【成功】后调用的方法
     * 若验证成功 生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {

        UserDetailsInfo user= (UserDetailsInfo) authResult.getPrincipal();
        // 创建Token
        String token = jwtUtil.generate(user);
        // 设置编码 防止乱码问题
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setContentType("text/json;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("access_token",token);
        jsonObject.put("token_type","bearer");
        jsonObject.put("userid",user.getId()+"");
        jsonObject.put("username",user.getUsername());
        jsonObject.put("expires_in",JwtUtil.validateSecond);
        jsonObject.put("scope","userInfo");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(ResponseMessage.success(jsonObject)));
    }

    /**
     * 验证【失败】调用的方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        String returnData="";
        // 账号过期
        if (failed instanceof AccountExpiredException) {
            returnData="账号过期";
        }
        // 密码错误
        else if (failed instanceof BadCredentialsException) {
            returnData="密码错误";
        }
        // 密码过期
        else if (failed instanceof CredentialsExpiredException) {
            returnData="密码过期";
        }
        // 账号不可用
        else if (failed instanceof DisabledException) {
            returnData="账号不可用";
        }
        //账号锁定
        else if (failed instanceof LockedException) {
            returnData="账号锁定";
        }
        // 用户不存在
        else if (failed instanceof InternalAuthenticationServiceException) {
            returnData="用户不存在";
        }
        // 其他错误
        else{
            returnData="未知异常";
        }
        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(ResponseMessage.error(returnData)));
    }
}
