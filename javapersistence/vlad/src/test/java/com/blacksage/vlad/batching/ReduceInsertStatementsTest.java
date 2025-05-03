/**
 * - You'll need datasource-proxy artifact to view whether batching is occurring.
 *   DatasourceProxyBeanPostProcessor works.
 */

package com.blacksage.vlad.batching;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ReduceInsertStatementsTest {

        @Getter
        @Entity
        @Table(
                name = "posts",
                uniqueConstraints = {
                        @UniqueConstraint(name = "slug_is_unique", columnNames = "slug")
                }
        )
        private static class Post {

                // Requires Non-Identity ID.
                // If you have Identity, then Hibernate will disable batch insert.
                // Update and delete still works though.
                @Id
                @SequenceGenerator(
                        name = "post_id_sequence_generator",
                        sequenceName = "post_id_sequence"
                )
                @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_sequence_generator")
                private Long id;

                @Setter
                @NotBlank
                @Column(nullable = false)
                private String title;

                @Setter
                @NotBlank
                @Column(nullable = false, unique = true)
                private String slug;
        }

        @PersistenceContext
        private EntityManager em;

        @Test
        void createPostsWithoutBatching() {
                // This should generate 3 post insert statements.
                generateXPosts(3, 1);
        }

        @Test
        void createPostsWithBatching() {
                em.unwrap(Session.class).setJdbcBatchSize(10); // Only 1 insert statement now.
                generateXPosts(3, 2);
        }

        void generateXPosts(int x, int uniqueIdentifier) {
                for (int i = 0; i < x; i++) {
                        Post post = new Post();
                        post.setTitle("Post " + uniqueIdentifier + " " + i);
                        post.setSlug("Post " + uniqueIdentifier + " " + i);
                        em.persist(post);
                }
                em.flush();
        }
}
