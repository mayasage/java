package com.laura.lauraspringboot.controllers;

import com.laura.lauraspringboot.dtos.MoneyTransferRequest;
import com.laura.lauraspringboot.models.Account;
import com.laura.lauraspringboot.services.AccountService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Data
public class AccountController {
        private final AccountService accountService;

        @PostMapping("/transferMoneyByAccountIds")
        public void transferMoney(
                @RequestBody MoneyTransferRequest moneyTransferRequest
        ) {
                accountService.transferMoney(
                        moneyTransferRequest.getSenderAccountId(),
                        moneyTransferRequest.getReceiverAccountId(),
                        moneyTransferRequest.getTransferAmount()
                );
        }

        @GetMapping("/getByAccountUniqueIdentifierKey")
        public Account findAccountByAccountUniqueIdentifierKey(
                @RequestParam String accountUniqueIdentifierKey
        ) {
                return accountService.findAccountByAccountUniqueIdentifierKey(accountUniqueIdentifierKey);
        }

        @GetMapping("/getByAccountId")
        public Account findAccountByAccountId(
                @RequestParam int accountId
        ) {
                return accountService.findAccountByAccountId(accountId);
        }

        @GetMapping("/")
        public List<Account> findAllAccounts() {
                return accountService.findAllAccounts();
        }
}
