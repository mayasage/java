package com.catalin.javapersistence.models.test;

//import com.catalin.javapersistence.repositories.test.AddressRepository;
import com.catalin.javapersistence.repositories.test.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.envers.Audited;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class UserTest {

        @Autowired
        private UserRepository userRepository;

//        @Autowired
//        private AddressRepository addressRepository;

        @AfterEach
        @Transactional
        @Commit
        public void afterEach() {
                System.out.println("deleting the user");
                User user = userRepository.findAll().getFirst();
                System.out.println("found user's address: " + user.getShippingAddress());
//                userRepository.delete(user);
        }

        @Test
        @Transactional
        @Commit
        public void test() {
                User john = new User("John Smith");

                Address address =
                        new Address("Flowers Street", "01246", "Boston");

                john.setShippingAddress(address);
//                address.setUser(john);

//                System.out.println("addressId: " + address.getId());
                System.out.println("johnId: " + john.getId());

                Shipment shipment = new Shipment();
                address.newDelivery(shipment);

                userRepository.save(john);
//                addressRepository.save(address);

                System.out.println("I'm hitting the query to finduser");

                User user = userRepository.findAll().getFirst();
                System.out.println("shipping address: " + user.getShippingAddress());
        }
}
