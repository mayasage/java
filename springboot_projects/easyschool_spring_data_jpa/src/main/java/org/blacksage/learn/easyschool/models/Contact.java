package org.blacksage.learn.easyschool.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.blacksage.learn.easyschool.constants.ContactStatusConstants;

@Entity
@Table(name = "contact_msg")

@Getter
@Setter

public class Contact extends BaseEntity {

        @NotBlank(message = "Name must not be blank")
        @Size(min = 3, message = "Name must be at least 3 characters long")
        private String name;

        @NotBlank(message = "Mobile number must not be blank")
        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
        private String mobileNum;

        @NotBlank(message = "Email must not be blank")
        @Email(message = "Please provide a valid email address")
        private String email;

        @NotBlank(message = "Subject must not be blank")
        @Size(min = 5, message = "Subject must be at least 5 characters long")
        private String subject;

        @NotBlank(message = "Message must not be blank")
        @Size(min = 10, message = "Message must be at least 10 characters long")
        private String message;

        private String status;

        public void closeMessage() {
                this.status = ContactStatusConstants.CLOSED;
        }
}
