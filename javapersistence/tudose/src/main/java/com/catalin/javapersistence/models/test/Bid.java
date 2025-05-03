package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "bids")
@NoArgsConstructor
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "graph.BidItemBuyerBids",
                attributeNodes = {
                        @NamedAttributeNode(value = "bidder"),
                        @NamedAttributeNode(
                                value = "item",
                                subgraph = "subgraph.item"
                        )
                },
                subgraphs = {
                        @NamedSubgraph(
                                name = "subgraph.item",
                                attributeNodes = {
                                        @NamedAttributeNode(value = "buyer"),
                                        @NamedAttributeNode(value = "bids")
                                }
                        )
                }
        )
})
public class Bid extends AbstractEntityBase {

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "item_id", nullable = false, updatable = false)
        @NotNull
        private Item item;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false, updatable = false)
        @NotNull
        private User bidder;

        @NotNull
        private BigDecimal amount;

        public Bid(BigDecimal amount, Item item) {
                this.amount = amount;
                this.item = item;
        }

        public Bid(BigDecimal amount) {
                this.amount = amount;
        }

        @Override
        public String toString() {
                return "Bid{" +
                       ", amount=" + amount +
                       '}';
        }
}
