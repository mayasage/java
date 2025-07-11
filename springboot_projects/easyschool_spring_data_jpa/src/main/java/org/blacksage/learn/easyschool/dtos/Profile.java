package org.blacksage.learn.easyschool.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.blacksage.learn.easyschool.models.Person;

@Getter
@Setter
@NoArgsConstructor

public class Profile {

        @NotBlank(message = "Name must not be blank")
        @Size(min = 3, message = "Name must be at least 3 characters long")
        private String name;
        @NotBlank(message = "Mobile number must not be blank")
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
        private String mobileNumber;
        @NotBlank(message = "Email must not be blank")
        @Email(message = "Please provide a valid email address")
        private String email;
        @NotBlank(message = "Address1 must not be blank")
        @Size(min = 5, message = "Address1 must be at least 5 characters long")
        private String address1;
        private String address2;
        @NotBlank(message = "City must not be blank")
        @Size(min = 5, message = "City must be at least 5 characters long")
        private String city;
        @NotBlank(message = "State must not be blank")
        @Size(min = 5, message = "State must be at least 5 characters long")
        private String state;
        @NotBlank(message = "Zip Code must not be blank")
        @Pattern(regexp = "(^$|[0-9]{5})", message = "Zip Code must be 5 digits")
        private String zipCode;

        public Profile(Person person) {

                this.name = person.getName();
                this.mobileNumber = person.getMobileNumber();
                this.email = person.getEmail();

                if (person.getAddress() != null) {
                        this.address1 = person.getAddress().getAddress1();
                        this.address2 = person.getAddress().getAddress2();
                        this.city = person.getAddress().getCity();
                        this.state = person.getAddress().getState();
                        this.zipCode = person.getAddress().getZipCode();
                }
        }
}
