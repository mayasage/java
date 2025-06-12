CREATE TABLE holidays
(
    id         bigint IDENTITY (1, 1) NOT NULL,
    created_at datetime,
    created_by varchar(255),
    updated_at datetime,
    updated_by varchar(255),
    day        varchar(255),
    reason     varchar(255),
    type       varchar(255),
    CONSTRAINT pk_holidays PRIMARY KEY (id)
)
GO
