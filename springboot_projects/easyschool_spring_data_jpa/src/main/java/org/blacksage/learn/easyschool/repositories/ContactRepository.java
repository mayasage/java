package org.blacksage.learn.easyschool.repositories;

import jakarta.transaction.Transactional;
import org.blacksage.learn.easyschool.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
        // Spring Data JPA will automatically pick your named queries

        @Query("SELECT c FROM #{#entityName} c WHERE c.status = :status")
        Page<Contact> findAllByStatus(@Param("status") String status,
                                      Pageable pageable);

        @Query("UPDATE #{#entityName} c SET c.status = ?1 WHERE c.id = ?2")
        @Transactional
        @Modifying
        void updateStatusById(@Param("status") String status,
                              @Param("id") Long id);

        List<Contact> findByStatus(String status);
}
