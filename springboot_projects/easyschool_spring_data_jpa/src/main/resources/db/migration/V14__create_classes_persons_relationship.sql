CREATE TABLE classes
(
    id         bigint IDENTITY (1, 1) NOT NULL,
    created_at datetime,
    created_by varchar(255),
    updated_at datetime,
    updated_by varchar(255),
    name       varchar(255)           NOT NULL,
    CONSTRAINT pk_classes PRIMARY KEY (id)
)
GO

ALTER TABLE persons
    ADD easy_class_id bigint
GO

ALTER TABLE classes
    ADD CONSTRAINT uc_classes_name UNIQUE (name)
GO

ALTER TABLE persons
    ADD CONSTRAINT uc_persons_email UNIQUE (email)
GO

ALTER TABLE persons
    ADD CONSTRAINT FK_PERSONS_ON_CLASSES
    FOREIGN KEY (easy_class_id) REFERENCES classes (id)
GO