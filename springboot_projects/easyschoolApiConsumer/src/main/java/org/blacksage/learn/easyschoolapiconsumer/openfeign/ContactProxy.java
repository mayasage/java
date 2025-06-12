package org.blacksage.learn.easyschoolapiconsumer.openfeign;

import feign.Headers;
import org.blacksage.learn.easyschoolapiconsumer.models.Contact;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "contact",
        url = "${easyschool.api.base-url}/contact",
        configuration = ContactProxyConfiguration.class
)
public interface ContactProxy {

        @GetMapping(value = "/getMessagesByStatus")
        @Headers(value = "Content-Type: application/json")
        List<Contact> getMessagesByStatus(@RequestParam String status);
}
