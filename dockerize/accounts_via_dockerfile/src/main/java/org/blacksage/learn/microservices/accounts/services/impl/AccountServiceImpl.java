package org.blacksage.learn.microservices.accounts.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.blacksage.learn.microservices.accounts.constants.AccountConstants;
import org.blacksage.learn.microservices.accounts.dtos.AccountDto;
import org.blacksage.learn.microservices.accounts.dtos.CustomerDto;
import org.blacksage.learn.microservices.accounts.exceptions.CustomerAlreadyExistsException;
import org.blacksage.learn.microservices.accounts.exceptions.ResourceNotFoundException;
import org.blacksage.learn.microservices.accounts.mappers.AccountMapper;
import org.blacksage.learn.microservices.accounts.mappers.CustomerMapper;
import org.blacksage.learn.microservices.accounts.models.Account;
import org.blacksage.learn.microservices.accounts.models.Customer;
import org.blacksage.learn.microservices.accounts.repositories.AccountRepository;
import org.blacksage.learn.microservices.accounts.repositories.CustomerRepository;
import org.blacksage.learn.microservices.accounts.services.IAccountService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements IAccountService {

        private final AccountRepository accountRepository;
        private final CustomerRepository customerRepository;

        @Override
        public void createAccount(CustomerDto customerDto) {
                if (customerRepository.existsByMobileNumber(customerDto.getMobileNumber())) {
                        throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDto.getMobileNumber() + " already exists.");
                }
                Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
                Customer savedCustomer = customerRepository.save(customer);
                accountRepository.save(createNewAccount(savedCustomer));
        }

        @Override
        public CustomerDto findByMobileNumber(String mobileNumber) {
                Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer",
                                        "mobileNumber",
                                        mobileNumber));

                Account account = accountRepository.findByCustomerId(customer.getCustomerId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Account",
                                        "customerId",
                                        customer.getCustomerId()));

                CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer);
                customerDto.setAccountDto(AccountMapper.mapToAccountDto(account));
                return customerDto;
        }

        @Override
        public boolean updateAccount(CustomerDto customerDto) {
                boolean isUpdated = false;
                AccountDto accountDto = customerDto.getAccountDto();
                Account account =
                        accountRepository
                                .findById(accountDto.getAccountNumber())
                                .orElseThrow(() ->
                                        new ResourceNotFoundException(
                                                "Account",
                                                "accountNumber",
                                                accountDto.getAccountNumber()));

                AccountMapper.mapToAccount(accountDto, account);
                account = accountRepository.save(account);
                Long customerId = account.getCustomerId();
                Customer customer = customerRepository.findById(customerId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer",
                                        "customerId",
                                        customerId));
                CustomerMapper.mapToCustomer(customerDto, customer);
                isUpdated = true;
                customerRepository.save(customer);

                return isUpdated;
        }

        @Override
        public boolean deleteAccount(String mobileNumber) {

                Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer",
                                        "mobileNumber",
                                        mobileNumber));

                accountRepository.deleteByCustomerId(customer.getCustomerId());
                customerRepository.deleteById(customer.getCustomerId());

                return true;
        }

        private Account createNewAccount(Customer customer) {
                Account newAccount = new Account();
                newAccount.setCustomerId(customer.getCustomerId());
                long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

                newAccount.setAccountNumber(randomAccountNumber);
                newAccount.setAccountType(AccountConstants.SAVINGS);
                newAccount.setBranchAddress(AccountConstants.ADDRESS);
                return newAccount;
        }
}
