package org.blacksage.learn.microservices.accounts.dtos;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "contact")
public record ContactInfoDto(String message,
                             Map<String ,String> contact,
                             List<String> onCallSupport) {
}
