package com.fidnortech.xjx.utils;


import com.fidnortech.xjx.base.UserVO;
import com.fidnortech.xjx.common.UserDetailsInfo;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {


    public static UserDetailsInfo getUserInfo(){
        UserDetailsInfo userDetails = null;
       try {
           Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           if(!principal.equals("anonymousUser")){
               userDetails = (UserDetailsInfo)principal;
           }
       }catch (Exception e){

       }


        return userDetails;
    }

    public static UserVO getUser(){
        UserVO userVO = new UserVO();
        UserDetailsInfo userDetails = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!principal.equals("anonymousUser")){
            userDetails = (UserDetailsInfo)principal;
            userVO.setId(userDetails.getId());
            userVO.setUserName(userDetails.getUsername());
        }
        return userVO;
    }

}
