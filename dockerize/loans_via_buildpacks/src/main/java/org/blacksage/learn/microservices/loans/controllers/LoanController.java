package org.blacksage.learn.microservices.loans.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.blacksage.learn.microservices.loans.constants.MessageConstants;
import org.blacksage.learn.microservices.loans.dtos.LoanDto;
import org.blacksage.learn.microservices.loans.dtos.ResponseDto;
import org.blacksage.learn.microservices.loans.services.ILoanService;
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

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class LoanController {

        private final ILoanService iLoanService;

        @PostMapping("/create")
        public ResponseEntity<ResponseDto> createLoan(
                @RequestParam String mobileNumber
        ) {
                iLoanService.createLoan(mobileNumber);
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(ResponseDto.from(
                                MessageConstants.LOAN_CREATED_SUCCESSFULLY,
                                HttpStatus.CREATED.value()
                        ));
        }

        @GetMapping("/fetch")
        public ResponseEntity<ResponseDto> getLoanByMobileNumber(
                @RequestParam String mobileNumber
        ) {
                LoanDto loanDto = iLoanService.findLoanByMobileNumber(mobileNumber);
                return ResponseEntity.ok(
                        ResponseDto.from(
                                MessageConstants.LOAN_FETCHED_SUCCESSFULLY,
                                HttpStatus.OK.value(),
                                loanDto
                        )
                );
        }

        @PutMapping("/update")
        public ResponseEntity<ResponseDto> updateLoan(
                @Valid @RequestBody LoanDto loanDto
        ) {
                iLoanService.updateLoan(loanDto);
                return ResponseEntity.ok(
                        ResponseDto.from(
                                MessageConstants.LOAN_UPDATED_SUCCESSFULLY,
                                HttpStatus.OK.value()
                        )
                );
        }

        @DeleteMapping("/delete")
        public ResponseEntity<ResponseDto> deleteLoan(
                @RequestParam String mobileNumber
        ) {
                iLoanService.deleteLoan(mobileNumber);
                return ResponseEntity.ok(
                        ResponseDto.from(
                                MessageConstants.LOAN_DELETED_SUCCESSFULLY,
                                HttpStatus.OK.value()
                        )
                );
        }
}
