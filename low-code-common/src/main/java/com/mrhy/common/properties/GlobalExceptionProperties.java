package com.mrhy.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: 全局拦截配置
 * @author: cooper
 * @date: 2021/7/23 10:28 下午
 */
@ConfigurationProperties(prefix = "global.exception")
@Data
public class GlobalExceptionProperties {
    public boolean enabled;
}
