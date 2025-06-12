/**
 * Same bean name in two different configurations.
 * Don't they clash?
 */

package org.blacksage.learn.easyschoolapiconsumer.openfeign;

import feign.Headers;
import org.blacksage.learn.easyschoolapiconsumer.models.Contact;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "contact2",
        url = "${easyschool.api.base-url}/contact",
        configuration = ContactProxy2Configuration.class
)
public interface ContactProxy2 {

        @GetMapping(value = "/getMessagesByStatus")
        @Headers(value = "Content-Type: application/json")
        List<Contact> getMessagesByStatus(@RequestParam String status);
}
