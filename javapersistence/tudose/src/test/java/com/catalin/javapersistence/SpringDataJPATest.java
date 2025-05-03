package com.catalin.javapersistence;

import com.catalin.javapersistence.configuration.SpringDataConfiguration;
import com.catalin.javapersistence.models.Message;
//import com.catalin.javapersistence.repositories.MessageRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringDataConfiguration.class)
public class SpringDataJPATest {
//        @Autowired
//        private MessageRepository messageRepository;
//
//        @Test
//        @Transactional
//        public void storeLoadMessage() {
//                Message message = new Message();
//                message.setMessageText("Hello World");
//                messageRepository.save(message);
//
//                List<Message> messages = (List<Message>) messageRepository.findAll();
//
//                Assertions.assertAll(
//                        () -> Assertions.assertEquals(1, messages.size()),
//                        () -> Assertions.assertEquals("Hello World", messages.getFirst().getMessageText())
//                );
//
//                throw new RuntimeException();
//        }
}
