/**
 * Requires feign client starter
 */

package org.blacksage.learn.easyschoolapiconsumer.openfeign;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactProxyConfiguration {

        @Value("${easyschool.api.auth.admin.username}")
        private String adminUsername;

        @Value("${easyschool.api.auth.admin.password}")
        private String adminPassword;

        @Bean("contactProxyRequestInterceptor")
        public RequestInterceptor requestInterceptor() {
                return new BasicAuthRequestInterceptor(
                        adminUsername,
                        adminPassword
                );
        }
}
