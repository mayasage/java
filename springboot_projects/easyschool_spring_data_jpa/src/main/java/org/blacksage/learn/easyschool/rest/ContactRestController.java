package org.blacksage.learn.easyschool.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.blacksage.learn.easyschool.models.Contact;
import org.blacksage.learn.easyschool.repositories.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping(
        path = "/api/contact",
        produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
        }
)

@RequiredArgsConstructor

public class ContactRestController {

        private final ContactRepository contactRepository;

        @GetMapping("/getMessagesByStatus")
        public List<Contact> getMessagesByStatus(@RequestParam(required = false)
                                                 String status,
                                                 @RequestBody(required = false)
                                                 Contact contact) {

                if (status != null && !status.isEmpty())
                        return contactRepository.findByStatus(status);
                if (contact != null && contact.getStatus() != null)
                        return contactRepository.findByStatus(contact.getStatus());
                throw new IllegalArgumentException("Status must be provided");
        }

        @PostMapping("/saveMsg")
        public ResponseEntity<ApiResponse> saveMessage(
                @Valid @RequestBody Contact contact) {

                contactRepository.save(contact);

                ApiResponse response = new ApiResponse();
                response.setMessage("Message saved successfully");
                response.setStatusCode(HttpStatus.CREATED.value());
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(response);
        }

        @DeleteMapping("/deleteMsg")
        public ResponseEntity<ApiResponse> deleteMessage(
                @RequestBody Contact contact
        ) {
                contactRepository.deleteById(contact.getId());

                ApiResponse response = new ApiResponse();
                response.setMessage("Message deleted successfully");
                response.setStatusCode(HttpStatus.OK.value());
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(response);

        }

        @PatchMapping("/closeMsg")
        public ResponseEntity<ApiResponse> closeMessage(
                @RequestBody Contact contact
        ) {
                ApiResponse apiResponse = new ApiResponse();

                Optional<Contact> contactInDb =
                        contactRepository.findById(contact.getId());

                if (contactInDb.isEmpty()) {
                        apiResponse.setMessage("Message not found");
                        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
                        return ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .body(apiResponse);
                }

                contactInDb.get().closeMessage();
                contactRepository.save(contactInDb.get());
                apiResponse.setMessage("Message closed successfully");
                apiResponse.setStatusCode(HttpStatus.OK.value());
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(apiResponse);
        }
}
