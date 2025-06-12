package org.blacksage.learn.easyschool.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blacksage.learn.easyschool.model.Contact;
import org.blacksage.learn.easyschool.service.ContactService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class ContactController {

        private final ContactService contactService;

        @PostMapping("/saveMsg")
        public String saveMsg(@Valid @ModelAttribute("contact") Contact contact,
                              Errors errors) {
                if (errors.hasErrors()) {
                        log.error("Contact form validation failed due to: {}", errors.toString());
                       return "contact";
                }
                contactService.saveMessageDetails(contact);
                return "redirect:/contact";
        }

       @GetMapping("/contact")
       public String contact(Model model) {
                model.addAttribute("contact", new Contact());
               return "contact";
       }

       @GetMapping("/displayMessages")
       public String displayMessages(Model model) {
               List<Contact> contactMessages = contactService.findMessagesWithOpenStatus();
               model.addAttribute("contactMsgs", contactMessages);
               return "messages";
       }

        @GetMapping("/closeMsg")
        public String closeMsg(@RequestParam Long id, Authentication authentication) {
                contactService.updateMessageStatus(id, authentication.getName());
                return "redirect:/displayMessages";
        }
}
