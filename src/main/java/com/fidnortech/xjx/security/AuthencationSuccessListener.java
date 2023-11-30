package com.fidnortech.xjx.security;


import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;


@Component
public class AuthencationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {



    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {




    }
}
