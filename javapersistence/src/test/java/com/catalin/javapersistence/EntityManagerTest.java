package com.catalin.javapersistence;

import com.catalin.javapersistence.models.test.A1;
import com.catalin.javapersistence.models.test.A2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.PersistenceUnitUtil;
import jakarta.persistence.PersistenceUtil;
import org.hibernate.Hibernate;
import org.hibernate.LazyInitializationException;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class EntityManagerTest {

        @PersistenceUnit
        private EntityManagerFactory entityManagerFactory;

        @Test
        public void test() {
                PersistenceUnitUtil puu = entityManagerFactory.getPersistenceUnitUtil();

                A1 a = new A1();
                a.setName("A1");
//                Assertions.assertNull(puu.getIdentifier(a));

                EntityManager em = entityManagerFactory.createEntityManager();
                System.out.println("em init");
                try {
                        System.out.println("id: " + a.getId());
//                        Assertions.assertNull(em.getReference(A1.class, a.getId()));
//                        System.out.println("returned this: " + em.getReference(A1.class, a.getId()));
                        em.getTransaction().begin();
                        Assertions.assertFalse(em.contains(a));
//                        Assertions.assertNull(puu.getIdentifier(a));
                        System.out.println("detached a: " + (!em.contains(a) && puu.getIdentifier(a) == a.getId()));
                        em.persist(a);
                        Assertions.assertNotNull(em.getReference(A1.class, a.getId()));
                        Assertions.assertTrue(em.contains(a));
//                        Assertions.assertNotNull(puu.getIdentifier(a));
                        em.getTransaction().commit();
                        System.out.println("em Commit");
                } catch (Exception ex) {
                        if (em.getTransaction().isActive()) {
                                Assertions.assertTrue(em.contains(a));
                                em.getTransaction().rollback();
                                Assertions.assertNotNull(em.getReference(A1.class, a.getId()));
                                System.out.println("em Rollback");
                        }
                } finally {
                        if (em.isOpen()) {
//                                Assertions.assertFalse(em.contains(a));
                                System.out.println("id: " + a.getId());
//                                Assertions.assertNull(em.getReference(A1.class, a.getId()));
                                em.close();
                                System.out.println("em Closed");
                        }
                }

//                Assertions.assertNull(puu.getIdentifier(a));

                A1 lazyReference = null;

                EntityManager em2 = entityManagerFactory.createEntityManager();
                System.out.println("em2 init");
                try {
                        System.out.println("It will not send an API call right now");
//                        Assertions.assertNotNull(em2.getReference(A1.class, a.getId()));
                        A1 x = em2.getReference(A1.class, a.getId());
                        lazyReference = x;
                        Assertions.assertFalse(puu.isLoaded(x));
//                        A1 x1 = em2.getReference(A1.class, a.getId());
//                        A1 x2 = em2.getReference(A1.class, a.getId());
//                        System.out.println("returned this: " + em2.getReference(A1.class, a.getId()));
//                        System.out.println("returned this: " + em2.getReference(A1.class, a.getId()));
//                        System.out.println("returned this: " + em2.getReference(A1.class, a.getId()));
//                        System.out.println("returned this: " + em2.getReference(A1.class, a.getId()));
                        System.out.println("But it will now");
//                        a.getId();
//                        a.getName();
                        em2.find(A1.class, a.getId());
//                        System.out.println("calling x.getName(): " + x.getName());
//                        Hibernate.initialize(x);
//                        Hibernate.initialize(x);
//                        Hibernate.initialize(x);
//                        Hibernate.initialize(x);
                        Assertions.assertTrue(puu.isLoaded(x));
//                        Assertions.assertFalse(puu.isLoaded(x));
                        System.out.println("end");
                        em2.getTransaction().begin();
                        Assertions.assertFalse(em2.contains(a));
//                        Assertions.assertFalse(puu.isLoaded(x));
                        A1 f = em2.find(A1.class, a.getId());
                        Assertions.assertTrue(puu.isLoaded(x));
                        A1 f2 = em2.find(A1.class, a.getId());
                        Assertions.assertAll(
                                () -> Assertions.assertEquals(f, f2),
                                () -> Assertions.assertSame(f, f2),
                                () -> Assertions.assertEquals(f.getId(), f2.getId())
                        );
                        Assertions.assertNotNull(f);
                        Assertions.assertTrue(em2.contains(f));
                        Assertions.assertNotNull(puu.getIdentifier(f));
                        Assertions.assertAll(
                                () -> Assertions.assertNotNull(puu.getIdentifier(f)) // is not in transient state
                                // (we have the identifier so it's not in transient state)
//                                () -> Assertions.assertFalse(!em2.contains(f) && !puu.getIdentifier(f))
                        );
                        em2.getTransaction().commit();
                        Assertions.assertNotNull(puu.getIdentifier(a));
                        Assertions.assertNotNull(puu.getIdentifier(f));
                        System.out.println("em2 Commit");
                        Assertions.assertTrue(puu.isLoaded(x));
                } catch (Exception ex) {
                        if (em2.getTransaction().isActive()) {
                                em2.getTransaction().rollback();
                                System.out.println("em2 Rollback");
                        }
                } finally {
                        if (em2.isOpen()) {
                                em2.close();
                                System.out.println("em2 Closed");
                        }
                }

                A1 lazyReference2 = lazyReference;
//                Assertions.assertThrows(LazyInitializationException.class, () -> lazyReference2.getName());

//                Assertions.assertNull(puu.getIdentifier(a));

                EntityManager em3 = entityManagerFactory.createEntityManager();
                System.out.println("em2 init");
                try {
                        A1 x = em3.getReference(A1.class, a.getId());
                        lazyReference = x;
                        em3.getTransaction().begin();
                        em3.getTransaction().commit();
                        System.out.println("em3 Commit");
                        Assertions.assertFalse(puu.isLoaded(x));
                } catch (Exception ex) {
                        if (em3.getTransaction().isActive()) {
                                em3.getTransaction().rollback();
                                System.out.println("em3 Rollback");
                        }
                } finally {
                        if (em3.isOpen()) {
                                em3.close();
                                System.out.println("em3 Closed");
                        }
                }

                A1 lazyReference3 = lazyReference;
                Assertions.assertThrows(LazyInitializationException.class, () -> lazyReference3.getName());

//                EntityManager em4 = entityManagerFactory.createEntityManager();
//                System.out.println("em4 init");
//                try {
//                        A1 x = em4.getReference(A1.class, a.getId());
//                        Assertions.assertAll(
//                                () -> Assertions.assertTrue(em4.contains(x)),
//                                () -> Assertions.assertFalse(puu.isLoaded(x))
//                        );
//                        em4.getTransaction().begin();
//                        em4.remove(x);
//                        Assertions.assertAll(
//                                () -> Assertions.assertFalse(em4.contains(x)),
//                                () -> Assertions.assertTrue(puu.isLoaded(x)),
//                                () -> Assertions.assertNotNull(x.getId()),
//                                () -> Assertions.assertNotNull(x.getName())
//                        );
//                        em4.getTransaction().commit();
//                        System.out.println("em4 Commit");
//                } catch (Exception ex) {
//                        if (em4.getTransaction().isActive()) {
//                                em4.getTransaction().rollback();
//                                System.out.println("em4 Rollback");
//                        }
//                } finally {
//                        if (em4.isOpen()) {
//                                em4.close();
//                                System.out.println("em4 Closed");
//                        }
//                }

                EntityManager em5 = entityManagerFactory.createEntityManager();
                System.out.println("em5 init");
                try {
                        A1 x = em5.find(A1.class, a.getId());
                        Assertions.assertAll(
                                () -> Assertions.assertTrue(em5.contains(x)),
                                () -> Assertions.assertTrue(puu.isLoaded(x))
                        );
                        em5.getTransaction().begin();
                        em5.remove(x);
                        em5.persist(x);
                        em5.remove(x);
                        em5.persist(x);
                        em5.remove(x);
                        em5.persist(x);
                        em5.remove(x);
                        em5.persist(x);
                        em5.remove(x);
                        em5.persist(x);
                        em5.remove(x);
                        em5.persist(x);
                        em5.remove(x);
                        em5.refresh(x);
                        Assertions.assertAll(
//                                () -> Assertions.assertFalse(em5.contains(x)),
                                () -> Assertions.assertTrue(em5.contains(x)),
                                () -> Assertions.assertTrue(puu.isLoaded(x)),
                                () -> Assertions.assertNull(x.getId())
//                                () -> Assertions.assertNull(x.getName())
                        );
                        em5.getTransaction().commit();
                        System.out.println("em5 Commit");
                } catch (Exception ex) {
                        if (em5.getTransaction().isActive()) {
                                em5.getTransaction().rollback();
                                System.out.println("em5 Rollback");
                        }
                } finally {
                        if (em5.isOpen()) {
                                em5.close();
                                System.out.println("em5 Closed");
                        }
                }
        }

//        private static EntityManagerFactory emfa = Persistence.createEntityManagerFactory("a");
//        private static EntityManagerFactory emfb = Persistence.createEntityManagerFactory("b");

        @Autowired
        @Qualifier("emfa")
        private EntityManagerFactory emfa;

        @Autowired
        @Qualifier("emfb")
        private EntityManagerFactory emfb;

        @Test
        public void testReplication() {
                EntityManager ema = emfa.createEntityManager();
                ema.getTransaction().begin();
                A1 a = new A1();
                a.setName("a");
                ema.persist(a);
                ema.getTransaction().commit();
                ema.close();

                ema = emfa.createEntityManager();
                ema.getTransaction().begin();
                A1 x = ema.find(A1.class, a.getId());
                Assertions.assertNotNull(x);

                EntityManager emb = emfb.createEntityManager();
                emb.getTransaction().begin();
                Session embs = emb.unwrap(Session.class);
                embs.replicate(a, ReplicationMode.LATEST_VERSION);
                emb.getTransaction().commit();
                emb.close();
                ema.close();

//                emb = emfb.createEntityManager();
//                emb.getTransaction().begin();
//                embs = emb.unwrap(Session.class);
//                embs.replicate(a, ReplicationMode.LATEST_VERSION);
//                emb.getTransaction().commit();
//                emb.close();

                ema = emfa.createEntityManager();
                ema.getTransaction().begin();
                x = ema.find(A1.class, a.getId());
                x.setName("b");
                ema.persist(x);
//                ema.getTransaction().commit();
//                ema.close();

                emb = emfb.createEntityManager();
                emb.getTransaction().begin();
                embs = emb.unwrap(Session.class);
                embs.replicate(x, ReplicationMode.LATEST_VERSION);
                emb.getTransaction().commit();
                emb.close();
                ema.close();
        }

        @Test
        public void readOnlyEntity() {
                EntityManager em = entityManagerFactory.createEntityManager();

                em.getTransaction().begin();
                A1 a = new A1();
                a.setName("a");
                em.persist(a);
                A1 a2 = new A1();
                a2.setName("b");
                em.persist(a2);
                em.getTransaction().commit();
                em.close();

                EntityManager em2 = entityManagerFactory.createEntityManager();
//                em2.unwrap(Session.class).setDefaultReadOnly(true);
                em2.getTransaction().begin();
                A1 x = em2.find(A1.class, a.getId());
                A1 x2 = em2.find(A1.class, a2.getId());
                Assertions.assertAll(
                        () -> Assertions.assertNotNull(x),
                        () -> Assertions.assertNotNull(x2)
                );
                em2.unwrap(Session.class).setReadOnly(x, true);
                x.setName("a1");
                x2.setName("b1");
//                em2.persist(x);
//                em2.persist(x2);
                em2.flush();
                em2.getTransaction().commit();
                em2.close();
        }

        @Test
        public void disableFlushing() {
                EntityManager em = entityManagerFactory.createEntityManager();
                em.getTransaction().begin();
                A1 a = new A1();
                a.setName("a");
                em.persist(a);
                A1 a2 = new A1();
                a2.setName("b");
                em.persist(a2);
                em.getTransaction().commit();
                em.close();

                EntityManager em2 = entityManagerFactory.createEntityManager();
                em2.getTransaction().begin();
                A1 x = em2.find(A1.class, a.getId());
                x.setName("a1");
                em2.setFlushMode(FlushModeType.COMMIT);
                Assertions.assertEquals(
                        "a",
                        em2
                                .createQuery(
                                        "select x.name from A1 x where x.id = :id",
                                        String.class
                                )
                                .setParameter("id", a.getId())
                                .getSingleResult()
                );
                em2.getTransaction().commit();
                em2.close();
        }

        @Test
        public void detachedEntity() {
                EntityManager em = entityManagerFactory.createEntityManager();
                em.getTransaction().begin();
                A1 a = new A1();
                a.setName("a");
                em.persist(a);
                em.getTransaction().commit();
                em.close();

                EntityManager em2 = entityManagerFactory.createEntityManager();
                em2.getTransaction().begin();
                A1 x = em2.find(A1.class, a.getId());
                A1 x2 = em2.find(A1.class, a.getId());
                Assertions.assertAll(
                        () -> Assertions.assertEquals(x.getId(), x2.getId()),
                        () -> Assertions.assertEquals(x, x2),
                        () -> Assertions.assertSame(x, x2)
                );
                em2.getTransaction().commit();
                em2.close();

                EntityManager em3 = entityManagerFactory.createEntityManager();
                em3.getTransaction().begin();
                A1 x3 = em3.find(A1.class, a.getId());
                Assertions.assertAll(
                        () -> Assertions.assertEquals(x.getId(), x3.getId()),
                        () -> Assertions.assertNotEquals(x, x3),
//                        () -> Assertions.assertEquals(x, x3),
                        () -> Assertions.assertNotSame(x, x3)
                );
                em3.getTransaction().commit();
                em3.close();

                Set<A1> aSet = new HashSet<>();
                aSet.add(x);
                aSet.add(x2);
                aSet.add(x3);
//                Assertions.assertEquals(1, aSet.size());
                Assertions.assertEquals(2, aSet.size());
        }

        @Test
        public void detachment() {
                EntityManager em = entityManagerFactory.createEntityManager();
                em.getTransaction().begin();
                A1 a = new A1();
                a.setName("a");
                em.persist(a);
                em.getTransaction().commit();
                em.close();

                EntityManager em2 = entityManagerFactory.createEntityManager();
                em2.getTransaction().begin();
                A1 x = em2.find(A1.class, a.getId());
                Assertions.assertTrue(em2.contains(x));
                em2.detach(x);
                Assertions.assertFalse(em2.contains(x));
                em2.getTransaction().commit();
                em2.close();

                x.setName("b");

                A1 a2 = new A1();
                a2.setName("a2");

                EntityManager em3 = entityManagerFactory.createEntityManager();
                em3.getTransaction().begin();
                System.out.println("Hibernate will perform DB Query");
                A1 mergedUser = em3.merge(x);
                Assertions.assertNotEquals(x, mergedUser);
                mergedUser.setName("c");
                x.setName("d");
                A1 mergedUser2 = em3.merge(a2);
                a2.setName("e");
                mergedUser2.setName("f");
//                em3.remove(a2);
                em3.remove(mergedUser2);
                em3.getTransaction().commit();
                em3.close();
        }
}
