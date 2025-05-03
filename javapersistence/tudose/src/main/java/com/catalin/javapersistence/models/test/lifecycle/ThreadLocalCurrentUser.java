package com.catalin.javapersistence.models.test.lifecycle;

import com.catalin.javapersistence.models.test.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadLocalCurrentUser extends ThreadLocal<User> {

        public static final ThreadLocalCurrentUser INSTANCE = new ThreadLocalCurrentUser();
}
