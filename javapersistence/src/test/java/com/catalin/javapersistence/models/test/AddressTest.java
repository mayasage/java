package com.catalin.javapersistence.models.test;

//import com.catalin.javapersistence.repositories.test.AddressRepository;
import com.catalin.javapersistence.repositories.test.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class AddressTest {

        @Autowired
        private UserRepository userRepository;
//        @Autowired
//        private AddressRepository addressRepository;

//        @Test
//        public void test() {
//                City city = new City();
//                city.setCountry("Switzerland");
//                city.setZipcode(new SwissZipcode("1234"));
//                city.setName("Bern");
//
//                Address address = new Address();
////                address.setCity(city);
//                address.setStreet("Herrengasse");
//
////                User user = new User();
//////                user.setBillingAddress(address);
////                userRepository.save(user);
//
////                User fetchedUser = userRepository.findAll().getFirst();
////                Assertions.assertNotNull(fetchedUser);
////                System.out.println(fetchedUser);
//        }
//
//        @Test
//        public void addAddress() {
//                Address address = new Address("a", "b", "c");
//                addressRepository.save(address);
//        }
}
