package com.mrhy.server;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cooper
 * @description
 * @date 2021/8/31 11:17 下午
 */
@SpringBootApplication
@EnableDubbo
@MapperScan("com.mrhy.server.mapper")
public class ServerApplication {
    public static void main(String[] args) {


        SpringApplication.run(ServerApplication.class, args);
    }

}
