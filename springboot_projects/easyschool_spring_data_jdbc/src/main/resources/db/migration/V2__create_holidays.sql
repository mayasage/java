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