package com.laura.lauraspringboot.repositories.mappers;

import com.laura.lauraspringboot.models.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setAccountUniqueIdentifierKey(rs.getString("account_unique_identifier_key"));
                account.setAccountHolderName(rs.getString("account_holder_name"));
                account.setAccountBalance(rs.getBigDecimal("account_balance"));
                return account;
        }
}
