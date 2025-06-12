package org.blacksage.learn.easyschool.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")

@Getter
@Setter

public class Role extends BaseEntity {

        @NotBlank(message = "A role name must never be blank")
        @Column(unique = true, nullable = false)
        private String name;
}
