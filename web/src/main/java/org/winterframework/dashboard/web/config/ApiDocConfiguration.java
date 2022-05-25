package org.winterframework.dashboard.web.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Profile("dev")
@Configuration
@EnableSwagger2
public class ApiDocConfiguration {
    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.winterframework.dashboard"))
                .paths(PathSelectors.any())
                .build().securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Winter Dashboard API")//文档顶栏
                .description("Winter Dashboard API") //描述
                .version("1.0") //版本
                .contact(new Contact("Winter Studio","","")) //作者
                .build();
    }

    private List<SecurityScheme> securitySchemes(){
        List<SecurityScheme> list = new ArrayList<>();
        list.add(new ApiKey("BearerToken", "Authorization", "header"));
        return list;
    }

}
