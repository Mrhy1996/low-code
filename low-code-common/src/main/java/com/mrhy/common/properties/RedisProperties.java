package com.mrhy.common.properties;

/**
 * @description: redis配置
 * @author: cooper
 * @date: 2021/7/28 5:24 下午
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redis.local")
@Data
public class RedisProperties {
    private boolean enabled;
}
