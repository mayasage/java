package org.blacksage.learn.easyschoolapiconsumer.openfeign;

import lombok.RequiredArgsConstructor;
import org.blacksage.learn.easyschoolapiconsumer.models.Contact;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feignClient")
@RequiredArgsConstructor

public class FeignClientContactController {

        private final ContactProxy contactProxy;
        private final ContactProxy2 contactProxy2;

        @GetMapping("/getMessages")
        public List<Contact> getMessagesByStatus(@RequestParam String status) {
                return contactProxy.getMessagesByStatus(status);
        }

        @GetMapping("/getMessages2")
        public List<Contact> getMessagesByStatus2(@RequestParam String status) {
                return contactProxy2.getMessagesByStatus(status);
        }
}
