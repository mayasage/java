package org.blacksage.learn.easyschoolapiconsumer.webclient;

import lombok.RequiredArgsConstructor;
import org.blacksage.learn.easyschoolapiconsumer.ApiResponse;
import org.blacksage.learn.easyschoolapiconsumer.models.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/webClient")

@RequiredArgsConstructor

public class ContactWebClientController {

        private final WebClient contactWebClient;

        @Value("${easyschool.api.base-url}/contact")
        private String contactBaseUrl;

        @GetMapping("/getMessages")
        public Mono<List<Contact>> getMessagesByStatus(@RequestParam String status) {
                String url = contactBaseUrl + "/getMessagesByStatus?status=" + status;
                System.out.println("Contact WebClient URL: " + url);
                return contactWebClient.get()
                        .uri(url)
                        .header("Accept", "application/json")
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<>() {});
        }

        @PostMapping("/saveMsg")
        public Mono<ApiResponse> saveMessage(@RequestBody Contact contact) {
                String url = contactBaseUrl + "/saveMsg";
                return contactWebClient.post()
                        .uri(url)
                        .body(Mono.just(contact), Contact.class)
                        .retrieve()
                        .bodyToMono(ApiResponse.class);
        }
}
