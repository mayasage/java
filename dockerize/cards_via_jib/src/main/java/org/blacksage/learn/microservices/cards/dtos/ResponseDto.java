package org.blacksage.learn.microservices.cards.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
        private String message;
        private int statusCode;
        private Object data;

        public static ResponseDto from(String message, int statusCode, Object data) {
                ResponseDto response = new ResponseDto();
                response.setMessage(message);
                response.setStatusCode(statusCode);
                response.setData(data);
                return response;
        }

        public static ResponseDto from(String message, int statusCode) {
                return from(message, statusCode, null);
        }
}
