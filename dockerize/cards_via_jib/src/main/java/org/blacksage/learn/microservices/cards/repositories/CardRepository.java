package org.blacksage.learn.microservices.cards.repositories;

import org.blacksage.learn.microservices.cards.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
        boolean existsByMobileNumber(String mobileNumber);

        Optional<Card> findByMobileNumber(String mobileNumber);
}
