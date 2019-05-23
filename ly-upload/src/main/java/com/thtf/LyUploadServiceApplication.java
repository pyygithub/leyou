package com.thtf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 文件上传启动类
 * @author: pyy
 * @date: 2019/5/23 9:23
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LyUploadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyUploadServiceApplication.class, args);
    }
}
