package com.cloud.nacos.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Data
@Component
@Configuration
@ConfigurationProperties("app")
public class AppConfig {

    private Integer test;
    private Integer test2;

}


