CREATE TABLE IF NOT EXISTS USERS
(
    ID         TEXT PRIMARY KEY,
    FIRST_NAME TEXT,
    LAST_NAME  TEXT,
    EMAIL      TEXT,
    PASSWORD   TEXT,
    TYPE       TEXT,
    CREATED_BY TEXT,
    UPDATED_BY TEXT,
    CREATED_ON DATE,
    UPDATED_ON DATE
);

CREATE TABLE IF NOT EXISTS EVENTS
(
    ID          TEXT PRIMARY KEY,
    TYPE        TEXT,
    DETAILS     TEXT,
    USER_ID     TEXT REFERENCES users(id),
    CREATED_ON  DATE
);

CREATE TABLE IF NOT EXISTS CLASSES
(
    ID          TEXT PRIMARY KEY,
    DESCRIPTION TEXT,
    PROF_ID     TEXT REFERENCES USERS (ID),
    GA_ID       TEXT REFERENCES USERS (ID),
    CREATED_BY  TEXT,
    UPDATED_BY  TEXT,
    CREATED_ON  DATE,
    UPDATED_ON  DATE
);

CREATE TABLE IF NOT EXISTS CLASS_STUDENTS
(
    ID         TEXT PRIMARY KEY,
    STUDENT_ID TEXT REFERENCES USERS (ID),
    CLASS_ID   TEXT REFERENCES CLASSES (ID),
    CREATED_BY TEXT,
    UPDATED_BY TEXT,
    CREATED_ON DATE,
    UPDATED_ON DATE,
    UNIQUE (STUDENT_ID, CLASS_ID)
);

CREATE TABLE IF NOT EXISTS ASSIGNMENTS
(
    ID           TEXT PRIMARY KEY,
    CLASS_ID     TEXT REFERENCES CLASSES (ID) NOT NULL,
    QUESTION     TEXT                         NOT NULL,
    NO_OF_GROUPS INT,
    CREATED_BY   TEXT,
    UPDATED_BY   TEXT,
    CREATED_ON   DATE,
    UPDATED_ON   DATE
);

CREATE TABLE IF NOT EXISTS GROUPS
(
    ID            TEXT PRIMARY KEY,
    GROUP_ID      TEXT NOT NULL,
    ASSIGNMENT_ID TEXT NOT NULL REFERENCES ASSIGNMENTS (ID),
    ANSWER        TEXT,
    ACTIVE        BOOLEAN,
    CREATED_BY    TEXT,
    UPDATED_BY    TEXT,
    CREATED_ON    DATE,
    UPDATED_ON    DATE,
    UNIQUE (ASSIGNMENT_ID, GROUP_ID)
);

CREATE TABLE IF NOT EXISTS GROUP_STUDENTS
(
    ID         TEXT PRIMARY KEY,
    GROUP_ID   TEXT REFERENCES GROUPS (ID) NOT NULL,
    STUDENT_ID TEXT REFERENCES USERS (ID)  NOT NULL,
    CREATED_BY TEXT,
    UPDATED_BY TEXT,
    CREATED_ON DATE,
    UPDATED_ON DATE,
    UNIQUE (STUDENT_ID, GROUP_ID)
);

CREATE TABLE IF NOT EXISTS GROUP_DISCUSSIONS
(
    ID         TEXT PRIMARY KEY,
    GROUP_ID   TEXT REFERENCES GROUPS (ID) NOT NULL,
    CHAT       TEXT                        NOT NULL,
    CREATED_BY TEXT                        NOT NULL,
    CREATED_ON DATE                        NOT NULL
);

CREATE TABLE IF NOT EXISTS ASSIGNMENT_ANSWER
(
    ID               TEXT PRIMARY KEY,
    GROUP_ID         TEXT REFERENCES GROUPS (ID) NOT NULL,
    ANSWER           TEXT                        NOT NULL,
    STUDENT_APPROVED BOOLEAN,
    GA_APPROVED      BOOLEAN,
    CREATED_BY       TEXT                        NOT NULL,
    CREATED_ON       DATE                        NOT NULL
);

CREATE TABLE IF NOT EXISTS STUDENT_APPROVED_ANSWER
(
    ID         TEXT PRIMARY KEY,
    ANSWER     TEXT REFERENCES ASSIGNMENT_ANSWER (ID) NOT NULL,
    STUDENT_ID TEXT REFERENCES USERS (ID)             NOT NULL,
    ACCEPTED   BOOLEAN                                NOT NULL,
    CREATED_ON DATE                                   NOT NULL
);

CREATE TABLE IF NOT EXISTS GA_APPROVED_ANSWER
(
    ID         TEXT PRIMARY KEY,
    ANSWER     TEXT REFERENCES ASSIGNMENT_ANSWER (ID) NOT NULL,
    GA_ID      TEXT REFERENCES USERS (ID)             NOT NULL,
    ACCEPTED   BOOLEAN                                NOT NULL,
    CREATED_ON DATE                                   NOT NULL
);

CREATE TABLE SPRING_SESSION (
                                PRIMARY_ID CHAR(36) NOT NULL,
                                SESSION_ID CHAR(36) NOT NULL,
                                CREATION_TIME BIGINT NOT NULL,
                                LAST_ACCESS_TIME BIGINT NOT NULL,
                                MAX_INACTIVE_INTERVAL INT NOT NULL,
                                EXPIRY_TIME BIGINT NOT NULL,
                                PRINCIPAL_NAME VARCHAR(100),
                                CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
                                           SESSION_PRIMARY_ID CHAR(36) NOT NULL,
                                           ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
                                           ATTRIBUTE_BYTES BYTEA NOT NULL,
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
                                           CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);
