package com.laura.lauraspringboot.services;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
@Getter
public class LoginCountService {
        private int loginCount;

        public void increment() {
                loginCount++;
        }
}
