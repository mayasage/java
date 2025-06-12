package org.blacksage.learn.easyschool.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "courses")

@Getter
@Setter

public class Course extends BaseEntity {

        @Getter(AccessLevel.NONE)
        @Setter(AccessLevel.NONE)
        @ManyToMany(
                mappedBy = "courses",
                cascade = {
                        CascadeType.PERSIST,
                        CascadeType.MERGE
                }
        )
        private List<Person> students = new ArrayList<>();

        @NotBlank(message = "Name must be unique.")
        @Column(unique = true, nullable = false)
        @NaturalId
        private String name;

        @NotBlank(message = "Every course must have a fee.")
        @Column(nullable = false)
        private Long fees;

        public List<Person> getStudents() {
                return Collections.unmodifiableList(students);
        }

        public void addStudent(@NotNull Person student) {
                if (this.students.contains(student)) return;
                this.students.add(student);
                student.addCourse(this);
                System.out.println("List after adding student: " + this.students);
        }

        public void removeStudent(@NotNull Person student) {
                System.out.println("List before removing student: " + this.students);
                this.students.remove(student);
                student.removeCourse(this);
                System.out.println("List after removing student: " + this.students);
        }

        @Override
        public boolean equals(Object o) {
                if (o == this) return true;
                if (o == null) return false;

                Class<?> thisClass =
                        (this instanceof HibernateProxy)
                                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                                : this.getClass();

                Class<?> thatClass =
                        (o instanceof HibernateProxy)
                                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                                : o.getClass();

                if (thisClass != thatClass) return false;
                return this.getName().equals(((Course) o).getName());
        }

        @Override
        public int hashCode() {
                Course actual =
                        (this instanceof HibernateProxy)
                                ? (Course) ((HibernateProxy) this).getHibernateLazyInitializer().getImplementation()
                                : this;
                return actual.getName().hashCode();
        }

        @Override
        public String toString() {
                return "Course{" + getName() + "}";
        }
}
