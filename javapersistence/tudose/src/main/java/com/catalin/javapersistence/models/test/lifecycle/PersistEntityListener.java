package com.catalin.javapersistence.models.test.lifecycle;

import com.catalin.javapersistence.models.test.User;
import jakarta.persistence.PostPersist;

public class PersistEntityListener {

        @PostPersist
        public void logMessage(Object entityInstance) {
                User threadLocalCurrentUser = ThreadLocalCurrentUser.INSTANCE.get();
                SingletonLog log = SingletonLog.INSTANCE;

//                log.save("User: " + threadLocalCurrentUser.getUsername() + " saved entityInstance: " + entityInstance);
        }
}
