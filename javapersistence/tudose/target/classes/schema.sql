CREATE TABLE IF NOT EXISTS users
(
    id              BIGINT       NOT NULL PRIMARY KEY,
    username        VARCHAR(15)  NOT NULL UNIQUE,
    address_street  VARCHAR(255) NOT NULL,
    address_zipcode VARCHAR(5)   NOT NULL,
    address_city    VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS billing_details
(
    id       BIGINT       NOT NULL PRIMARY KEY,
    account  VARCHAR(15)  NOT NULL,
    bankname VARCHAR(255) NOT NULL,
    user_id  BIGINT       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS user_billing_details
(
    user_id           BIGINT,
    billing_details_id BIGINT,
    PRIMARY KEY (user_id, billing_details_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (billing_details_id) REFERENCES billing_details (id)
);
