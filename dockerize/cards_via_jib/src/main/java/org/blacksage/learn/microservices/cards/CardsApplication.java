package org.blacksage.learn.microservices.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
public class CardsApplication {

        public static void main(String[] args) {
                SpringApplication.run(CardsApplication.class, args);
        }

}
