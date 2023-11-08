package com.fidnortech.xjx.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import  org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import  springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import  springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"dev"})
public class SwaggerConfig  {

    @Bean
    public Docket createRestApi() {

        return (new Docket(DocumentationType.SWAGGER_2)).pathMapping("/").
                select().apis(RequestHandlerSelectors.basePackage("com.fidnortech.xjx")).
                paths(PathSelectors.any()).build()
                .apiInfo((new ApiInfoBuilder()).title("海南数字乡村项目接口文档").description("详细信息").version("1.0").build());
    }

}