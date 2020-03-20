package com.gamaza.rest4cep.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.gamaza.rest4cep.config.constant.SwaggerConstants.*;

/**
 * Swagger Configuration class
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * Swagger Design API configuration
     */
    @Bean
    public Docket swaggerApiDesign() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(SWAGGER_GROUP_NAME_DESIGN)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/design/.*"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Swagger Runtime API configuration
     */
    @Bean
    public Docket swaggerApiRuntime() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(SWAGGER_GROUP_NAME_RUNTIME)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/runtime/.*"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Redirect paths to Swagger UI
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", SWAGGER_UI_URL);
        registry.addRedirectViewController("/api", SWAGGER_UI_URL);
        registry.addRedirectViewController("/doc", SWAGGER_UI_URL);
    }

    /**
     * API Metadata
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SWAGGER_API_INFO_TITLE)
                .description(SWAGGER_API_INFO_DESCRIPTION)
                .version(SWAGGER_API_INFO_VERSION)
                .build();
    }

}
