package org.blacksage.learn.easyschoolapiconsumer.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Contact {
        private Long id;
        private String name;
        private String mobileNum;
        private String email;
        private String subject;
        private String message;
        private String status;
}
