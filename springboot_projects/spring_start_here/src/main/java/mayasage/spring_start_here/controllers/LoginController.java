package mayasage.spring_start_here.controllers;

import mayasage.spring_start_here.models.LoginProcessor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Data
public class LoginController {
        private final LoginProcessor loginProcessor;

        @GetMapping("/login")
        public String getLoginPage() {
                return "login";
        }

        @PostMapping("/login")
        public String login(
                @RequestParam String username,
                @RequestParam String password,
                Model page
        ) {
                loginProcessor.setUsername(username);
                loginProcessor.setPassword(password);
                if (loginProcessor.login()) {
                        page.addAttribute("username", username);
                        return "redirect:/user-profile";
                } else {
                        page.addAttribute("message", "Login failed.");
                        return "login";
                }
        }
}
