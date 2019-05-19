package com.thtf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description: 商品微服务
 * @Author: panyangyang
 * @Date: 2019/5/16 16:57
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.thtf.item.mapper")
public class LyItemServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyItemServiceApplication.class, args);
    }
}
