package org.blacksage.learn.easyschool.services;

import lombok.RequiredArgsConstructor;
import org.blacksage.learn.easyschool.models.Person;
import org.blacksage.learn.easyschool.models.Role;
import org.blacksage.learn.easyschool.repositories.PersonRepository;
import org.blacksage.learn.easyschool.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

        private final PersonRepository personRepository;
        private final RoleRepository roleRepository;
        private final PasswordEncoder passwordEncoder;

        public void createNewPerson(Person person, String roleName) {
                Role role = roleRepository.findByName(roleName).orElseThrow();
                person.setRole(role);
                person.setPassword(passwordEncoder.encode(person.getPassword()));
                personRepository.save(person);
        }
}
