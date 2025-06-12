CREATE TABLE IF NOT EXISTS purchases
(
    purchase_id   TINYINT AUTO_INCREMENT PRIMARY KEY,
    product_name  VARCHAR(50) NOT NULL,
    product_price DOUBLE      NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts
(
    account_id                    TINYINT AUTO_INCREMENT PRIMARY KEY,
    account_unique_identifier_key BINARY(16) DEFAULT (UUID_TO_BIN(UUID(), true)) NOT NULL UNIQUE,
    account_holder_name           VARCHAR(50)                                    NOT NULL,
    account_balance               DOUBLE                                         NOT NULL
);