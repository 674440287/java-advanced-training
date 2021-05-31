package com.yb.dubbo.api.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Knife4j的Swagger配置
 *
 * @author yangbiao
 * @date 2021/5/17
 */
@Profile({"dev", "test"})
@Configuration
@EnableKnife4j
public class Knife4jSwaggerConfig {
    public static final String KNIFE4J_PERMIT = "/swagger-resources,/v3/api-docs,/doc.html";

    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        // 是否启用Swagger(默认开启)
        return new Docket(DocumentationType.OAS_30)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                // .apis(RequestHandlerSelectors.basePackage("com.xxx"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                /* 设置安全模式，swagger可以设置访问token */
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                //设置请求的统一前缀(swagger里会自动给请求添加url,这里不添加,直接使用接口url)
                .pathMapping("");
    }

    /**
     * 安全模式，这里指定token通过Authorization头请求头传递
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        return apiKeyList;
    }

    /**
     * 安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                //实测匹配该正则请求会自动添加请求头拦击,必须有token的请求头才能发送请求
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());
        return securityContexts;
    }

    /**
     * 默认的安全上引用
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }

    /**
     * 添加摘要信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot项目 后台服务API接口文档")
                .description("使用 knife4j 搭建的后台服务API接口文档")
                .termsOfServiceUrl("http://localhost:8080/")
                .contact(new Contact("yb", "", "yb@163.com"))
                .version("1.0.0")
                .build();
    }
}
