package org.blacksage.learn.easyschool.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blacksage.learn.easyschool.dtos.Profile;
import org.blacksage.learn.easyschool.models.Person;
import org.blacksage.learn.easyschool.repositories.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller("profileControllerBean-because-its-clashing-with-HALExplorer")
@RequiredArgsConstructor

public class ProfileController {

        private final PersonRepository personRepository;

        @PostMapping("/updateProfile")
        public String updateProfile(@Valid @ModelAttribute("profile") Profile profile,
                                    Errors errors,
                                    HttpSession httpSession) {

                if (errors.hasErrors()) {
                        return "profile";
                }

                Person person = (Person) httpSession.getAttribute("loggedInPerson");
                person.mergeProfile(profile);

                personRepository.save(person);

                Assert.isTrue(
                        person ==
                                httpSession.getAttribute("loggedInPerson"),
                        "Session doesn't contain reference to the person."
                );

                return "redirect:/displayProfile";
        }

        @GetMapping("/displayProfile")
        public String displayProfile(Model model,
                                     HttpSession httpSession) {

                Person person = (Person) httpSession.getAttribute("loggedInPerson");

                Profile profile = new Profile(person);

                model.addAttribute("profile", profile);

                return "profile";
        }
}
