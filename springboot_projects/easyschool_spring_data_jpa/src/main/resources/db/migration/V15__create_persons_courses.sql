CREATE TABLE courses
(
    id         bigint IDENTITY (1, 1) NOT NULL,
    created_at datetime,
    created_by varchar(255),
    updated_at datetime,
    updated_by varchar(255),
    name       varchar(255)           NOT NULL,
    fees       bigint                 NOT NULL,
    CONSTRAINT pk_courses PRIMARY KEY (id)
)
GO

CREATE TABLE persons_courses
(
    course_id bigint NOT NULL,
    person_id bigint NOT NULL
)
GO

ALTER TABLE courses
    ADD CONSTRAINT uc_courses_name UNIQUE (name)
GO

ALTER TABLE persons_courses
    ADD CONSTRAINT uc_person_id_course_id UNIQUE (person_id, course_id)
GO

ALTER TABLE persons_courses
    ADD CONSTRAINT  fk_persons_courses_on_course
    FOREIGN KEY     (course_id)
    REFERENCES      courses (id)
GO

ALTER TABLE persons_courses
    ADD CONSTRAINT  fk_persons_courses_on_person
    FOREIGN KEY     (person_id)
    REFERENCES      persons (id)
GO

ALTER TABLE persons
    DROP CONSTRAINT uc_persons_email
GO

ALTER TABLE persons
    ALTER COLUMN email varchar(255) NOT NULL
GO

ALTER TABLE persons
    ADD CONSTRAINT uc_persons_email UNIQUE (email)
GO
