package com.catalin.javapersistence.models.test;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Immutable;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Embeddable
@Table(name = "categorized_items")
@Immutable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategorizedItem {

//        @EmbeddedId
//        private final Id id = new Id();

//        @ManyToOne
//        @JoinColumn(
//                name = "category_id",
//                nullable = false,
//                updatable = false,
//                insertable = false
//        )
//        private Category category;

        @ManyToOne
        @JoinColumn(
                name = "item_id",
                nullable = false,
                updatable = false
//                insertable = false
        )
        private Item item;

        @ManyToOne
        @JoinColumn(
                name = "user_id",
//                nullable = false,
                updatable = false
        )
        private User addedBy;

//        @Column(updatable = false, nullable = false)
        @Column(updatable = false)
        @CreationTimestamp
        private ZonedDateTime addedOn;

        public CategorizedItem(
                User addedBy,
//                Category category,
                Item item
        ) {
//                this.category = category;
                this.item = item;
//                this.id.categoryId = category.getId();
//                this.id.itemId = item.getId();
//                category.addCategorizedItem(this);
//                item.addCategorizedItem(this);

                this.addedBy = addedBy;
//                user.addCategorizedItem(this);
        }

        @Embeddable
        @NoArgsConstructor(access = AccessLevel.PROTECTED)
        @EqualsAndHashCode
        public static class Id implements Serializable {
                @NotNull
                @Column(name = "category_id", nullable = false, updatable = false, insertable = false)
                private Long categoryId;

                @NotNull
                @Column(name = "item_id", nullable = false, updatable = false, insertable = false)
                private Long itemId;

                public Id(Long itemId, Long categoryId) {
                        System.out.println("I did not call this constructor");
                        this.itemId = itemId;
                        this.categoryId = categoryId;
                }
        }
}
