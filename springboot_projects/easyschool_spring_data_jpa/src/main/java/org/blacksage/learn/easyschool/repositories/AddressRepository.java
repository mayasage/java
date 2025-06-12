package org.blacksage.learn.easyschool.repositories;

import org.blacksage.learn.easyschool.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
