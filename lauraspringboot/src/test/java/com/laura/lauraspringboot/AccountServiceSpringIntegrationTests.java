package com.laura.lauraspringboot;

import com.laura.lauraspringboot.models.Account;
import com.laura.lauraspringboot.repositories.AccountRepository;
import com.laura.lauraspringboot.services.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;

@SpringBootTest
public class AccountServiceSpringIntegrationTests {
        @MockitoBean
        private AccountRepository accountRepository;

        @Autowired
        private AccountService accountService;

        @Test
        public void transferMoneyHappyFlow() {
                Account senderAccount = new Account();
                senderAccount.setAccountId(1);
                senderAccount.setAccountBalance(new BigDecimal(1000));

                Account receiverAccount = new Account();
                receiverAccount.setAccountId(2);
                receiverAccount.setAccountBalance(new BigDecimal(1000));

                BDDMockito.given(accountService.findAccountByAccountId(senderAccount.getAccountId())).willReturn(senderAccount);
                BDDMockito.given(accountRepository.findAccountByAccountId(receiverAccount.getAccountId())).willReturn(receiverAccount);

                accountService.transferMoney(senderAccount.getAccountId(), receiverAccount.getAccountId(), new BigDecimal(100));

                BDDMockito.verify(accountRepository).modifyAccountBalanceByAccountId(senderAccount.getAccountId(), new BigDecimal(900));
                BDDMockito.verify(accountRepository).modifyAccountBalanceByAccountId(receiverAccount.getAccountId(), new BigDecimal(1100));
        }
}
