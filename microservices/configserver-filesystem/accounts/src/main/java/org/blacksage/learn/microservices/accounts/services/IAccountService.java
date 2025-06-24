package org.blacksage.learn.microservices.accounts.services;

import org.blacksage.learn.microservices.accounts.dtos.CustomerDto;

public interface IAccountService {

        void createAccount(CustomerDto customerDto);

        CustomerDto findByMobileNumber(String mobileNumber);

        boolean updateAccount(CustomerDto customerDto);

        boolean deleteAccount(String mobileNumber);
}
