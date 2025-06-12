ALTER TABLE persons
    ADD role_id bigint
GO

ALTER TABLE persons
    ADD CONSTRAINT  FK_PERSONS_ON_ROLE
    FOREIGN KEY     (role_id)
    REFERENCES      roles (id)
GO
