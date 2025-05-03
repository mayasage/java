package com.catalin.javapersistence.repositories;

//import com.catalin.javapersistence.repositories.projections.UserProjection;
import org.junit.jupiter.api.TestInstance;
        import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest extends RepositoryTestsBaseClass {
//        @Autowired
//        private UserRepository userRepository;
//
//        @Test
//        public void findAll() {
//                List<User> users = userRepository.findAll();
//                Assertions.assertEquals(10, users.size());
//        }
//
//        @Test
//        public void findAllByOrderByUserFirstName() {
//                List<User> users = userRepository.findAllByOrderByUserFirstName();
//                Assertions.assertAll(
//                        () -> Assertions.assertEquals(10, users.size()),
//                        () -> Assertions.assertEquals("user9", users.getLast().getUserFirstName())
//                );
//        }
//
//        @Test
//        public void findTopByOrderByUserFirstName() {
//                User user = userRepository.findTopByOrderByUserFirstNameAsc();
//                Assertions.assertAll(
//                        () -> Assertions.assertNotNull(user),
//                        () -> Assertions.assertEquals("user0", user.getUserFirstName())
//                );
//        }
//
//        @Test
//        public void findAllByOrderByUserCreatedAtAsc() {
//                Page<User> userPage1 = userRepository.findAllByOrderByUserCreatedAtAsc(PageRequest.of(0, 5));
//                Page<User> userPage2 = userRepository.findAllByOrderByUserCreatedAtAsc(PageRequest.of(1, 5));
//                int index = 0;
//                for (User user : userPage1) Assertions.assertEquals("user" + index++, user.getUsername());
//                for (User user : userPage2) Assertions.assertEquals("user" + index++, user.getUsername());
//        }
//
//        @Test
//        public void findFirst10ByUserLevel() {
////                List<User> users = userRepository.findFirst10ByUserLevel(1, Sort.by(Sort.Direction.DESC, "Username"));
//                Sort.TypedSort<User> userTypedSort = Sort.sort(User.class);
//                List<User> users = userRepository.findFirst10ByUserLevel(1, userTypedSort.by(User::getUsername).descending());
//                int index = 9;
//                for (User user : users) Assertions.assertEquals("user" + index--, user.getUsername());
//        }
//
//        @Test
//        public void findByUserLevel() {
//                Page<User> userPage1 =
//                        userRepository
//                                .findByUserLevel(
//                                        1,
//                                        PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "Username"))
//                                );
//                Page<User> userPage2 =
//                        userRepository
//                                .findByUserLevel(
//                                        1,
//                                        PageRequest.of(1, 5, Sort.by(Sort.Direction.DESC, "Username"))
//                                );
//                int index = 4;
//                for (User user : userPage1) Assertions.assertEquals("user" + index--, user.getUsername());
//                index = 9;
//                for (User user : userPage2) Assertions.assertEquals("user" + index--, user.getUsername());
//        }
//
//        @Test
//        public void testStreamable() {
//                try (Stream<User> result =
//                        userRepository.findByUserEmailContaining("user")
//                                .and(userRepository.findByUserLevel(1))
//                                .stream()
//                                .distinct()) {
//                        Assertions.assertEquals(10, result.count());
//                }
//        }
//
//        @Test
//        public void findActiveInactiveUsersCount() {
//                UserProjection.UserActivityCounts userActivityCounts = userRepository.findActiveInactiveUsersCount();
//                Assertions.assertAll(
//                        () -> Assertions.assertEquals(2, userActivityCounts.getActiveUserCount()),
//                        () -> Assertions.assertEquals(8, userActivityCounts.getInactiveUserCount()),
//                        () -> Assertions.assertEquals(10, userActivityCounts.getTotalUserCount()),
//                        () -> Assertions.assertEquals("activeUserCount=2, inactiveUserCount=8, totalUserCount=10", userActivityCounts.stringify())
//                );
//        }
//
//        @Test
//        public void findActiveUsersByUserLevelAndUserEmailContaining() {
//                List<User> users = userRepository.findActiveUsersByUserLevelAndUserEmailContaining(2, "user");
//                Assertions.assertAll(
//                        () -> Assertions.assertEquals(2, users.size()),
//                        () -> Assertions.assertEquals("user8", users.getFirst().getUsername()),
//                        () -> Assertions.assertEquals("user9", users.getLast().getUsername())
//                );
//        }
//
//        @Test
//        public void findByUsernameContainingSortByEmailLength() {
//                List<Object[]> results =
//                        userRepository
//                                .findByUsernameContainingSortByEmailLength(
//                                        "user",
//                                        JpaSort.unsafe("LENGTH(u.userEmail)")
//                                );
//                int index = 0;
//                for (Object[] result : results) {
//                        String username = (String) result[0];
//                        Integer userEmailLength = (Integer) result[1];
//                        Assertions.assertEquals("user" + index++, username);
//                        Assertions.assertEquals(15, userEmailLength);
//                }
//        }
//
//        @Test
//        public void findByUserEmail() {
//                UserProjection.UsernameOnly user = userRepository.findByUserEmail("user0@gmail.com", UserProjection.UsernameOnly.class);
//                Assertions.assertEquals("user0", user.getUsername());
//        }
//
//        @Test
//        public void upgradeUserLevels() {
//                int numberOfRecordsUpdated = userRepository.upgradeUserLevels(2, 1);
//                long numberOfRecordsWithUserLevel2 = userRepository.countByUserLevel(2);
//                Assertions.assertAll(
//                        () -> Assertions.assertEquals(5, numberOfRecordsUpdated),
//                        () -> Assertions.assertEquals(0, numberOfRecordsWithUserLevel2)
//                );
//        }
//
//        @Test
//        public void emailQueryByExample() {
//                User probe = new User();
//                probe.setUserEmail("user0");
//                ExampleMatcher matcher =
//                        ExampleMatcher
//                                .matching()
//                                .withMatcher("userEmail", ExampleMatcher.GenericPropertyMatchers.contains());
//                Example<User> example = Example.of(probe, matcher);
//
//                User user0 = userRepository.findOne(example).orElse(null);
//                Assertions.assertNotNull(user0);
//                Assertions.assertEquals("user0@gmail.com", user0.getUserEmail());
//        }
}
