package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
//@BatchSize(size = 10)
@Audited
public class User extends AbstractEntityBase {

        @NotNull
        private int ranking = 0;

        @NotAudited
        @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        private Set<Item> boughtItems = new HashSet<>();

//        @OneToMany(mappedBy = "addedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//        private Set<CategorizedItem> categorizedItems = new HashSet<>();
//
//        public Set<CategorizedItem> getCategorizedItems() {
//                return Collections.unmodifiableSet(categorizedItems);
//        }
//
//        public void addCategorizedItem(CategorizedItem categorizedItem) {
//                categorizedItems.add(categorizedItem);
//        }

        public void purchaseItem(Item item) {
                boughtItems.add(item);
        }

        @NotBlank
        @Column(unique = true, nullable = false)
        private String username;

//        @OneToOne(
//                optional = false,
//                fetch = FetchType.LAZY,
//                cascade = CascadeType.ALL,
//                orphanRemoval = true
//        )
//        @JoinColumn(name = "shipping_address_id")
//        private Address shippingAddress;

        @NotAudited
        @Embedded
        @AssociationOverrides({
                @AssociationOverride(name = "deliveries", joinTable = @JoinTable(
                        name = "pickachu",
                        joinColumns = @JoinColumn(name = "x_id"),
                        inverseJoinColumns = @JoinColumn(name = "y_id")
                ))
        })
        private Address shippingAddress;

        public User(String username) {
                this.username = username;
        }

//        public User(Long id, String username) {
//                this.id = id;
//                this.username = username;
//        }

//        @Embedded
//        @AttributeOverrides({
//                @AttributeOverride(
//                        name = "street",
//                        column = @Column(name = "billing_street", nullable = false)
//                ),
//                @AttributeOverride(
//                        name = "zipcode",
//                        column = @Column(name = "billing_zipcode", nullable = false)
//                ),
//                @AttributeOverride(
//                        name = "city",
//                        column = @Column(name = "billing_city", nullable = false)
//                ),
//        })
//        private Address billingAddress;

//        @OneToMany(mappedBy = "user")
//        private Set<BillingDetails> billingDetails = new HashSet<>();

        @Override
        public String toString() {
                return "User{" +
                       "boughtItems=" + boughtItems +
                       ", username='" + username + '\'' +
                       ", shippingAddress=" + shippingAddress +
                       '}';
        }
}
