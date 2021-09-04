package com.mrhy.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author cooper
 * @description
 * @date 2021/8/30 12:28 上午
 */
@ConfigurationProperties(prefix = "rest")
@Data
public class RestTemplateProperties {
    private Boolean enabled;
}
