package com.laura.lauraspringboot;

import com.laura.lauraspringboot.controllers.LoginController;
import com.laura.lauraspringboot.models.LoginProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
public class LoginControllerUnitTests {
        @Mock
        private Model model;

        @Mock
        private LoginProcessor loginProcessor;

        @InjectMocks
        private LoginController loginController;

        @Test
        public void postLoginSuccessHappyFlow() {
                BDDMockito.given(loginProcessor.login()).willReturn(true);

                String result = loginController.login("username", "password", model);

                Assertions.assertEquals("redirect:/user-profile", result);
                BDDMockito.verify(model).addAttribute("username", "username");
        }

        @Test
        public void postLoginFailureHappyFlow() {
                BDDMockito.given(loginProcessor.login()).willReturn(false);

                String result = loginController.login("username", "password", model);

                Assertions.assertEquals("login", result);
                BDDMockito.verify(model).addAttribute("message", "Login failed.");
        }
}
