package com.polarbookshop.catalogservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
    private String greeting;
}
