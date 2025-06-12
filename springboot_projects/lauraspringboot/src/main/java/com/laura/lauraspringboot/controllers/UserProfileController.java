package com.laura.lauraspringboot.controllers;

import com.laura.lauraspringboot.services.LoggedUserManagementService;
import com.laura.lauraspringboot.services.LoginCountService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Data
public class UserProfileController {
        private final LoggedUserManagementService loggedUserManagementService;
        private final LoginCountService loginCountService;

        @GetMapping("/user-profile")
        public String showUserProfile(
                @RequestParam(required = false) String logout,
                Model page
        ) {
                if (logout != null) {
                        loggedUserManagementService.setUsername(null);
                        return "redirect:/login";
                }
                String username = loggedUserManagementService.getUsername();
                if (username == null) {
                        page.addAttribute("message", "You are not logged in");
                        return "redirect:/login";
                } else {
                        page.addAttribute("username", username);
                        page.addAttribute("loginAttemptNumber", loginCountService.getLoginCount());
                        return "user-profile";
                }
        }
}
