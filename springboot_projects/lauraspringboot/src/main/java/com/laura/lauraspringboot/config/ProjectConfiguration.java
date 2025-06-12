package com.laura.lauraspringboot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.sql.DataSource;

@Configuration
@EnableFeignClients(basePackages = "com.laura.lauraspringboot.proxies")
public class ProjectConfiguration {
        @Value("${custom.spring.datasource.url}")
        private String customDatasourceUrl;

        @Value("${custom.spring.datasource.username}")
        private String customDatasourceUsername;

        @Value("${custom.spring.datasource.password}")
        private String customDatasourcePassword;

//        @Bean
//        public DataSource datasource() {
//                HikariDataSource dataSource = new HikariDataSource();
//                dataSource.setJdbcUrl(customDatasourceUrl);
//                dataSource.setUsername(customDatasourceUsername);
//                dataSource.setPassword(customDatasourcePassword);
//                dataSource.setConnectionTimeout(1000);
//                return dataSource;
//        }

        @Bean
        public RestTemplate restTemplate() {
                return new RestTemplate();
        }

        @Bean
        public WebClient webClient() {
                return WebClient.builder().build();
        }
}
