package org.blacksage.learn.microservices.loans.services;

import org.blacksage.learn.microservices.loans.dtos.LoanDto;

public interface ILoanService {

        void createLoan(String mobileNumber);

        LoanDto findLoanByMobileNumber(String mobileNumber);

        void updateLoan(LoanDto loanDto);

        void deleteLoan(String mobileNumber);
}
