package org.blacksage.learn.easyschool.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blacksage.learn.easyschool.constants.ContactCreatedByConstants;
import org.blacksage.learn.easyschool.constants.ContactStatusConstants;
import org.blacksage.learn.easyschool.model.Contact;
import org.blacksage.learn.easyschool.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {

        private final ContactRepository contactRepository;

        public void saveMessageDetails(Contact contact) {
                contact.setStatus(ContactStatusConstants.OPEN);
                contact.setCreatedBy(ContactCreatedByConstants.ANONYMOUS);
                contact.setCreatedAt(LocalDateTime.now());

                contactRepository.saveContactMsg(contact);
        }

        public List<Contact> findMessagesWithOpenStatus() {
                return contactRepository.findMessagesWithStatus(ContactStatusConstants.OPEN);
        }

        public void updateMessageStatus(Long id, String updatedBy) {
                contactRepository.updateMessageStatus(id, ContactStatusConstants.CLOSED, updatedBy);
        }
}
