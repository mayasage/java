package org.blacksage.learn.microservices.loans;

import org.blacksage.learn.microservices.loans.dtos.ContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@EnableConfigurationProperties(ContactInfoDto.class)
public class LoansApplication {

        public static void main(String[] args) {
                SpringApplication.run(LoansApplication.class, args);
        }

}
