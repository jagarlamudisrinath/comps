-- users script
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('mark', 'Mark', 'Richards', 'markrichards@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'PROFESSOR');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('srinath', 'Srinath', 'Jagarlamudi', 'jagarlamudisrinath99@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('mahesh', 'Mahesh', 'Kumar', 'mahesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('suresh', 'Suresh', 'Kaproo', 'suresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('naresh', 'Naresh', 'Reddy', 'naresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('vijay', 'Vijay', 'Naidu', 'vijay@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('sahil', 'Sahil', 'Seth', 'sahil@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('raja', 'Raja', 'Simha', 'raja@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('ganesh', 'Ganesh', 'Mekala', 'ganesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('harish', 'Harish', 'Thumma', 'harish@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');

-- class script
insert into classes (id, prof_id, ga_id, title) values ('2022-CS002', 'mark', 'srinath', 'Data Structures');

-- class_students script
insert into class_students (id, student_id, class_id) values ('2022-CS002-srinath', 'srinath', '2022-CS002');
insert into class_students (id, student_id, class_id) values ('2022-CS002-mahesh', 'mahesh', '2022-CS002');
insert into class_students (id, student_id, class_id) values ('2022-CS002-naresh', 'naresh', '2022-CS002');
insert into class_students (id, student_id, class_id) values ('2022-CS002-suresh', 'suresh', '2022-CS002');
insert into class_students (id, student_id, class_id) values ('2022-CS002-sahil', 'sahil', '2022-CS002');
insert into class_students (id, student_id, class_id) values ('2022-CS002-vijay', 'vijay', '2022-CS002');

-- assignments
insert into assignments(id, class_id, question, no_of_groups, created_by) values ('2022-CS002-001', '2022-CS002', 'What is Encapsulation?', 3, 'mark');

insert into groups(id, group_id, assignment_id, answer, active) values ('2022-CS002-001-g1', 'g1', '2022-CS002-001', 'test',  true);
insert into groups(id, group_id, assignment_id, answer, active) values ('2022-CS002-001-g2', 'g2', '2022-CS002-001', 'test',  true);
insert into groups(id, group_id, assignment_id, answer, active) values ('2022-CS002-001-g3', 'g3', '2022-CS002-001', 'test',  true);

insert into group_students(id, group_id, student_id) values ('2022-CS002-001-g1-srinath', '2022-CS002-001-g1', 'srinath');
insert into group_students(id, group_id, student_id) values ('2022-CS002-001-g2-mahesh', '2022-CS002-001-g2', 'mahesh');