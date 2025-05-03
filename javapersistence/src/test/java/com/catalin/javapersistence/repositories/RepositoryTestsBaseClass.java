package com.catalin.javapersistence.repositories;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class RepositoryTestsBaseClass {

//        @Autowired
//        private UserRepository userRepository;
//
//        @BeforeAll
//        public void beforeAll() {
//                userRepository.saveAll(generateUsers());
//        }

//        @AfterAll
//        public void afterAll() {
//                userRepository.deleteAll();
//        }

//        private List<User> generateUsers() {
//                List<User> users = new ArrayList<>();
//                for (int i = 0; i < 10; i++) {
//                        User user = new User();
//                        user.setUsername("user" + i);
//                        user.setUserEmail("user" + i + "@gmail.com");
//                        user.setUserFirstName("user" + i);
//                        if (i > 4) user.setUserLevel(2);
//                        if (i > 7) user.setUserActivatedAt(ZonedDateTime.now());
//                        users.add(user);
//                }
//                return users;
//        }
}
