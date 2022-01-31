CREATE TABLE IF NOT EXISTS users
(
    id         TEXT PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    email      TEXT,
    password   TEXT,
    type       TEXT,
    created_by TEXT,
    updated_by TEXT,
    created_on date,
    updated_on date
);

CREATE TABLE IF NOT EXISTS classes
(
    id          TEXT PRIMARY KEY,
    description TEXT,
    prof_id     TEXT references users (id),
    created_by  TEXT,
    updated_by  TEXT,
    created_on  date,
    updated_on  date
);

CREATE TABLE IF NOT EXISTS class_students
(
    ID         text PRIMARY KEY,
    student_id TEXT REFERENCES users (id),
    class_id   TEXT REFERENCES classes (id),
    created_by TEXT,
    updated_by TEXT,
    created_on date,
    updated_on date,
    UNIQUE (student_id, class_id)
);

CREATE TABLE IF NOT EXISTS assignments
(
    id           TEXT PRIMARY KEY,
    class_id     TEXT REFERENCES classes (id) not null,
    question     TEXT                         not null,
    no_of_groups int,
    created_by   TEXT,
    updated_by   TEXT,
    created_on   date,
    updated_on   date
);

CREATE TABLE IF NOT EXISTS groups
(
    id            text PRIMARY KEY,
    group_id      TEXT not null,
    assignment_id TEXT not null REFERENCES assignments (id),
    answer        TEXT,
    active        boolean,
    created_by    TEXT,
    updated_by    TEXT,
    created_on    date,
    updated_on    date,
    UNIQUE (assignment_id, group_id)
);

CREATE TABLE IF NOT EXISTS group_students
(
    group_id   TEXT REFERENCES groups (ID) not null,
    student_id TEXT REFERENCES users (id)  not null,
    created_by TEXT,
    updated_by TEXT,
    created_on date,
    updated_on date,
    UNIQUE (student_id, group_id)
);

CREATE TABLE IF NOT EXISTS group_gas
(
    group_id   TEXT REFERENCES groups (ID) not null,
    ga_id      text REFERENCES users (id)  not null,
    active     boolean                     not null,
    created_by TEXT,
    updated_by TEXT,
    created_on date,
    updated_on date,
    UNIQUE (ga_id, group_id)
);

CREATE TABLE IF NOT EXISTS group_discussions
(
    id         text primary key,
    group_id   TEXT REFERENCES groups (ID) not null,
    chat       TEXT                        not null,
    created_by TEXT                        NOT NULL,
    created_on date                        NOT NULL
);

CREATE TABLE IF NOT EXISTS assignment_answer
(
    id               text primary key,
    group_id         TEXT REFERENCES groups (ID) not null,
    answer           TEXT                        not null,
    student_approved boolean,
    ga_approved      boolean,
    created_by       TEXT                        NOT NULL,
    created_on       date                        NOT NULL
);

CREATE TABLE IF NOT EXISTS student_approved_answer
(
    id         text primary key,
    answer     TEXT REFERENCES assignment_answer (ID) not null,
    student_id text references users (id)             not null,
    accepted   boolean                                not null,
    created_on date                                   NOT NULL
);

CREATE TABLE IF NOT EXISTS ga_approved_answer
(
    id         text primary key,
    answer     TEXT REFERENCES assignment_answer (ID) not null,
    ga_id      text references users (id)             not null,
    accepted   boolean                                not null,
    created_on date                                   NOT NULL
);