INSERT IGNORE INTO accounts
    (account_unique_identifier_key, account_holder_name, account_balance)
VALUES (UUID_TO_BIN("69d9898f-fc85-11ef-a6bb-0242ac110002", true), "Maya", 1000),
       (UUID_TO_BIN("d05ad544-fc85-11ef-a6bb-0242ac110002", true), "Lilian", 1000);