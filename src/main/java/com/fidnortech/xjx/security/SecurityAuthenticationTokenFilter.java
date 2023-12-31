package com.fidnortech.xjx.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class SecurityAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        String token = JwtUtil.getToken(httpServletRequest);
        if (StringUtils.hasText(token)) {
            UsernamePasswordAuthenticationToken authentication = JwtUtil.getAuthentication(token);


            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
         filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}

