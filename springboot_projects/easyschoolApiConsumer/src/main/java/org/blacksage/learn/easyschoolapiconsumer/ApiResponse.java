package org.blacksage.learn.easyschoolapiconsumer;

import lombok.Data;

@Data

public class ApiResponse {

        private String message;
        private int statusCode;
        private Object data;
}
