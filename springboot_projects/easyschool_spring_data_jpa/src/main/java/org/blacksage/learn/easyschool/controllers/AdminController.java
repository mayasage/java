package org.blacksage.learn.easyschool.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blacksage.learn.easyschool.models.Course;
import org.blacksage.learn.easyschool.models.EasyClass;
import org.blacksage.learn.easyschool.models.Person;
import org.blacksage.learn.easyschool.repositories.CourseRepository;
import org.blacksage.learn.easyschool.repositories.EasyClassRepository;
import org.blacksage.learn.easyschool.repositories.PersonRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@Controller
@RequestMapping("/admin")

public class AdminController {

        private final EasyClassRepository easyClassRepository;
        private final PersonRepository personRepository;
        private final CourseRepository courseRepository;

        @GetMapping("/displayClasses")
        public String displayClasses(Model model) {

                List<EasyClass> easyClasses = easyClassRepository.findAll();

                model.addAttribute("eazyClass", new EasyClass());
                model.addAttribute("eazyClasses", easyClasses);

                return "classes";
        }

        @PostMapping("/addNewClass")
        public String addNewClass(@Valid @ModelAttribute("eazyClass") EasyClass eazyClass) {

                easyClassRepository.save(eazyClass);

                return "redirect:/admin/displayClasses";
        }

        @GetMapping("/deleteClass")
        public String deleteClass(@RequestParam Long id) {

                EasyClass easyClass = easyClassRepository.findById(id).orElseThrow();

                easyClass
                        .getStudents()
                        .forEach(student -> {
                                student.setEasyClass(null);
                                personRepository.save(student);
                        });

                easyClassRepository.deleteById(id);

                return "redirect:/admin/displayClasses";
        }

        @GetMapping("/displayStudents")
        public String displayStudents(@RequestParam(value = "id") Long id,
                                      @RequestParam(value = "error", required = false) boolean error,
                                      Model model,
                                      HttpSession httpSession) {

                System.out.println("error: " + error);

                EasyClass easyClass = easyClassRepository.findById(id).orElseThrow();

                model.addAttribute("eazyClass", easyClass);
                model.addAttribute("person", new Person());

                if (error) {
                        model.addAttribute("errorMessage", "Invalid email received.");
                }

                httpSession.setAttribute("eazyClass", easyClass);

                return "students";
        }

        @PostMapping("/addStudent")
        public String addStudent(@ModelAttribute("person") Person person,
                                 HttpSession httpSession) {

                EasyClass easyClass = (EasyClass) httpSession.getAttribute("eazyClass");

                Optional<Person> personInDB =
                        personRepository.findByEmail(person.getEmail());

                if (personInDB.isEmpty()) {
                        return "redirect:/admin/displayStudents?id="
                                + easyClass.getId()
                                + "&error=true";
                }

                boolean isNotExist = easyClass.addStudent(personInDB.get());
                if (!isNotExist) {
                        return "redirect:/admin/displayStudents?id="
                                + easyClass.getId()
                                + "&error=true";
                }
                easyClassRepository.save(easyClass);

                return "redirect:/admin/displayStudents?id="
                        + easyClass.getId();
        }

        @GetMapping("/deleteStudent")
        public String deleteStudent(@RequestParam Long id,
                                    HttpSession httpSession) {

                EasyClass easyClass =
                        (EasyClass) httpSession.getAttribute("eazyClass");

                Optional<Person> person = personRepository.findById(id);
                if (person.isEmpty()) {
                        return "redirect:/admin/displayStudents?id="
                                + easyClass.getId()
                                + "&error=true";
                }

                boolean isNotExist = easyClass.removeStudent(person.get());
                if (!isNotExist) {
                        return "redirect:/admin/displayStudents?id="
                                + easyClass.getId()
                                + "&error=true";
                }
                easyClassRepository.save(easyClass);

                httpSession.setAttribute("eazyClass", easyClass);

                return "redirect:/admin/displayStudents?id="
                        + easyClass.getId();
        }

        @GetMapping("/displayCourses")
        public String displayCourses(Model model) {
                List<Course> courses = courseRepository.findAll(Sort.by("name").ascending());
                model.addAttribute("courses", courses);
                model.addAttribute("course", new Course());
                return "courses_secure";
        }

        @PostMapping("/addNewCourse")
        public String addNewCourse(@ModelAttribute("course") Course course) {
                courseRepository.save(course);
                return "redirect:/admin/displayCourses";
        }

        @GetMapping("/viewStudents")
        public String viewStudentsInCourse(@RequestParam("id") Long courseId,
                                           Model model,
                                           HttpSession httpSession,
                                           @RequestParam(required = false)
                                           boolean error) {
                Course course = courseRepository.findById(courseId).orElseThrow();
                model.addAttribute("courses", course);
                model.addAttribute("person", new Person());
                httpSession.setAttribute("course", course);
                if (error) {
                        model.addAttribute(
                                "errorMessage",
                                "Invalid email received."
                        );
                }
                return "course_students";
        }

        @PostMapping("/addStudentToCourse")
        public String addStudentToCourse(@ModelAttribute("person")
                                         Person person,
                                         HttpSession httpSession) {
                Course course =
                        (Course) httpSession.getAttribute("course");
                Optional<Person> personInDB =
                        personRepository.findByEmail(person.getEmail());
                if (personInDB.isEmpty()) {
                        return "redirect:/admin/viewStudents?id="
                                + course.getId()
                                + "&error=true";
                }
                course.addStudent(personInDB.get());
                courseRepository.save(course);
                return "redirect:/admin/viewStudents?id="
                        + course.getId();
        }

        @GetMapping("/deleteStudentFromCourse")
        public String deleteStudentFromCourse(@RequestParam("id") Long personId,
                                              HttpSession httpSession) {
                Course course = (Course) httpSession.getAttribute("course");
                Optional<Person> person = personRepository.findById(personId);
                if (person.isEmpty()) {
                        return "redirect:/admin/viewStudents?id="
                                + course.getId()
                                + "&error=true";
                }
                course.removeStudent(person.get());
                courseRepository.save(course);
                return "redirect:/admin/viewStudents?id="
                        + course.getId();
        }
}
