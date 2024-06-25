package com.ioomex.youleoffice.config.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * SwaggerConfig
 * <p>
 * 配置 Swagger 文档
 * Swagger 提供了 API 文档和测试的用户界面。
 * 这个配置类设置了文档的基本信息和扫描路径。
 *
 * @author ioomex
 * @since 2024-06-11
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 是否启用 Swagger 文档的标志
    @Value("${managementSwagger.openFlag}")
    private Boolean openFlag;

    // Swagger 文档的标题
    @Value("${managementSwagger.title}")
    private String title;

    // Swagger 文档的描述
    @Value("${managementSwagger.description}")
    private String description;

    // Swagger 文档的版本
    @Value("${managementSwagger.version}")
    private String version;

    // Swagger 文档的许可证信息
    @Value("${managementSwagger.license}")
    private String apacheLicense;

    /**
     * 创建 Swagger 的 Docket Bean
     *
     * @return 配置好的 Docket 实例
     */
    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        configureApiInfo(docket);
        docket = configureApiScan(docket).enable(openFlag);
        configureApiToken(docket);
        return docket;
    }

    /**
     * 配置 接口 JWT
     *
     * @param docket Docket Bean
     */
    private void configureApiToken(Docket docket) {
        // 创建一个 ApiKey，名为 "token"，它位于 HTTP 请求的 header 中
        ApiKey apiKey = new ApiKey("token", "token", "header");
        List<ApiKey> apiKeys = List.of(apiKey);
        docket.securitySchemes(apiKeys);

        // 创建一个授权范围，名称为 "global"，描述为 "accessEverything"
        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = {scope};

        // 创建一个 SecurityReference，将上面的 ApiKey 和 AuthorizationScope 关联起来
        SecurityReference reference = new SecurityReference("token", authorizationScopes);
        List<SecurityReference> references = List.of(reference);

        // 创建一个 SecurityContext，将 SecurityReference 列表设置进去
        SecurityContext securityContext = SecurityContext.builder().securityReferences(references).build();
        List<SecurityContext> securityContexts = List.of(securityContext);
        docket.securityContexts(securityContexts);
    }

    /**
     * 配置 API 扫描，指定哪些接口需要展示，哪些接口不需要展示。
     *
     * @param docket 文档配置
     * @return 配置好的 Docket 实例
     */
    private static Docket configureApiScan(Docket docket) {
        return docket.select()
          .paths(PathSelectors.any())
//          .apis(RequestHandlerSelectors.withClassAnnotation(StartSwaggerScan.class))
          // 如果需要基于方法注解进行扫描，可以使用下面这行代码替代上面的扫描配置
          .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
          .build();
    }

    /**
     * 配置 Swagger 文档的基本信息
     *
     * @param docket 通过 ApiInfoBuilder 配置文档基本信息
     */
    private void configureApiInfo(Docket docket) {
        ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title(title)
          .description(description)
          .version(version)
          .license(apacheLicense);
        docket.apiInfo(builder.build());
    }
}