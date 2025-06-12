INSERT INTO persons (name,
                     mobile_number,
                     email,
                     password,
                     role_id,
                     created_at,
                     created_by)
VALUES ('Admin',
        '1234567890',
        'admin@easyschool.com',
        'admin',
        1,
        GETDATE(),
        'SYSTEM');
