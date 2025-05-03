//package com.catalin.javapersistence.repositories;
//
//import com.catalin.javapersistence.models.User;
//import com.catalin.javapersistence.repositories.projections.UserProjection;
//import jakarta.transaction.Transactional;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.util.Streamable;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//        List<User> findAllByOrderByUserFirstName();
//
//        <T> T findByUserEmail(
//                @Email
//                @NotBlank(message = "User's email is required.")
//                String userEmail,
//                Class<T> projection
//        );
//
//        User findTopByOrderByUserFirstNameAsc();
//
//        Page<User> findAllByOrderByUserCreatedAtAsc(Pageable pageable);
//
//        List<User> findFirst10ByUserLevel(Integer userLevel, Sort sort);
//
//        Page<User> findByUserLevel(Integer userLevel, Pageable pageable);
//
//        Streamable<User> findByUserEmailContaining(@NotBlank(message = "Pattern for User's email is required.") String pattern);
//
//        Streamable<User> findByUserLevel(Integer userLevel);
//
//        @Query("""
//                        SELECT
//                            SUM(CASE WHEN u.userActivatedAt IS NULL THEN 1 ELSE 0 END) AS inactiveUserCount,
//                            SUM(CASE WHEN u.userActivatedAt IS NOT NULL THEN 1 ELSE 0 END) AS activeUserCount
//                        FROM #{#entityName} u
//                """)
//        UserProjection.UserActivityCounts findActiveInactiveUsersCount();
//
//        @Query("""
//                        SELECT  u
//                        FROM    #{#entityName} u
//                        WHERE   u.userActivatedAt IS NOT NULL AND
//                                u.userEmail LIKE CONCAT("%", :userEmailContainText, "%") AND
//                                u.userLevel = :userLevel
//                """)
//        List<User> findActiveUsersByUserLevelAndUserEmailContaining(
//                @NotBlank(message = "User's level is required.")
//                @Param("userLevel")
//                Integer userLevel,
//
//                @NotBlank(message = "Text that is a part of User's email is required.")
//                @Param("userEmailContainText")
//                String userEmailContainText
//        );
//
//        @Query("""
//                        SELECT  u.username,
//                                LENGTH(u.userEmail) AS userEmailLength
//                        FROM    #{#entityName} u
//                        WHERE   u.username LIKE %?1%
//                """)
//        List<Object[]> findByUsernameContainingSortByEmailLength(String userContainingText, Sort sort);
//
//        @Query("""
//                        UPDATE  #{#entityName} u
//                        SET     u.userLevel = :newUserLevel
//                        WHERE   u.userLevel = :oldUserLevel
//                """)
//        @Modifying
//        @Transactional
//        int upgradeUserLevels(int oldUserLevel, int newUserLevel);
//
//        long count();
//
//        long countByUserLevel(int userLevel);
//
//        User findUserByUsername(@NotBlank(message = "User's username must be unique.") String username);
//}
