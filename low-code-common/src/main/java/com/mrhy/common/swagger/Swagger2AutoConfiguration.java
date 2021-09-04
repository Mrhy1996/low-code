package com.mrhy.common.swagger;

import com.mrhy.common.properties.SwaggerProperties;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description: swagger3配置
 * @author: cooper
 * @date: 2021/7/20 5:09 下午
 */
@Configuration
@Log4j2
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(prefix = "swagger", value = "enabled", havingValue = "true")
@ConditionalOnWebApplication
public class Swagger2AutoConfiguration {

    private final SwaggerProperties properties;

    public Swagger2AutoConfiguration(SwaggerProperties swaggerProperties) {
        this.properties = swaggerProperties;
    }

    /**
     * @description swagger配置
     * @author cooper
     * @date 2021/7/24 12:00 上午
     */
    @Bean
    @ConditionalOnMissingBean(Docket.class)
    public Docket createRestApi() {
        log.info("==========swagger init==========");
        return new Docket(DocumentationType.SWAGGER_2)// 注意此处改动，需要将SWAGGER_2改成OAS_30
                .enable(this.properties.getEnabled())
                .apiInfo(apiInfo()).select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(this.properties.getTitle())
                .description(this.properties.getDescription())
                .version(this.properties.getVersion())
                .build();
    }


}
