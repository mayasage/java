package org.blacksage.learn.easyschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class EasyschoolApplication {

        public static void main(String[] args) {
                SpringApplication.run(EasyschoolApplication.class, args);
        }

}
