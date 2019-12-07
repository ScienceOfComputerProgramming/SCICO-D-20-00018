package com.gamaza.rest4cep.config;

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

/**
 * Swagger Configuration class
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * Swagger Esper API configuration
     *
     * @return docketConfig
     */
    @Bean
    public Docket swaggerApiEsper() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("REST4CEP Design API")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/design/.*"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Swagger Mongo API configuration
     *
     * @return docketConfig
     */
    @Bean
    public Docket swaggerApiMongo() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("REST4CEP Runtime API")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/runtime/.*"))
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Redirect paths to Swagger UI
     *
     * @param registry **registry**
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
        registry.addRedirectViewController("/api", "/swagger-ui.html");
        registry.addRedirectViewController("/doc", "/swagger-ui.html");
    }

    /**
     * Create API Metadata
     *
     * @return API Metadata
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("REST4CEP (Design y Runtime)")
                .description("API REST for CEP Design, management and runtime")
                .version("1.0")
                .build();
    }

}
