package org.blacksage.learn.easyschool.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

        @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
        public String displayLoginPage(@RequestParam(required = false) boolean error,
                                       @RequestParam(required = false) boolean logout,
                                       Model model) {

                log.info("error: {}, logout: {}", error, logout);

                String errorMessage = null;
                if (error) {
                        errorMessage = "Invalid username or password";
                }
                if (logout) {
                        errorMessage = "You have been logged out successfully";
                }
                model.addAttribute("errorMessage", errorMessage);

                return "login";
        }

        @GetMapping("/logout")
        public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                log.info("auth: {}", auth);
                log.info("context: {}", SecurityContextHolder.getContext());
                if (auth != null) {
                        new SecurityContextLogoutHandler().logout(request, response, auth);
                }
                return "redirect:/login?logout=true";
        }
}
