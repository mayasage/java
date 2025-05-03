package com.catalin.javapersistence.exceptions.request;

import com.catalin.javapersistence.exceptions.ApplicationException;

public class RequestException extends ApplicationException {
        public RequestException(String message) {
                super(message);
        }
}
