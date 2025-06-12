package mayasage.spring_start_here.controllers;

import mayasage.spring_start_here.dtos.MoneyTransferRequest;
import mayasage.spring_start_here.models.Account;
import mayasage.spring_start_here.services.AccountService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
