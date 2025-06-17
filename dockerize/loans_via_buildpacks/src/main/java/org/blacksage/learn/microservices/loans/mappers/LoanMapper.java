package org.blacksage.learn.microservices.loans.mappers;

import org.blacksage.learn.microservices.loans.dtos.LoanDto;
import org.blacksage.learn.microservices.loans.models.Loan;

public final class LoanMapper {

        private LoanMapper() {
                // Private constructor to prevent instantiation
        }

        public static LoanDto loanToLoanDto(Loan loan, LoanDto loanDto) {
                loanDto.setAmountPaid(loan.getAmountPaid());
                loanDto.setMobileNumber(loan.getMobileNumber());
                loanDto.setLoanType(loan.getLoanType());
                loanDto.setOutstandingAmount(loan.getOutstandingAmount());
                loanDto.setTotalLoan(loan.getTotalLoan());
                return loanDto;
        }

        public static LoanDto loanToLoanDto(Loan loan) {
                return loanToLoanDto(loan, new LoanDto());
        }

        public static Loan loanDtoToLoan(LoanDto loanDto, Loan loan) {
                loan.setAmountPaid(loanDto.getAmountPaid());
                loan.setMobileNumber(loanDto.getMobileNumber());
                loan.setLoanType(loanDto.getLoanType());
                loan.setOutstandingAmount(loanDto.getOutstandingAmount());
                loan.setTotalLoan(loanDto.getTotalLoan());
                return loan;
        }

        public static Loan loanDtoToLoan(LoanDto loanDto) {
                return loanDtoToLoan(loanDto, new Loan());
        }
}
