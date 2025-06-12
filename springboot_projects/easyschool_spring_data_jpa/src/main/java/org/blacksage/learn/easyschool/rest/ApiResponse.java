package org.blacksage.learn.easyschool.rest;

import lombok.Data;

@Data

public class ApiResponse {

        private String message;
        private int statusCode;
        private Object data;
}
