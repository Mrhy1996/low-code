package com.mrhy.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: swagger配置
 * @author: cooper
 * @date: 2021/7/23 11:29 下午
 */
@Data
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private Boolean enabled;
    private String title;
    private String description;
    private String version;
}
