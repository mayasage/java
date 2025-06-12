package org.blacksage.learn.easyschoolapiconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.blacksage.learn.easyschoolapiconsumer.openfeign")
public class EasyschoolApiConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyschoolApiConsumerApplication.class, args);
	}

}
