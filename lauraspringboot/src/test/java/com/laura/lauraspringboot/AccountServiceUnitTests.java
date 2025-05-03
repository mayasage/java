package com.laura.lauraspringboot;

import com.laura.lauraspringboot.models.Account;
import com.laura.lauraspringboot.repositories.AccountRepository;
import com.laura.lauraspringboot.services.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class AccountServiceUnitTests {
        @Test
        @DisplayName("Test that money is transferred from one account to another in a happy flow.")
        public void transferMoneyHappyFlow() {
                AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
                AccountService accountService = new AccountService(accountRepository);

                Account senderAccount = new Account();
                senderAccount.setAccountId(1);
                senderAccount.setAccountBalance(new BigDecimal(1000));
                BDDMockito.given(accountRepository.findAccountByAccountId(1)).willReturn(senderAccount);

                Account receiverAccount = new Account();
                receiverAccount.setAccountId(2);
                receiverAccount.setAccountBalance(new BigDecimal(1000));
                BDDMockito.given(accountRepository.findAccountByAccountId(2)).willReturn(receiverAccount);

                accountService.transferMoney(senderAccount.getAccountId(), receiverAccount.getAccountId(), new BigDecimal(100));

                BDDMockito.verify(accountRepository).modifyAccountBalanceByAccountId(1, new BigDecimal(900));
                BDDMockito.verify(accountRepository).modifyAccountBalanceByAccountId(2, new BigDecimal(1100));
        }
}
