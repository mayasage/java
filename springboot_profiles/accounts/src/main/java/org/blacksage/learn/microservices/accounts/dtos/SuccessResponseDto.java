package org.blacksage.learn.microservices.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
        name = "Successful Response",
        description = "Schema to hold success response details"
)
public class SuccessResponseDto {

        @Schema(
                description = "HTTP status code of the response",
                example = "201"
        )
        private String statusCode;

        @Schema(
                description = "Message describing the success of the operation",
                example = "Account created successfully"
        )
        private String message;
}
