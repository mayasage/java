package org.blacksage.learn.easyschool.controller;

import lombok.RequiredArgsConstructor;
import org.blacksage.learn.easyschool.constants.ContactCreatedByConstants;
import org.blacksage.learn.easyschool.constants.ContactStatusConstants;
import org.blacksage.learn.easyschool.model.Holiday;
import org.blacksage.learn.easyschool.repository.HolidaysRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HolidayController {

        private final HolidaysRepository holidaysRepository;

        @GetMapping("/holidays/{display}")
        public String displayHolidays(@PathVariable String display,
                                      Model model) {

                if (display != null) {
                       if (display.equalsIgnoreCase("festival")) {
                               model.addAttribute("festival", true);
                       } else if (display.equalsIgnoreCase("federal")) {
                               model.addAttribute("federal", true);
                       } else if (display.equalsIgnoreCase("all")) {
                               model.addAttribute("festival", true);
                               model.addAttribute("federal", true);
                       }
                }

                List<Holiday> holidays = holidaysRepository.findAllHolidays();

                Holiday.Type[] types = Holiday.Type.values();
                for (Holiday.Type type : types) {
                        model.addAttribute(
                                type.toString(),
                                holidays.stream()
                                        .filter(holiday -> holiday.getType().equals(type))
                                        .collect(Collectors.toList()));
                }

                return "holidays";
        }
}
