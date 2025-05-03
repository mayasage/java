package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "a1")
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
public class A1 extends AbstractEntityBase {

//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;

        private String name;
        private String description;

        @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
        @JoinTable(
                name = "a1a2",
                joinColumns = @JoinColumn(name = "a1_id"),
                inverseJoinColumns = @JoinColumn(name = "a2_id")
        )
        private Set<A2> a2 = new HashSet<>();

        @Override
        public String toString() {
                return "A1{" +
                       "id=" + super.getId() +
//                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", a2=" + a2 +
                       '}';
        }
}
