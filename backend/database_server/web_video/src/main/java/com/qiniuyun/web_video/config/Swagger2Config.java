package com.qiniuyun.web_video.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2Config {
    @Value("${swagger.enable}")
    private boolean enableSwagger;
    @Value("${swagger.serviceUrl}")
    private String serviceUrl;
    @Value("${swagger.contact}")
    private String contact;
    @Value("${swagger.title}")
    private String title;
    @Value("${swagger.version}")
    private String version;
    @Value("${swagger.basePackage}")
    private String basePackage;
    @Value("${swagger.description}")
    private String description;
    @Bean
    public Docket createRestApi() {


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(serviceUrl)
                .contact(contact)
                .version(version)
                .build();
    }
}
