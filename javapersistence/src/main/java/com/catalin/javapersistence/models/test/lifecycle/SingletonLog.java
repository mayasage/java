package com.catalin.javapersistence.models.test.lifecycle;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SingletonLog extends ArrayList<String> {

        public static final SingletonLog INSTANCE = new SingletonLog();

        public void save(String message) {
                add(message);
        }
}
