package org.blacksage.learn.microservices.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "EasyBank Accounts microservice REST API Documentation",
                version = "1.0.0",
                contact = @Contact(
                        name = "sage",
                        email = "mayablacksage@proton.me",
                        url = "dummy-url.com"
                ),
                license = @License(
                        name = "Apache License 2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "EasyBank Accounts microservice REST API Documentation",
                url = "dummy-url.com"
        )
)
public class AccountsApplication {

        public static void main(String[] args) {
                SpringApplication.run(AccountsApplication.class, args);
        }

}
