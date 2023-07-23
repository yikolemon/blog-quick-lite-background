package com.yikolemon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwagerrConfiguration {
    private static ApiInfo DEFAULT = null;
    @Bean
    public Docket docket(){
        DEFAULT = new ApiInfo("blog quick lite",
                "Api Documentation",
                "V-1.0",
                null,
                null,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT)
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.example.demo2.controller"))//按照包名扫描
                .apis(RequestHandlerSelectors.any()) //全部扫面
                //.apis(RequestHandlerSelectors.none())不扫面
                // .paths(PathSelectors.ant("controller"))//过滤指定包下的接口
                .build();
    }
}
