package com.mrhy.common.resttemplate;

import com.mrhy.common.properties.RestTemplateProperties;
import com.mrhy.common.properties.SwaggerProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: cooper
 * @date: 2021/7/28 7:52 下午
 */
@Configuration
@Import(RestTemplateUtils.class)
@EnableConfigurationProperties(RestTemplateProperties.class)
@ConditionalOnProperty(prefix = "rest",value = "enabled",havingValue = "true",matchIfMissing = true)
@Log4j2
public class RestTemplateAutoConfiguration {
    public RestTemplateAutoConfiguration() {
        log.info("=======RestTemplateAutoConfiguration init======");
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);//ms
        factory.setConnectTimeout(15000);//ms
        return factory;
    }
}
