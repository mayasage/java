package org.blacksage.learn.microservices.accounts.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.blacksage.learn.microservices.accounts.constants.AccountConstants;
import org.blacksage.learn.microservices.accounts.dtos.ContactInfoDto;
import org.blacksage.learn.microservices.accounts.dtos.CustomerDto;
import org.blacksage.learn.microservices.accounts.dtos.ErrorResponseDto;
import org.blacksage.learn.microservices.accounts.dtos.SuccessResponseDto;
import org.blacksage.learn.microservices.accounts.services.IAccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "CRUD REST APIs for Accounts in EasyBank",
        description = "CRUD REST APIs for Accounts in EasyBank"
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountsController {

        private final IAccountService accountService;
        private final Environment environment;
        private final ContactInfoDto contactInfoDto;

        @Value("${build.version}")
        private String buildVersion;

        @Operation(
                summary = "Create Account REST API",
                description = "REST API to create new Customer &  Account inside EazyBank"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "201",
                        description = "HTTP Status CREATED"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class)
                        )
                )
        })
        @PostMapping("/create")
        public ResponseEntity<SuccessResponseDto> createAccount(
                @Valid @RequestBody CustomerDto customerDto
        ) {
                accountService.createAccount(customerDto);

                SuccessResponseDto successResponse = new SuccessResponseDto();
                successResponse.setStatusCode(AccountConstants.STATUS_201);
                successResponse.setMessage(AccountConstants.MESSAGE_201);
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(successResponse);
        }

        @Operation(
                summary = "Fetch Account Details REST API",
                description = "REST API to fetch Customer &  Account details based on a mobile number"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "HTTP Status OK"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class)
                        )
                )
        })
        @GetMapping("/fetch")
        public ResponseEntity<CustomerDto> fetchAccount(
                @RequestParam
                @Pattern(
                        regexp = "(^$|[0-9]{10})",
                        message = "Mobile number must be 10 digits"
                )
                String mobileNumber
        ) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(accountService.findByMobileNumber(mobileNumber));
        }

        @Operation(
                summary = "Update Account Details REST API",
                description = "REST API to update Customer &  Account details based on a account number"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "HTTP Status OK"
                ),
                @ApiResponse(
                        responseCode = "417",
                        description = "Expectation Failed"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class)
                        )
                )
        })
        @PutMapping("/update")
        public ResponseEntity<SuccessResponseDto> updateAccountDetails(
                @Valid @RequestBody CustomerDto customerDto
        ) {
                boolean isUpdated = accountService.updateAccount(customerDto);

                SuccessResponseDto successResponse = new SuccessResponseDto();
                if (isUpdated) {
                        successResponse.setStatusCode(AccountConstants.STATUS_200);
                        successResponse.setMessage(AccountConstants.MESSAGE_200);
                        return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(successResponse);
                } else {
                        successResponse.setStatusCode(AccountConstants.STATUS_500);
                        successResponse.setMessage(AccountConstants.MESSAGE_500);
                        return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(successResponse);
                }
        }

        @Operation(
                summary = "Delete Account & Customer Details REST API",
                description = "REST API to delete Customer &  Account details based on a mobile number"
        )
        @ApiResponses({
                @ApiResponse(
                        responseCode = "200",
                        description = "HTTP Status OK"
                ),
                @ApiResponse(
                        responseCode = "417",
                        description = "Expectation Failed"
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "HTTP Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class)
                        )
                )
        })
        @DeleteMapping("/delete")
        public ResponseEntity<SuccessResponseDto> deleteAccount(
                @RequestParam
                @Pattern(
                        regexp = "(^$|[0-9]{10})",
                        message = "Mobile number must be 10 digits"
                )
                String mobileNumber
        ) {
                boolean isDeleted = accountService.deleteAccount(mobileNumber);

                SuccessResponseDto successResponse = new SuccessResponseDto();
                if (isDeleted) {
                        successResponse.setStatusCode(AccountConstants.STATUS_200);
                        successResponse.setMessage(AccountConstants.MESSAGE_200);
                        return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(successResponse);
                } else {
                        successResponse.setStatusCode(AccountConstants.STATUS_500);
                        successResponse.setMessage(AccountConstants.MESSAGE_500);
                        return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(successResponse);
                }
        }

        @GetMapping("/build-info")
        public ResponseEntity<String> getBuildVersion() {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(buildVersion);
        }

        @GetMapping("/java-version")
        public ResponseEntity<String> getJavaVersion() {
                // In a production application, JAVA_HOME will show you the path at which java is installed
                // If you use System.getProperty("java.version"), you can get the java version
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(environment.getProperty("JAVA_HOME"));
        }

        @GetMapping("/contact-info")
        public ResponseEntity<ContactInfoDto> getContactInfo() {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(contactInfoDto);
        }
}
