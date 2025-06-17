package org.blacksage.learn.microservices.accounts.mappers;

import org.blacksage.learn.microservices.accounts.dtos.AccountDto;
import org.blacksage.learn.microservices.accounts.models.Account;

public final class AccountMapper {

        private AccountMapper() {
        }

        public static AccountDto mapToAccountDto(Account account, AccountDto accountDto) {
                accountDto.setAccountNumber(account.getAccountNumber());
                accountDto.setAccountType(account.getAccountType());
                accountDto.setBranchAddress(account.getBranchAddress());
                return accountDto;
        }

        public static AccountDto mapToAccountDto(Account account) {
                AccountDto accountDto = new AccountDto();
                return mapToAccountDto(account, accountDto);
        }

        public static Account mapToAccount(AccountDto accountDto, Account account) {
                account.setAccountNumber(accountDto.getAccountNumber());
                account.setAccountType(accountDto.getAccountType());
                account.setBranchAddress(accountDto.getBranchAddress());
                return account;
        }
}
