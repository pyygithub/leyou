package com.thtf.gateway.config;


import com.thtf.gateway.properties.CorsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Description:全局跨域资源共享过滤器
 * @Author: panyangyang
 * @Date: 2019/5/20 14:04
 */
@Configuration
public class GlobalCorsConfig {

    @Autowired
    private CorsProperties corsProperties;

    @Bean
    public CorsFilter corsFilter() {

        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1) 允许的域,不要写*，否则cookie就无法使用了
        for (String origin : corsProperties.getAllowedOrigin()) {
            config.addAllowedOrigin(origin);
        }
        //2) 是否发送Cookie信息
        config.setAllowCredentials(true);
        //3) 允许的请求方式
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // 4）允许的头信息
        config.addAllowedHeader("*");
        // 5）本次许可的有效时长，单位是秒，过期之前的ajax请求就无需再次进行预检了
        config.setMaxAge(corsProperties.getMaxAge());

        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}