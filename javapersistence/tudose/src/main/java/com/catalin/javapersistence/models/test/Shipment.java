package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shipments")
@NoArgsConstructor
public class Shipment extends AbstractEntityBase {

//        @OneToOne(fetch = FetchType.LAZY)
//        @JoinTable(
//                name = "item_shipments",
//                joinColumns = @JoinColumn(name = "shipment_id"),
//                inverseJoinColumns =
//                        @JoinColumn(
//                                name = "item_id",
//                                nullable = false,
//                                unique = true
//                        )
//        )
//        private Item item;
//
//        public Shipment(Item item) {
//                this.item = item;
//        }
}
