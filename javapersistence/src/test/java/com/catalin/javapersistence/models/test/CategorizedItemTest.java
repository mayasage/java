package com.catalin.javapersistence.models.test;

//import com.catalin.javapersistence.repositories.test.CategorizedItemRepository;
import com.catalin.javapersistence.repositories.test.CategoryRepository;
import com.catalin.javapersistence.repositories.test.ItemRepository;
import com.catalin.javapersistence.repositories.test.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.math.BigDecimal;

@SpringBootTest
public class CategorizedItemTest {

        @Autowired
        private CategoryRepository categoryRepository;
        @Autowired
        private ItemRepository itemRepository;
//        @Autowired
//        private CategorizedItemRepository categorizedItemRepository;
        @Autowired
        private UserRepository userRepository;

        @Test
        @Transactional
        @Commit
        public void test() {
                Category someCategory = new Category("Some Category");
                Category otherCategory = new Category("Other Category");
                categoryRepository.save(someCategory);
                categoryRepository.save(otherCategory);

                Item someItem = new Item("Some Item");
                someItem.setMetricWeight(new BigDecimal(2));
                someItem.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                itemRepository.save(someItem);

                Item otherItem = new Item("Other Item");
                otherItem.setMetricWeight(new BigDecimal(2));
                otherItem.setBuyNowPrice(MonetaryAmount.fromString("1.1 USD"));
                itemRepository.save(otherItem);

                User john = new User("John Smith");
                userRepository.save(john);

//                CategorizedItem linkOne = new CategorizedItem(
//                        john, someCategory, someItem
//                );
//                categorizedItemRepository.save(linkOne);
//
//                CategorizedItem linkTwo = new CategorizedItem(
//                        john, someCategory, otherItem
//                );
//                categorizedItemRepository.save(linkTwo);
//
//                CategorizedItem linkThree = new CategorizedItem(
//                        john, otherCategory, someItem
//                );
//                categorizedItemRepository.save(linkThree);

//                CategorizedItem linkOne = new CategorizedItem(
//                        john, someItem
//                );
////                categorizedItemRepository.save(linkOne);
//                someCategory.addCategorizedItem(linkOne);
//
//                CategorizedItem linkTwo = new CategorizedItem(
//                        john, otherItem
//                );
////                categorizedItemRepository.save(linkTwo);
//                someCategory.addCategorizedItem(linkTwo);
//
//                CategorizedItem linkThree = new CategorizedItem(
//                        john, someItem
//                );
////                categorizedItemRepository.save(linkThree);
//                otherCategory.addCategorizedItem(linkThree);
//
//                categoryRepository.save(someCategory);
//                categoryRepository.save(otherCategory);

                someCategory.putItemAddedBy(someItem, john);
                someCategory.putItemAddedBy(otherItem, john);
                otherCategory.putItemAddedBy(someItem, john);

//                List<Category> categoriesOfItem =
//                        categoryRepository.findCategoryWithCategorizedItems(someItem);
//                Assertions.assertEquals(2, categoriesOfItem.size());
        }
}
