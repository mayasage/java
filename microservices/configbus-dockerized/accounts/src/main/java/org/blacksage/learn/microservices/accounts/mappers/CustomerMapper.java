package org.blacksage.learn.microservices.accounts.mappers;

import org.blacksage.learn.microservices.accounts.dtos.CustomerDto;
import org.blacksage.learn.microservices.accounts.models.Customer;

public final class CustomerMapper {

        private CustomerMapper() {
        }

        public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
                customerDto.setName(customer.getName());
                customerDto.setEmail(customer.getEmail());
                customerDto.setMobileNumber(customer.getMobileNumber());
                return customerDto;
        }

        public static CustomerDto mapToCustomerDto(Customer customer) {
                CustomerDto customerDto = new CustomerDto();
                return mapToCustomerDto(customer, customerDto);
        }

        public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
                customer.setName(customerDto.getName());
                customer.setEmail(customerDto.getEmail());
                customer.setMobileNumber(customerDto.getMobileNumber());
                return customer;
        }
}
