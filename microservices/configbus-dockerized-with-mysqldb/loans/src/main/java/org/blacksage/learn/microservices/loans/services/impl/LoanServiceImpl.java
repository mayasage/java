package org.blacksage.learn.microservices.loans.services.impl;

import lombok.RequiredArgsConstructor;
import org.blacksage.learn.microservices.loans.constants.LoanConstants;
import org.blacksage.learn.microservices.loans.constants.MessageConstants;
import org.blacksage.learn.microservices.loans.dtos.LoanDto;
import org.blacksage.learn.microservices.loans.exceptions.LoanAlreadyExistsException;
import org.blacksage.learn.microservices.loans.exceptions.LoanNotFoundException;
import org.blacksage.learn.microservices.loans.mappers.LoanMapper;
import org.blacksage.learn.microservices.loans.models.Loan;
import org.blacksage.learn.microservices.loans.repositories.LoanRepository;
import org.blacksage.learn.microservices.loans.services.ILoanService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService {

        private final LoanRepository loanRepository;

        @Override
        public void createLoan(String mobileNumber) {
                if (loanRepository.existsByMobileNumber(mobileNumber)) {
                        throw new LoanAlreadyExistsException(MessageConstants.LOAN_ALREADY_EXISTS);
                }
                long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
                Loan newLoan = new Loan();
                newLoan.setLoanNumber(Long.toString(randomLoanNumber));
                newLoan.setMobileNumber(mobileNumber);
                newLoan.setLoanType(LoanConstants.HOME_LOAN);
                newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
                newLoan.setAmountPaid(0);
                newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
                loanRepository.save(newLoan);
        }

        @Override
        public LoanDto findLoanByMobileNumber(String mobileNumber) {
                Loan loan =
                        loanRepository
                                .findByMobileNumber(mobileNumber)
                                .orElseThrow(() ->
                                        new LoanNotFoundException(MessageConstants.LOAN_NOT_FOUND)
                                );
                return LoanMapper.loanToLoanDto(loan);
        }

        @Override
        public void updateLoan(LoanDto loanDto) {
                Loan loan = loanRepository.findByMobileNumber(loanDto.getMobileNumber())
                        .orElseThrow(() ->
                                new LoanNotFoundException(MessageConstants.LOAN_NOT_FOUND)
                        );
                LoanMapper.loanDtoToLoan(loanDto, loan);
                loanRepository.save(loan);
        }

        @Override
        public void deleteLoan(String mobileNumber) {
                Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                        .orElseThrow(() ->
                                new LoanNotFoundException(MessageConstants.LOAN_NOT_FOUND)
                        );
                loanRepository.delete(loan);
        }
}
