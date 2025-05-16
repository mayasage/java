/*

Flush operation order. It's fixed.

1. OrphanRemovalAction
2. EntityInsertAction and EntityIdentityInsertAction
3. EntityUpdateAction
4. CollectionRemoveAction
5. CollectionUpdateAction
6. CollectionRecreateAction
7. EntityDeleteAction.

 */

package com.blacksage.vlad.flushoperationorder;

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
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class DeleteBeforeInsertTest {

        @Getter
        @Entity
        @Table(
                name = "posts",
                uniqueConstraints = {
                        @UniqueConstraint(name = "slug_is_unique", columnNames = "slug")
                }
        )
        private static class Post {

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

        @BeforeEach
        void beforeEach() {
                Post post = new Post();
                post.setTitle("Post Title");
                post.setSlug("Post Slug");
                em.persist(post);
        }

        @Test
        void seeIfItExist() {
                Post post = em.createQuery("select p from Post p", Post.class).getSingleResult();
                Assertions.assertNotNull(post);
                System.out.println("Post: Title: " + post.getTitle() + ", Slug: " + post.getSlug());
        }

        @Test
        void throwsError() {
                Post post = em.createQuery("select p from Post p", Post.class).getSingleResult();
                Assertions.assertNotNull(post);
                em.remove(post);
                Post sameSlug = new Post();
                sameSlug.setTitle("I am using the same slug");
                sameSlug.setSlug("Post Slug");
                Assertions.assertThrows(ConstraintViolationException.class, () -> em.persist(sameSlug));
        }

        @Test
        void doesNotThrowError() {
                Post post = em.createQuery("select p from Post p", Post.class).getSingleResult();
                em.remove(post);
                em.flush(); // just flush it into the database
                Post sameSlug = new Post();
                sameSlug.setTitle("I am using the same slug");
                sameSlug.setSlug("Post Slug");
                em.persist(sameSlug); // error is no more
        }
}
