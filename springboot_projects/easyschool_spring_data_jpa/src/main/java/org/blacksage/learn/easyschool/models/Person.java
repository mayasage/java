package org.blacksage.learn.easyschool.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.blacksage.learn.easyschool.annotations.FieldsValueMatch;
import org.blacksage.learn.easyschool.annotations.Password;
import org.blacksage.learn.easyschool.dtos.Profile;
import org.hibernate.annotations.NaturalId;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "persons")

@NamedEntityGraph(name = "Person.complete", includeAllAttributes = true)

@Getter
@Setter

@FieldsValueMatch.List({

        @FieldsValueMatch(
                fieldA = "password",
                fieldB = "confirmPassword",
                message = "Passwords do not match"
        ),

        @FieldsValueMatch(
                fieldA = "email",
                fieldB = "confirmEmail",
                message = "Emails do not match"
        )
})

public class Person extends BaseEntity {

        @ManyToMany
        @JoinTable(
                name = "persons_courses",
                joinColumns = @JoinColumn(name = "person_id", nullable = false),
                inverseJoinColumns = @JoinColumn(name = "course_id", nullable = false),
                uniqueConstraints = {
                        @UniqueConstraint(
                                name = "uc_person_id_course_id",
                                columnNames = {"person_id", "course_id"}
                        )
                }
        )
        private List<Course> courses = new ArrayList<>();

        @NotBlank(message = "Name must not be blank")
        @Size(min = 3, message = "Name must be at least 3 characters long")
        private String name;

        @NotBlank(message = "Mobile number must not be blank")
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
        private String mobileNumber;

        @NotBlank(message = "Email must not be blank")
        @Email(message = "Please provide a valid email address")
        @NaturalId
        @Column(unique = true, nullable = false)
        private String email;

        @NotBlank(message = "Confirm Email must not be blank")
        @Email(message = "Please provide a valid confirm email address")
        @Transient
        private String confirmEmail;

        @NotBlank(message = "Password must not be blank")
        @Size(min = 5, message = "Password must be at least 5 characters long")
        @Password
        private String password;

        @NotBlank(message = "Confirm Password must not be blank")
        @Size(min = 5, message = "Confirm Password must be at least 5 characters long")
        @Transient
        private String confirmPassword;

        @OneToOne
        private Role role;

        @OneToOne(cascade = CascadeType.ALL)
        private Address address;

        @ManyToOne
        private EasyClass easyClass;

        public void mergeProfile(Profile profile) {

                this.name = profile.getName();
                this.mobileNumber = profile.getMobileNumber();
                this.email = profile.getEmail();

                if (profile.getAddress1() != null) {
                        Address address = new Address();

                        address.setAddress1(profile.getAddress1());
                        address.setAddress2(profile.getAddress2());
                        address.setCity(profile.getCity());
                        address.setState(profile.getState());
                        address.setZipCode(profile.getZipCode());

                        this.address = address;
                }
        }

        public List<Course> getCourses() {
                return Collections.unmodifiableList(courses);
        }

        void addCourse(@NotNull Course course) {
                this.courses.add(course);
        }

        void removeCourse(@NotNull Course course) {
                System.out.println("Before removing course: " + this.courses);
                this.courses.remove(course);
                System.out.println("After removing course: " + this.courses);
        }

        public boolean hasEasyClass() {
                return this.easyClass != null;
        }

        public String getEasyClassName() {
                return this.easyClass.getName();
        }

        @Override
        public boolean equals(Object o) {

                if (o == null) return false;
                if (o == this) return true;

                Class<?> thisClass =
                        (this instanceof HibernateProxy)
                                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                                : this.getClass();

                Class<?> thatClass =
                        (o instanceof HibernateProxy)
                                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                                : o.getClass();

                if (thisClass != thatClass) return false;

                String thisEmail = this.getEmail();
                String thatEmail = ((Person) o).getEmail();

                return thisEmail.equals(thatEmail);
        }

        @Override
        public int hashCode() {

                Person actual =
                        (this instanceof HibernateProxy)
                                ? (Person) ((HibernateProxy) this).getHibernateLazyInitializer().getImplementation()
                                : this;

                return actual.getEmail().hashCode();
        }

        @Override
        public String toString() {
                return "Person{" + getName() + "}";
        }
}
