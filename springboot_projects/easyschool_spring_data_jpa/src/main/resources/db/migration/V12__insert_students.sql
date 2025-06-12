-- email and password are the same for convenience

INSERT INTO persons (name,
                     mobile_number,
                     email,
                     password,
                     role_id,
                     created_at,
                     created_by)

VALUES ('maya',
        '9274582190',
        'maya@blacksage.org',
        '$2a$12$skW9wvnM7Yo7BdPlHVmy3.HIgv8g0o.rv5oo1olZ4IAnzbqdeA1Tm',
        2,
        GETDATE(),
        'SYSTEM'),

       ('sage',
        '9274582910',
        'sage@blacksage.org',
        '$2a$12$9nySUMcsnvyd1blJaaOrfedqVb/kk3fb5IyJKJQSwE0ctS9.4isLe',
        2,
        GETDATE(),
        'SYSTEM');
