package com.thtf.gateway.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:跨域请求配置
 * @Author: panyangyang
 * @Date: 2019/5/20 14:07
 */
@Component
@ConfigurationProperties(value = "cors")
@Data
public class CorsProperties {

    /** 本次许可的有效时长，单位是秒 */
    private Long maxAge = 3600l;

    /** 允许的域 */
    private List<String> allowedOrigin;
}
