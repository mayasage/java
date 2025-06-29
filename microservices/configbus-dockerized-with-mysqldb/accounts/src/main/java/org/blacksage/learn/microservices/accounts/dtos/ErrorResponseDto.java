package org.blacksage.learn.microservices.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(
        name = "Error Response",
        description = "Schema to hold error response details"
)
public class ErrorResponseDto {

        @Schema(
                description = "The API endpoint that caused the error",
                example = "/api/v1/accounts"
        )
        private String apiPath;

        @Schema(
                description = "HTTP status code of the error",
                example = "404"
        )
        private HttpStatus errorCode;

        @Schema(
                description = "Detailed message describing the error",
                example = "Account not found"
        )
        private String errorMessage;

        @Schema(
                description = "Timestamp when the error occurred",
                example = "2023-10-01T12:00:00"
        )
        private LocalDateTime errorTime;
}
