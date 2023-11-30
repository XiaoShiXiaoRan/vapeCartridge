package com.fidnortech.xjx.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fidnortech.xjx.common.UserDetailsInfo;
import com.fidnortech.xjx.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
//@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        UserDetailsInfo userInfo = UserUtil.getUserInfo();
        if(userInfo!=null){

            ObjectWrapper  a = metaObject.getObjectWrapper();
            this.setFieldValByName("createTime",new Date(),metaObject);
            this.setFieldValByName("updateTime",new Date(),metaObject);



            String createBy = (String)metaObject.getValue("createBy");
            if(StringUtils.isEmpty(createBy)){
                this.setFieldValByName("createBy",userInfo.getId(),metaObject);
            }

            this.setFieldValByName("updateBy",userInfo.getId(),metaObject);
            this.setFieldValByName("isDel",0,metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        UserDetailsInfo userInfo = UserUtil.getUserInfo();
        if(userInfo!=null){
            this.setFieldValByName("updateTime",new Date(),metaObject);
            this.setFieldValByName("updateBy",userInfo.getId(),metaObject);
        }

    }
}
