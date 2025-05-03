package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import com.catalin.javapersistence.models.test.lifecycle.PersistEntityListener;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Formula;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "items")
//@BatchSize(size = 2)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@NamedEntityGraphs({
//        @NamedEntityGraph(
//                name = "item-buyer",
//                attributeNodes = {
//                        @NamedAttributeNode("buyer")
//                }
//        )
//})
@EntityListeners(PersistEntityListener.class)
@Audited
//@Filter(
//        name = "limitByUserRanking",
//        condition = """
//                        :currentUserRanking >= (
//                                                        SELECT  u.ranking
//                                                        FROM    users u
//                                                        WHERE   u.id = seller_id
//                                                )
//                    """
//)
@Filter(name = "limitByUserRankingDefault")
public class Item extends AbstractEntityBase {

        @Getter
        @Setter
        private ZonedDateTime creationTime;

        public Item(String name) {
                this.itemName = name;
        }

//        @OneToMany(mappedBy = "item")
//        private Set<CategorizedItem> categorizedItems = new HashSet<>();

//        public Set<CategorizedItem> getCategorizedItems() {
//                return Collections.unmodifiableSet(categorizedItems);
//        }
//
//        public void addCategorizedItem(CategorizedItem categorizedItem) {
//                categorizedItems.add(categorizedItem);
//        }

        @NotAudited
        @Getter
        @Setter
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinTable(
                name = "item_buyers",
                joinColumns = @JoinColumn(name = "item_id"),
                inverseJoinColumns = @JoinColumn(nullable = false)
        )
        private User buyer;

        @NotAudited
        @Getter
        @Setter
        @ManyToOne(fetch = FetchType.LAZY)
        private User seller;

//        @Setter
//        @OneToOne(fetch = FetchType.LAZY)
//        @JoinTable(
//                name = "item_shipments",
//                joinColumns = @JoinColumn(name = "item_id"),
//                inverseJoinColumns =
//                        @JoinColumn(
//                                name = "shipment_id",
//                                nullable = false,
//                                unique = true
//                        )
//        )
//        private Shipment shipment;

        @NotAudited
        @ElementCollection(fetch = FetchType.LAZY)
        @CollectionTable(name = "images")
        private Map<Filename, Image> images = new HashMap<>();

//        @OneToMany(
//                cascade = CascadeType.ALL,
//                orphanRemoval = true
//        )
//        @JoinColumn(name = "item_id", nullable = false)
//        @OrderColumn(name = "bid_order", nullable = false)
//        private List<Bid> bids = new ArrayList<>();

        @NotAudited
        @MapKey(name = "id")
        @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
//        @OneToMany(fetch = FetchType.LAZY)
//        @BatchSize(size = 5)
//        @Fetch(FetchMode.SELECT)
//        @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        private final Map<Long, Bid> bids = new HashMap<>();

        private Dimensions dimensions;
        private Weight weight;

        //        @Access(AccessType.FIELD)
        @Access(AccessType.PROPERTY)
//        @NaturalId
        @NotNull
        @Column(nullable = false)
        @Size(min = 1, max = 255, message = "Name is required, maximum 255 characters.")
        private String itemName;

        @NotAudited
        @Getter
//        @Formula("CONCAT(item_name, '-', id)")
        @Formula("(SELECT CONCAT(item_name, '-', id))")
//        @Formula("(SELECT CONCAT(item_name, '-', id) WHERE id = id)")
        private String itemShortDescription;

        @Getter
        @Setter
        @ColumnTransformer(
                read = "metric_weight / 2.20462",
                write = "? * 2.20462"
        )
        private BigDecimal metricWeight;

        @Getter
        @Column(insertable = false)
        @ColumnDefault("1.00")
        @Generated
        private BigDecimal initialPrice;

        @Getter
        @Setter
        @NotNull
        @Embedded
        @AttributeOverrides({
                @AttributeOverride(
                        name = "amount",
                        column = @Column(name = "buy_now_price", nullable = false)
                ),
                @AttributeOverride(
                        name = "currency",
                        column = @Column(name = "buy_now_currency", nullable = false)
                ),
        })
        @Column(nullable = false)
        private MonetaryAmount buyNowPrice;

        public void addBid(Bid bid) {
                bids.put(bid.getId(), bid);
        }

        public void clearBids() {
                bids.clear();
        }

        public String getItemName() {
//                System.out.println("Hibernate called the getItemName method due to @Access(AccessType.PROPERTY).");
                return itemName;
        }

        public void setItemName(String itemName) {
//                System.out.println("Hibernate called the setItemName method due to @Access(AccessType.PROPERTY).");
                this.itemName = itemName;
        }

        public void addImage(Filename filename, Image image) {
                images.put(filename, image);
        }

        public Map<Filename, Image> getImagesUnmodifiable() {
                return Collections.unmodifiableMap(images);
        }

        public Map<Filename, Image> getImages() {
                return images;
        }

//        public Collection<Bid> getBids() {
//                return Collections.unmodifiableCollection(bids);
//        }

        public Map<Long, Bid> getBidsUnmodifiable() {
                return Collections.unmodifiableMap(bids);
        }

        public Map<Long, Bid> getBids() {
                return bids;
        }

        @Override
        public String toString() {
                return "Item{" +
                       "images=" + images +
                       ", bids=" + bids +
                       ", dimensions=" + dimensions +
                       ", weight=" + weight +
                       ", itemName='" + itemName + '\'' +
                       ", itemShortDescription='" + itemShortDescription + '\'' +
                       ", metricWeight=" + metricWeight +
                       ", initialPrice=" + initialPrice +
                       ", buyNowPrice=" + buyNowPrice +
                       '}';
        }
}
