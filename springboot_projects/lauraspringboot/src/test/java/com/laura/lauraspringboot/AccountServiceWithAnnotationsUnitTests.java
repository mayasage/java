package com.laura.lauraspringboot;

import com.laura.lauraspringboot.exceptions.AccountNotFoundException;
import com.laura.lauraspringboot.models.Account;
import com.laura.lauraspringboot.repositories.AccountRepository;
import com.laura.lauraspringboot.services.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class AccountServiceWithAnnotationsUnitTests {
        @Mock
        private AccountRepository accountRepository;

        @InjectMocks
        private AccountService accountService;

        @Test
        public void moneyTransferHappyFlow() {
                Account senderAccount = new Account();
                senderAccount.setAccountId(1);
                senderAccount.setAccountBalance(new BigDecimal(1000));
                BDDMockito.given(accountService.findAccountByAccountId(senderAccount.getAccountId())).willReturn(senderAccount);

                Account receiverAccount = new Account();
                receiverAccount.setAccountId(2);
                receiverAccount.setAccountBalance(new BigDecimal(1000));
                BDDMockito.given(accountService.findAccountByAccountId(receiverAccount.getAccountId())).willReturn(receiverAccount);

                accountService.transferMoney(senderAccount.getAccountId(), receiverAccount.getAccountId(), new BigDecimal(100));

                BDDMockito.verify(accountRepository).modifyAccountBalanceByAccountId(senderAccount.getAccountId(), new BigDecimal(900));
                BDDMockito.verify(accountRepository).modifyAccountBalanceByAccountId(receiverAccount.getAccountId(), new BigDecimal(1100));
        }

        @Test
        public void moneyTransferDestinationAccountNotFoundFlow() {
                Account senderAccount = new Account();
                senderAccount.setAccountId(1);
                senderAccount.setAccountBalance(new BigDecimal(1000));

                BDDMockito.given(accountService.findAccountByAccountId(senderAccount.getAccountId())).willReturn(senderAccount);
                BDDMockito.given(accountService.findAccountByAccountId(2)).willReturn(null);

                Assertions.assertThrows(
                        AccountNotFoundException.class,
                        () -> accountService.transferMoney(senderAccount.getAccountId(), 2, new BigDecimal(100))
                );

                BDDMockito
                        .verify(accountRepository, Mockito.never())
                        .modifyAccountBalanceByAccountId(Mockito.anyInt(), Mockito.any());
        }
}
