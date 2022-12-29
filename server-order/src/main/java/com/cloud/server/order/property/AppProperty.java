package com.cloud.server.order.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "sale")
public class AppProperty {
    private String productName;
    private Integer maxNum;
}
