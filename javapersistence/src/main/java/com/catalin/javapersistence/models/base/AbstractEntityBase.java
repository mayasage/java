package com.catalin.javapersistence.models.base;

import com.catalin.javapersistence.models.test.interceptor.Auditable;
import com.catalin.javapersistence.services.genid.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.ZonedDateTime;
import java.util.Objects;

@MappedSuperclass
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.NONE)
public abstract class AbstractEntityBase implements Auditable {

        private static IdGenerator idGenerator;

        @Getter(AccessLevel.PUBLIC)
        @Id
        @Column(nullable = false, updatable = false, unique = true)
        private final Long id;

        @Version
        @ColumnDefault(value = "0")
        @Column(nullable = false)
        private final short version;

        @Getter(AccessLevel.PUBLIC)
        @CreationTimestamp
        @Column(nullable = false, updatable = false)
        private ZonedDateTime createdAt;

        @Getter(AccessLevel.PUBLIC)
        @UpdateTimestamp
        @Column(nullable = false)
        private ZonedDateTime updatedAt;

        public AbstractEntityBase() {
                if (idGenerator == null) {
                        throw new IllegalStateException("IdGenerator not set");
                }
                this.id = idGenerator.nextId();
                this.version = 0;
        }

        public static void setIdGenerator(IdGenerator idGenerator) {
                AbstractEntityBase.idGenerator = idGenerator;
        }

        @Override
        public final boolean equals(Object o) {
                if (this.getId() == null) {
                        throw new IllegalStateException("Id not set");
                }

                if (this == o) return true;
                if (o == null) return false;

                Class<?> thisClass =
                        this instanceof HibernateProxy
                                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                                : this.getClass();

                Class<?> thatClass =
                        o instanceof HibernateProxy
                                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                                : o.getClass();

                if (thisClass != thatClass) return false;

                AbstractEntityBase that = (AbstractEntityBase) o;
                return Objects.equals(this.getId(), that.getId());
        }

        @Override
        public final int hashCode() {
                AbstractEntityBase actual = this;
                if (this instanceof HibernateProxy) {
                        actual = (AbstractEntityBase) ((HibernateProxy) this).getHibernateLazyInitializer().getImplementation();
                }

                if (actual.getId() == null) {
                        throw new IllegalStateException("Id not set");
                }

                return actual.getId().hashCode();
        }
}
