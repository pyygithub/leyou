package com.thtf.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: Eureka 注册中心
 * @Author: panyangyang
 * @Date: 2019/5/16 15:50
 */
@SpringBootApplication
@EnableEurekaServer
public class LyEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyEurekaApplication.class, args);
    }
}
