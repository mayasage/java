/**
 * Requires web flux starter
 */

package org.blacksage.learn.easyschoolapiconsumer.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration

public class ContactWebClientConfiguration {

        @Value("${easyschool.api.auth.admin.username}")
        private String adminUsername;

        @Value("${easyschool.api.auth.admin.password}")
        private String adminPassword;

        @Bean("contactWebClient")
        public WebClient webClient() {
                return WebClient.builder()
                        .filter(
                                ExchangeFilterFunctions.basicAuthentication(
                                        adminUsername,
                                        adminPassword
                                )
                        )
                        .build();
        }
}
