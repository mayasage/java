CREATE TABLE persons
(
    id            bigint IDENTITY (1, 1) NOT NULL,
    created_at    datetime,
    created_by    varchar(255),
    updated_at    datetime,
    updated_by    varchar(255),
    name          varchar(255),
    mobile_number varchar(255),
    email         varchar(255),
    password      varchar(255),
    CONSTRAINT pk_persons PRIMARY KEY (id)
)
GO