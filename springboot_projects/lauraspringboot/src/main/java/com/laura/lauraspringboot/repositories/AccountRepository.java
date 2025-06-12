package com.laura.lauraspringboot.repositories;

import com.laura.lauraspringboot.models.Account;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
        @Query("""
                    SELECT 
                            account_id,
                            BIN_TO_UUID(account_unique_identifier_key, true) AS account_unique_identifier_key,
                            account_holder_name, 
                            account_balance
                    FROM 
                            accounts
                    WHERE 
                            account_unique_identifier_key = UUID_TO_BIN(:accountUniqueIdentifierKey, true)
                """)
        Account findAccountByAccountUniqueIdentifierKey(String accountUniqueIdentifierKey);

        @Query("""
                    SELECT 
                            account_id,
                            BIN_TO_UUID(account_unique_identifier_key, true) AS account_unique_identifier_key,
                            account_holder_name, 
                            account_balance
                    FROM 
                            accounts
                    WHERE 
                            account_id = :accountId
                """)
        Account findAccountByAccountId(int accountId);

        @Query("""
                        UPDATE accounts
                        SET account_balance = :newAccountBalance
                        WHERE account_id = :accountId
                """)
        @Modifying
        void modifyAccountBalanceByAccountId(int accountId, BigDecimal newAccountBalance);

        @Query("""
                        SELECT 
                                account_id,
                                BIN_TO_UUID(account_unique_identifier_key, true) AS account_unique_identifier_key,
                                account_holder_name, 
                                account_balance
                        FROM 
                                accounts
                """)
        List<Account> findAllAccounts();
}
