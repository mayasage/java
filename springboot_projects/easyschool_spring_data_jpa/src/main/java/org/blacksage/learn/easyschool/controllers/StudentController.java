package org.blacksage.learn.easyschool.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blacksage.learn.easyschool.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

        @GetMapping("/displayCourses")
        public String displayCourses(HttpSession httpSession, Model model) {
                Person person = (Person) httpSession.getAttribute("loggedInPerson");
                System.out.println("person in session: " + person);
                model.addAttribute("person", person);
                return "courses_enrolled";
        }
}
