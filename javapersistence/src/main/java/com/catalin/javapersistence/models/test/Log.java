package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "logs")
@Getter
@Setter
public class Log extends AbstractEntityBase {

        private String message;
}
