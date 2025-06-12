package org.blacksage.learn.easyschoolapiconsumer.openfeign;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactProxy2Configuration {

        @Value("${easyschool.api.auth.sage.username}")
        private String sageUsername;

        @Value("${easyschool.api.auth.sage.password}")
        private String sagePassword;

        @Bean("contactProxy2RequestInterceptor")
        public RequestInterceptor requestInterceptor() {
                return new BasicAuthRequestInterceptor(
                        sageUsername,
                        sagePassword
                );
        }
}
