package com.thtf.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Description: 网关服务
 * @Author: panyangyang
 * @Date: 2019/5/16 15:57
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class LyApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyApiGatewayApplication.class, args);
    }
}
