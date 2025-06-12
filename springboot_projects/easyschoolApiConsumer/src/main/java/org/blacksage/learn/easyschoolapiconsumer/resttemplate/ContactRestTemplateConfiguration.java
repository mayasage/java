/**
 * SpringBoot contains RestTemplate.
 */

package org.blacksage.learn.easyschoolapiconsumer.resttemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ContactRestTemplateConfiguration {

        @Value("${easyschool.api.auth.admin.username}")
        private String adminUsername;

        @Value("${easyschool.api.auth.admin.password}")
        private String adminPassword;

        @Bean(name = "contactRestTemplate")
        public RestTemplate restTemplate() {
                return new RestTemplateBuilder()
                        .basicAuthentication(adminUsername, adminPassword)
                        .build();
        }
}
