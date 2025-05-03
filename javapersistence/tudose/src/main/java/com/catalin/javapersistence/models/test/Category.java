package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends AbstractEntityBase {

        private String name;

        public Category(String name) {
                this.name = name;
        }

//        @Getter(AccessLevel.NONE)
//        @Setter(AccessLevel.NONE)
//        @ElementCollection
//        @CollectionTable(
//                name = "categorized_items",
//                joinColumns = @JoinColumn(name = "category_id"),
//                uniqueConstraints = @UniqueConstraint(
//                        columnNames = {"category_id", "item_id"}
//                )
//        )
//        private Set<CategorizedItem> categorizedItems = new HashSet<>();

//        public Set<CategorizedItem> getCategorizedItems() {
//                return Collections.unmodifiableSet(categorizedItems);
//        }
//
//        public void addCategorizedItem(CategorizedItem categorizedItem) {
//                categorizedItems.add(categorizedItem);
//        }

        @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
        @MapKeyJoinColumn(name = "item_id")
        @JoinTable(
                name = "categorized_item_2.0",
                joinColumns = @JoinColumn(name = "category_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id")
        )
//        @Filter(
//                name = "limitByUserRanking",
//                condition = """
//                        :currentUserRanking >= (
//                                                        SELECT  u.ranking
//                                                        FROM    users u
//                                                        WHERE   u.id = seller_id
//                                                )
//                    """
//        )
        @Filter(name = "limitByUserRankingDefault")
        private final Map<Item, User> itemAddedBy = new HashMap<>();

        public Map<Item, User> getItemAddedBy() {
                return Collections.unmodifiableMap(itemAddedBy);
        }

        public void putItemAddedBy(Item item, User user) {
                itemAddedBy.put(item, user);
        }
}
