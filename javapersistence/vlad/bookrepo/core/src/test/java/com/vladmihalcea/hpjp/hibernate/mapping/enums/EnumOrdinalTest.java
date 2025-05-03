package com.vladmihalcea.hpjp.hibernate.mapping.enums;

import com.vladmihalcea.hpjp.util.AbstractTest;
import com.vladmihalcea.hpjp.util.providers.Database;
import jakarta.persistence.*;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vlad Mihalcea
 */
public class EnumOrdinalTest extends AbstractTest {

    @Override
    protected Class<?>[] entities() {
        return new Class<?>[]{
            Post.class
        };
    }

    @Override
    protected Database database() {
        return Database.POSTGRESQL;
    }

    @Test
    public void test() {
        doInJPA(entityManager -> {
            entityManager.persist(
                new Post()
                    .setTitle("Check out my website")
                    .setStatus(PostStatus.REQUIRES_MODERATOR_INTERVENTION)
            );
        });

        try {
            doInJPA(entityManager -> {
                int postId = 50;

                int rowCount = entityManager.createNativeQuery("""
                    INSERT INTO post (status, title, id)
                    VALUES (:status, :title, :id)
                    """)
                .setParameter("status", 99)
                .setParameter("title", "Illegal Enum value")
                .setParameter("id", postId)
                .executeUpdate();

                assertEquals(1, rowCount);

                Post post = entityManager.find(Post.class, postId);

                fail("Should not map the Enum value of 100!");
            });
        } catch (ConstraintViolationException e) {
            assertTrue(e.getMessage().contains("violates check constraint \"post_status_check\""));
        }
    }

    public enum PostStatus {
        PENDING,
        APPROVED,
        SPAM,
        REQUIRES_MODERATOR_INTERVENTION
    }

    @Entity(name = "Post")
    @Table(name = "post")
    public static class Post {

        @Id
        @GeneratedValue
        private Integer id;

        private String title;

        @Enumerated(EnumType.ORDINAL)
        private PostStatus status;

        public Integer getId() {
            return id;
        }

        public Post setId(Integer id) {
            this.id = id;
            return this;
        }

        public String getTitle() {
            return title;
        }

        public Post setTitle(String title) {
            this.title = title;
            return this;
        }

        public PostStatus getStatus() {
            return status;
        }

        public Post setStatus(PostStatus status) {
            this.status = status;
            return this;
        }
    }
}
