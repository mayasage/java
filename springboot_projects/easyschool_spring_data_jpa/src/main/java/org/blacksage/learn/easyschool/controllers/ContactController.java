package org.blacksage.learn.easyschool.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blacksage.learn.easyschool.models.Contact;
import org.blacksage.learn.easyschool.services.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                        log.error("Contact form validation failed due to: {}", errors);
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

        @GetMapping("/displayMessages/page/{pageNumber}")
        public String displayMessages(Model model,
                                      @PathVariable int pageNumber,
                                      @RequestParam String sortField,
                                      @RequestParam String sortDir) {
                Page<Contact> contactMessagesPage =
                        contactService.findMessagesWithOpenStatus(
                                pageNumber,
                                sortField,
                                sortDir
                        );
                List<Contact> contactMessages = contactMessagesPage.getContent();
                model.addAttribute("contactMsgs", contactMessages);
                model.addAttribute("currentPage", pageNumber);
                model.addAttribute("totalPages", contactMessagesPage.getTotalPages());
                model.addAttribute("totalMsgs", contactMessagesPage.getTotalElements());
                model.addAttribute("sortField", sortField);
                model.addAttribute("sortDir", sortDir);
                model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
                return "messages";
        }

        @GetMapping("/closeMsg")
        public String closeMsg(@RequestParam Long id) {
                contactService.updateMessageStatus(id);
                return "redirect:/displayMessages/page/1?sortField=name&sortDir=asc";
        }
}
