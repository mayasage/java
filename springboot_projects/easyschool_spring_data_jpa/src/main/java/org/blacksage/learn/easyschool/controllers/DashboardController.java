package org.blacksage.learn.easyschool.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blacksage.learn.easyschool.models.Person;
import org.blacksage.learn.easyschool.repositories.PersonRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

        private final PersonRepository personRepository;

        @GetMapping({"", "/"})
        public String displayDashboard(Model model,
                                       Authentication authentication,
                                       HttpSession httpSession) {

                Person person = personRepository.findFullByEmail(authentication.getName()).orElseThrow();

                model.addAttribute("username", person.getName());
                model.addAttribute("roles", authentication.getAuthorities().toString());
                if (person.hasEasyClass()) {
                        model.addAttribute("enrolledClass", person.getEasyClassName());
                }

                System.out.println("setting person in session: " + person);
                httpSession.setAttribute("loggedInPerson", person);

                System.out.println("rendering dashboard");

                return "dashboard";
        }
}
