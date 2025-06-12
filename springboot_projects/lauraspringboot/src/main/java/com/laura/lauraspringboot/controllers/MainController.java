package com.laura.lauraspringboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
        @RequestMapping( "/home/{color}")
        public String fetchHomePage(
                @PathVariable String color,
                @RequestParam(defaultValue = "Mansa Musa", required = false) String name,
                @RequestParam(defaultValue = "Timbaktoo", required = false) String place,
                Model page
        ) {
                page.addAttribute("color", color);
                page.addAttribute("name", name);
                page.addAttribute("place", place);
                return "home";
        }
}
