package org.blacksage.learn.easyschool.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "classes")

@Getter
@Setter

public class EasyClass extends BaseEntity {

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @OneToMany(mappedBy = "easyClass", cascade = CascadeType.PERSIST)
        private final List<Person> students = new ArrayList<>();

        @NotBlank(message = "Name must not be blank")
        @Size(min = 3, message = "Name must be at least 3 characters long")
        @NaturalId
        @Column(unique = true, nullable = false)
        private String name;

        public List<Person> getStudents() {
                return Collections.unmodifiableList(students);
        }

        public boolean addStudent(Person person) {
                if (this.students.contains(person)) return false;
                this.students.add(person);
                person.setEasyClass(this);
                return true;
        }

        public boolean removeStudent(Person person) {
                if (!this.students.contains(person)) return false;
                this.students.remove(person);
                person.setEasyClass(null);
                return true;
        }
}
