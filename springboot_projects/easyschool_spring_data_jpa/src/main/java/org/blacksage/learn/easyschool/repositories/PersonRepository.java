package org.blacksage.learn.easyschool.repositories;

import org.blacksage.learn.easyschool.models.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
        Optional<Person> findByEmail(String email);

        @EntityGraph(value = "Person.complete")
        Optional<Person> findFullByEmail(String email);
}
