package com.catalin.javapersistence.models.test.interceptor;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditLogRecord extends AbstractEntityBase {

        @NotBlank
        private String message;

        @NotNull
        private Long entityId;

        @NotNull
        private Class<? extends Auditable> entityClass;

        @NotNull
        private Long userId;
}
