package mayasage.spring_start_here.services;

import mayasage.spring_start_here.exceptions.AccountNotFoundException;
import mayasage.spring_start_here.models.Account;
import mayasage.spring_start_here.repositories.AccountRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Data
public class AccountService {
//        private final AccountRepositoryWithJdbcTemplate accountRepositoryWithJdbcTemplate;

//        @Transactional
//        public void transferMoney(int senderAccountId, int receiverAccountId, BigDecimal amount) {
//                Account senderAccount = accountRepositoryWithJdbcTemplate.findAccountByAccountId(senderAccountId);
//                Account receiverAccount = accountRepositoryWithJdbcTemplate.findAccountByAccountId(receiverAccountId);
//                BigDecimal newSenderAmount = senderAccount.getAccountBalance().subtract(amount);
//                BigDecimal newReceiverAmount = receiverAccount.getAccountBalance().add(amount);
//                accountRepositoryWithJdbcTemplate.modifyAccountBalanceByAccountId(senderAccountId, newSenderAmount);
//                accountRepositoryWithJdbcTemplate.modifyAccountBalanceByAccountId(receiverAccountId, newReceiverAmount);
//                throw new RuntimeException();
//        }
//
//        public Account findAccountByAccountId(int accountId) {
//                return accountRepositoryWithJdbcTemplate.findAccountByAccountId(accountId);
//        }
//
//        public Account findAccountByAccountUniqueIdentifierKey(String accountUniqueIdentifierKey) {
//                return accountRepositoryWithJdbcTemplate.findAccountByAccountUniqueIdentifierKey(accountUniqueIdentifierKey);
//        }
//
//        public List<Account> findAllAccounts() {
//                return accountRepositoryWithJdbcTemplate.findAllAccounts();
//        }

        private final AccountRepository accountRepository;

        @Transactional
        public void transferMoney(int senderAccountId, int receiverAccountId, BigDecimal amount) {
                Account senderAccount = accountRepository.findAccountByAccountId(senderAccountId);
                if (senderAccount == null) throw new AccountNotFoundException();
                Account receiverAccount = accountRepository.findAccountByAccountId(receiverAccountId);
                if (receiverAccount == null) throw new AccountNotFoundException();
                BigDecimal newSenderAmount = senderAccount.getAccountBalance().subtract(amount);
                BigDecimal newReceiverAmount = receiverAccount.getAccountBalance().add(amount);
                accountRepository.modifyAccountBalanceByAccountId(senderAccountId, newSenderAmount);
                accountRepository.modifyAccountBalanceByAccountId(receiverAccountId, newReceiverAmount);
        }

        public Account findAccountByAccountId(int accountId) {
                return accountRepository.findAccountByAccountId(accountId);
        }

        public Account findAccountByAccountUniqueIdentifierKey(String accountUniqueIdentifierKey) {
                return accountRepository.findAccountByAccountUniqueIdentifierKey(accountUniqueIdentifierKey);
        }

        public List<Account> findAllAccounts() {
                return accountRepository.findAllAccounts();
        }
}
