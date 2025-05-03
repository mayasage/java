//package com.catalin.javapersistence.repositories.views;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import lombok.Getter;
//import org.hibernate.annotations.Immutable;
//import org.hibernate.annotations.Subselect;
//import org.hibernate.annotations.Synchronize;
//
//@Entity
//@Immutable
//@Subselect(value = """
//                SELECT                  i.item_id               AS itemId,
//                                        i.item_name             AS itemName,
//                                        COUNT(b.bid_id)         AS numberOfBids
//                FROM                    items i
//                LEFT OUTER JOIN         bids b
//                ON                      b.bid_item_id = i.item_id
//                GROUP BY                i.item_id, i.item_name
//        """)
//@Synchronize({"Item", "Bid"})
//@Getter
//public class BidSummary {
//        @Id
//        private Long itemId;
//        private String itemName;
//        private Long numberOfBids;
//}
