package org.blacksage.learn.microservices.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
        name = "Account",
        description = "Schema to hold Account details"
)
public class AccountDto {

        @Schema(
                description = "Unique identifier for the account",
                example = "1234567890"
        )
        @NotBlank(message = "AccountNumber can not be a null or empty")
        @Pattern(
                regexp = "(^$|[0-9]{10})",
                message = "AccountNumber must be 10 digits"
        )
        private Long accountNumber;

        @Schema(
                description = "Type of the account (e.g., Savings, Current)",
                example = "Savings"
        )
        @NotBlank(message = "AccountType can not be a null or empty")
        private String accountType;

        @Schema(
                description = "Branch name where the account is held",
                example = "Main Branch"
        )
        @NotBlank(message = "BranchAddress can not be a null or empty")
        private String branchAddress;
}
