CREATE TABLE roles
(
    id         bigint IDENTITY (1, 1) NOT NULL,
    created_at datetime,
    created_by varchar(255),
    updated_at datetime,
    updated_by varchar(255),
    name       varchar(255)           NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (id)
)
GO

ALTER TABLE roles
    ADD CONSTRAINT uc_roles_name UNIQUE (name)
GO