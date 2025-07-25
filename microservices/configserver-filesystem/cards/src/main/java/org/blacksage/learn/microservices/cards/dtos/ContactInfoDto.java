package org.blacksage.learn.microservices.cards.dtos;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "cards")
public record ContactInfoDto(String message,
                             Map<String ,String> contact,
                             List<String> onCallSupport) {
}
