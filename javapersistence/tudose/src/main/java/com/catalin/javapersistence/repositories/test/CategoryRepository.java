package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.models.test.Category;
import com.catalin.javapersistence.models.test.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

//        @Query("""
//                    SELECT      c
//                    FROM        Category c
//                    LEFT JOIN   c.categorizedItems ci
//                    WHERE       ci.item = :item
//                """)
//        List<Category> findCategoryWithCategorizedItems(@Param("item") Item item);
}