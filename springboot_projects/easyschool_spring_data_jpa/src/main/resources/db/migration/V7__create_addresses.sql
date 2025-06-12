CREATE TABLE addresses
(
    id         bigint IDENTITY (1, 1) NOT NULL,
    created_at datetime,
    created_by varchar(255),
    updated_at datetime,
    updated_by varchar(255),
    address1   varchar(255),
    address2   varchar(255),
    city       varchar(255),
    state      varchar(255),
    zip_code   int NOT NULL,
    CONSTRAINT pk_addresses PRIMARY KEY (id)
)
GO
