package com.blacksage.flyway1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Getter
@Entity
@Table(name = "books")
public class Book {

        @Id
        @SequenceGenerator(
                name = "book_id_sequence_generator",
                sequenceName = "book_id_sequence"
        )
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_sequence_generator")
        private Long id;

        @Setter
        @NotBlank(message = "Every book has an isbn.")
        @NaturalId
        private String isbn;

        @Setter
        @NotBlank(message = "Every book has a title.")
        private String title;
}
