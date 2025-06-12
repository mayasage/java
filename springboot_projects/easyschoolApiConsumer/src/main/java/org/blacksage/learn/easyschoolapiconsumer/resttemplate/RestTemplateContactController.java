package org.blacksage.learn.easyschoolapiconsumer.resttemplate;

import lombok.RequiredArgsConstructor;
import org.blacksage.learn.easyschoolapiconsumer.ApiResponse;
import org.blacksage.learn.easyschoolapiconsumer.models.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/restTemplate")
@RequiredArgsConstructor

public class RestTemplateContactController {

        private final RestTemplate restTemplate;

        @Value("${easyschool.api.base-url}/contact")
        private String contactBaseUrl;

        @GetMapping("/getMessages")
        public List<Contact> getMessagesByStatus(@RequestParam String status) {
                String url = contactBaseUrl + "/getMessagesByStatus?status=" + status;
                System.out.println("Contact RestTemplate URL: " + url);
                HttpHeaders headers = new HttpHeaders();
                headers.add("Accept", "application/json");
                HttpEntity<Contact> httpEntity = new HttpEntity<>(headers);
                ResponseEntity<List<Contact>> response = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        httpEntity,
                        new ParameterizedTypeReference<List<Contact>>() {}
                );
                return response.getBody();
        }

        @PostMapping("/saveMsg")
        public ResponseEntity<ApiResponse> saveMessage(@RequestBody Contact contact) {
               String url = contactBaseUrl + "/saveMsg";
                HttpHeaders headers = new HttpHeaders();
                HttpEntity<Contact> httpEntity = new HttpEntity<>(contact, headers);
                return restTemplate.exchange(
                        url,
                        HttpMethod.POST,
                        httpEntity,
                        ApiResponse.class
                );
        }
}
