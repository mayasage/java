package com.laura.lauraspringboot.repositories;

import com.laura.lauraspringboot.models.Account;
import com.laura.lauraspringboot.repositories.mappers.AccountRowMapper;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

@Repository
@Data
public class AccountRepositoryWithJdbcTemplate {
        private final JdbcTemplate jdbcTemplate;
        private final Logger logger = Logger.getLogger(AccountRepositoryWithJdbcTemplate.class.getName());

        public Account findAccountByAccountUniqueIdentifierKey(String accountUniqueIdentifierKey) {
                String sql = """
                        SELECT 
                                account_id,
                                BIN_TO_UUID(account_unique_identifier_key, true) AS account_unique_identifier_key,
                                account_holder_name, 
                                account_balance
                        FROM 
                                accounts
                        WHERE 
                                account_unique_identifier_key = UUID_TO_BIN(?, true)
                        """;
                return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), accountUniqueIdentifierKey);
        }

        public Account findAccountByAccountId(int accountId) {
                String sql = """
                        SELECT 
                                account_id,
                                BIN_TO_UUID(account_unique_identifier_key, true) AS account_unique_identifier_key,
                                account_holder_name, 
                                account_balance
                        FROM 
                                accounts
                        WHERE 
                                account_id = ?
                        """;
                return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), accountId);
        }

        public void modifyAccountBalanceByAccountId(int accountId, BigDecimal newAccountBalance) {
                String sql = """
                        UPDATE accounts
                        SET account_balance = ?
                        WHERE account_id = ?
                        """;
                jdbcTemplate.update(sql, newAccountBalance, accountId);
                logger.info("account_id: " + accountId + " modified account_balance to: " + newAccountBalance);
        }

        public List<Account> findAllAccounts() {
                String sql = """
                        SELECT 
                                account_id,
                                BIN_TO_UUID(account_unique_identifier_key, true) AS account_unique_identifier_key,
                                account_holder_name, 
                                account_balance
                        FROM 
                                accounts
                        """;
                return jdbcTemplate.query(sql, new AccountRowMapper());
        }
}
