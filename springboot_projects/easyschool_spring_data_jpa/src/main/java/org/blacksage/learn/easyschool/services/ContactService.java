package org.blacksage.learn.easyschool.services;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.blacksage.learn.easyschool.config.EasySchoolProperties;
import org.blacksage.learn.easyschool.constants.ContactStatusConstants;
import org.blacksage.learn.easyschool.models.Contact;
import org.blacksage.learn.easyschool.repositories.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {

        private final ContactRepository contactRepository;
        private final EasySchoolProperties easySchoolProperties;

        public void saveMessageDetails(Contact contact) {
                contact.setStatus(ContactStatusConstants.OPEN);
                contactRepository.save(contact);
        }

        public Page<Contact> findMessagesWithOpenStatus(int pageNumber,
                                                        @NotBlank String sortField,
                                                        @NotBlank String sortDir) {
                int pageSize = easySchoolProperties.getPageSize();
                Pageable pageable =
                        PageRequest.of(
                                pageNumber - 1,
                                pageSize,
                                sortDir.equals("asc")
                                        ? Sort.by(sortField).ascending()
                                        : Sort.by(sortField).descending()
                        );
                System.out.println("sortField = " + sortField + "sortDir = " + sortDir);
                return contactRepository
                        .findAllByStatus(ContactStatusConstants.OPEN, pageable);
        }

        public void updateMessageStatus(Long id) {
                contactRepository
                        .updateStatusById(
                                ContactStatusConstants.CLOSED,
                                id
                        );
        }
}
