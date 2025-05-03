package com.blacksage.flyway1;

import com.blacksage.flyway1.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Flyway1Application implements CommandLineRunner {

        private final BookRepository bookRepository;

        public static void main(String[] args) {
                SpringApplication.run(Flyway1Application.class, args);
        }

        @Override
        public void run(String ... args) throws Exception {
                bookRepository.findAll().forEach(System.out::println);
        }
}
