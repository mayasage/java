package org.blacksage.learn.easyschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class EasyschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyschoolApplication.class, args);
	}

}
