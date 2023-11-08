package com.fidnortech.xjx;

import com.fidnortech.xjx.aspect.LogRecordAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @Author: xjx
 * @CreateTime: 2023-11-08  10:09
 */

@SpringBootApplication
@MapperScan("com.fidnortech.xjx.*.mapper")
@EnableAspectJAutoProxy
@Import(LogRecordAspect.class)
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringbootApplication.class);
        application.run();

    }

}
