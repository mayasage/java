package org.blacksage.learn.easyschool.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @JsonIgnore
        @CreatedDate
        @Column(updatable = false)
        private LocalDateTime createdAt;

        @JsonIgnore
        @CreatedBy
        @Column(updatable = false)
        private String createdBy;

        @JsonIgnore
        @LastModifiedDate
        @Column(insertable = false)
        private LocalDateTime updatedAt;

        @JsonIgnore
        @LastModifiedBy
        @Column(insertable = false)
        private String updatedBy;
}
