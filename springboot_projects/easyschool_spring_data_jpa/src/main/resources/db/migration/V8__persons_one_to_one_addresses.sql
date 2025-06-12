ALTER TABLE persons
    ADD address_id bigint
GO

ALTER TABLE persons
    ADD CONSTRAINT  FK_PERSONS_ON_ADDRESS
    FOREIGN KEY     (address_id)
    REFERENCES      addresses (id)
GO
