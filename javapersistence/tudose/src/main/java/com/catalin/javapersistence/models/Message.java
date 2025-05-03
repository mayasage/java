package com.catalin.javapersistence.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

//@Entity
@Table(name = "messages")
public class Message {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "message_id", nullable = false, updatable = false, unique = true)
        private Long messageId;

        @Getter
        @Setter
        @Column(name = "message_text", nullable = false)
        private String messageText;

        @Getter
        private Long ormXmlTestFieldId;

        @Getter
        @Setter
        private String ormXmlTestFieldString;

        @Getter
        @Setter
        @CreationTimestamp
        private ZonedDateTime ormXmlTestFieldZonedDateTime;
}
