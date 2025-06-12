package org.blacksage.learn.easyschooladmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class EasyschoolAdminApplication {

        public static void main(String[] args) {
                SpringApplication.run(EasyschoolAdminApplication.class, args);
        }

}
