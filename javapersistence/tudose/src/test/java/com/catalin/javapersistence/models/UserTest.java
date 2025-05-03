package com.catalin.javapersistence.models;

//import com.catalin.javapersistence.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserTest {
//        @Autowired
//        private UserRepository userRepository;
//
//        @Autowired
//        private EntityManagerFactory entityManagerFactory;
//
//        @Test
//        public void setNameGetName() {
//                User user = new User();
//                user.setName("Lara Alana");
//                String fullName = user.getName();
//                Assertions.assertAll(
//                        () -> Assertions.assertEquals("Lara Alana", fullName),
//                        () -> Assertions.assertEquals("Lara", user.getUserFirstName()),
//                        () -> Assertions.assertEquals("Alana", user.getUserLastName())
//                );
//        }
//
//        @Test
//        public void userNameMustNotBeNull() {
//                User user = new User();
//                user.setName("Lara Alana");
//                user.setUserEmail("lara.alana@gmail.com");
//                Assertions.assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
//        }
//
//        @Test
//        public void firstNameMustNotBeNull() {
//                User user = new User();
//                user.setUsername("lara_alana");
//                user.setUserEmail("lara.alana@gmail.com");
//                Assertions.assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
//        }
//
//        @Test
//        public void emailMustNotBeNull() {
//                User user = new User();
//                user.setUsername("lara_alana");
//                user.setName("Lara Alana");
//                Assertions.assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
//        }
//
//        @Test
//        public void happyFlow() {
//                User user = new User();
//                user.setUsername("lara_alana");
//                user.setName("Lara Alana");
//                user.setUserEmail("lara.alana@gmail.com");
//                userRepository.save(user);
//        }
//
//        @Test
//        public void emailMustBeValid() {
//                User user = new User();
//                user.setUsername("lara_alana");
//                user.setName("Lara Alana");
//                user.setUserEmail("lara.alana");
//                Assertions.assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
//        }
//
//        @Test
//        public void sequencePrimaryIdTest() {
//                EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//                entityManager.getTransaction().begin();
//                for (int i = 0; i < 200; i++) {
//                        User user = new User();
//                        user.setUsername("user" + i);
//                        user.setUserFirstName("user" + i);
//                        user.setUserEmail("user" + i + "@gmail.com");
//                        entityManager.persist(user);
//                }
//                System.out.println("Not yet committed.");
//                entityManager.getTransaction().commit();
//                System.out.println("Committed.");
//
//                /*
//                This is the output I got for a loop of 200 with allocation_size of 50:
//
//                --
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//
//                Not yet committed.
//
//                Hibernate: insert into users (user_activated_at,user_created_at,user_email,user_first_name,user_last_name,user_level,user_updated_at,username,user_id) values (?,?,?,?,?,?,?,?,?)
//                Hibernate: insert into users (user_activated_at,user_created_at,user_email,user_first_name,user_last_name,user_level,user_updated_at,username,user_id) values (?,?,?,?,?,?,?,?,?)
//                ...
//
//                Committed.
//                --
//
//                On changing allocation size to 1, I got:
//
//                --
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//                ...
//
//                Not yet committed.
//
//                Hibernate: insert into users (user_activated_at,user_created_at,user_email,user_first_name,user_last_name,user_level,user_updated_at,username,user_id) values (?,?,?,?,?,?,?,?,?)
//                Hibernate: insert into users (user_activated_at,user_created_at,user_email,user_first_name,user_last_name,user_level,user_updated_at,username,user_id) values (?,?,?,?,?,?,?,?,?)
//                ...
//
//                Committed.
//                --
//
//                400 queries I guess, I didn't count, as apposed to 200 in the previous case, again I didn't count.
//                 */
//        }
//
//        @Test
//        @Transactional
//        @Commit
//        public void sequencePrimaryIdTestButTransactional() {
//                for (int i = 0; i < 200; i++) {
//                        User user = new User();
//                        user.setUsername("user" + i);
//                        user.setUserFirstName("user" + i);
//                        user.setUserEmail("user" + i + "@gmail.com");
//                        userRepository.save(user);
//                }
//                System.out.println("Not yet committed.");
//
//                /*
//                It's the same output, as in the upper method.
//
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//                Hibernate: select next_val as id_val from user_id_sequence for update
//                Hibernate: update user_id_sequence set next_val= ? where next_val=?
//
//                Not yet committed.
//
//                Hibernate: insert into users (user_activated_at,user_created_at,user_email,user_first_name,user_last_name,user_level,user_updated_at,username,user_id) values (?,?,?,?,?,?,?,?,?)
//                Hibernate: insert into users (user_activated_at,user_created_at,user_email,user_first_name,user_last_name,user_level,user_updated_at,username,user_id) values (?,?,?,?,?,?,?,?,?)
//                ...
//                 */
//        }
}
