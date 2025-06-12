IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='contact_msg' AND xtype='U')
CREATE TABLE contact_msg (
                             id INT IDENTITY(1,1) PRIMARY KEY,
                             name VARCHAR(100) NOT NULL,
                             mobile_num VARCHAR(10) NOT NULL,
                             email VARCHAR(100) NOT NULL,
                             subject VARCHAR(100) NOT NULL,
                             message VARCHAR(500) NOT NULL,
                             status VARCHAR(10) NOT NULL,
                             created_at DATETIME2 NOT NULL,
                             created_by VARCHAR(50) NOT NULL,
                             updated_at DATETIME2 NULL,
                             updated_by VARCHAR(50) NULL
);

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='holidays' AND xtype='U')
CREATE TABLE holidays (
                          day VARCHAR(20) NOT NULL,
                          reason VARCHAR(100) NOT NULL,
                          type VARCHAR(20) NOT NULL,
                          created_at DATETIME2 NOT NULL,
                          created_by VARCHAR(50) NOT NULL,
                          updated_at DATETIME2 NULL,
                          updated_by VARCHAR(50) NULL
);
