package org.blacksage.learn.easyschool.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blacksage.learn.easyschool.constants.RoleNameConstants;
import org.blacksage.learn.easyschool.models.Person;
import org.blacksage.learn.easyschool.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")

@RequiredArgsConstructor

@Slf4j

public class PublicController {

        private final PersonService personService;

        @GetMapping("/register")
        public String register(Model model) {

                model.addAttribute("person", new Person());
                return "register";
        }

        @PostMapping("/createUser")
        public String createUser(@Valid @ModelAttribute("person") Person person,
                                 Errors errors) {

                if (errors.hasErrors()) {
                        return "register";
                }

                personService.createNewPerson(person, RoleNameConstants.STUDENT);

                return "redirect:/login?register=true";
        }
}
