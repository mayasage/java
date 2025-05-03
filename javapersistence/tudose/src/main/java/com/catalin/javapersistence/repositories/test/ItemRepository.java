package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.models.test.Image;
import com.catalin.javapersistence.models.test.Item;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
        Item findItemByItemName(String itemName);

        @Query("""
            SELECT              i
            FROM                Item i
            INNER JOIN FETCH    i.images
            WHERE               i.id = :id
        """)
        Item findItemWithImages(@Param("id") Long id);

        @Query(
                value = """
                    SELECT      *
                    FROM        images
                    WHERE       item_id = :id
                """,
                nativeQuery = true
        )
        Set<Image> findImagesNative(Long id);

        @Query("""
            SELECT              i
            FROM                Item i
            INNER JOIN FETCH    i.bids
            WHERE               i.id = :id
        """)
        Item findItemWithBids(@Param("id") Long id);

        Item findOneByItemName(@NotNull String itemName);
}