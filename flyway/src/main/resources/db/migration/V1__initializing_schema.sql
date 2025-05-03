
CREATE SEQUENCE         book_id_sequence
START WITH              1
INCREMENT BY            50;

CREATE TABLE books (

        id      BIGINT  NOT NULL        DEFAULT nextval('book_id_sequence'),
        isbn    TEXT    NOT NULL,
        title   TEXT    NOT NULL,

        PRIMARY KEY (id),

        CONSTRAINT      book_isbn_uq    UNIQUE(isbn)
);
