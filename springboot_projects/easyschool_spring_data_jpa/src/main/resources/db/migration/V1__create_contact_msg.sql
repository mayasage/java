CREATE TABLE contact_msg
(
    id         bigint IDENTITY (1, 1) NOT NULL,
    created_at datetime,
    created_by varchar(255),
    updated_at datetime,
    updated_by varchar(255),
    name       varchar(255),
    mobile_num varchar(255),
    email      varchar(255),
    subject    varchar(255),
    message    varchar(255),
    status     varchar(255),
    CONSTRAINT pk_contact_msg PRIMARY KEY (id)
)
GO