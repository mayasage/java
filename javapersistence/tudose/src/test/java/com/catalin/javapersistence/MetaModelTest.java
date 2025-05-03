package com.catalin.javapersistence;

import com.catalin.javapersistence.models.auction.AuctionItem;
//import com.catalin.javapersistence.models.BidItem_;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.metamodel.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class MetaModelTest {

        @PersistenceUnit
        private EntityManagerFactory entityManagerFactory;

        @Test
        public void getMetaModel() {
                Metamodel metamodel = entityManagerFactory.getMetamodel();
                System.out.println("metamodel: " + metamodel);

                Set<EntityType<?>> entities = metamodel.getEntities();
                System.out.println("entities: " + entities);

                Set<ManagedType<?>> managedTypes = metamodel.getManagedTypes();
                System.out.println("managedTypes: " + managedTypes);

                managedTypes.forEach((managedType) -> {
                        Assertions.assertAll(
                                () -> {
                                        System.out.println("javaType: " + managedType.getJavaType());
                                        Assertions.assertEquals(Type.PersistenceType.ENTITY, managedType.getPersistenceType());
                                }
                        );
                });
        }

        @Test
        public void itemModalReflection() {
                Metamodel metamodel = entityManagerFactory.getMetamodel();

                ManagedType<?> item =
                        metamodel
                                .getManagedTypes()
                                .stream()
                                .filter((managedType) -> managedType.getJavaType().equals(AuctionItem.class))
                                .collect(Collectors.toSet())
                                .iterator()
                                .next();

                System.out.println("item: " + item);

                SingularAttribute<?, ?> itemNameAttribute = item.getSingularAttribute("itemName");
                System.out.println("itemNameAttribute: " + itemNameAttribute);
                Assertions.assertFalse(itemNameAttribute.isOptional());
        }

//        @Test
//        public void criteriaBuilderQuery() {
//                EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//                entityManager.getTransaction().begin();
//                Item item = new Item("earring");
//                entityManager.persist(item);
//                entityManager.getTransaction().commit();
//
//                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//                CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
//                Root<Item> itemRoot = criteriaQuery.from(Item.class);
//                criteriaQuery.select(itemRoot);
////                Path<String> itemName = itemRoot.get("itemName");
////                criteriaQuery.where(criteriaBuilder.like(itemName, criteriaBuilder.parameter(String.class, "pattern")));
//                criteriaQuery
//                        .where(
//                                criteriaBuilder
//                                        .like(
//                                                itemRoot.get(BidItem_.bidItemName),
//                                                criteriaBuilder.parameter(String.class, "pattern")
//                                        )
//                        );
//                List<Item> items =
//                        entityManager
//                                .createQuery(criteriaQuery)
//                                .setParameter("pattern", "%earring%")
//                                .getResultList();
//
//                Assertions.assertAll(
//                        () -> Assertions.assertEquals(1, items.size()),
//                        () -> Assertions.assertEquals("earring", items.getFirst().getBidItemName())
//                );
//        }
}
