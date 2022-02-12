-- users script
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('9mark', 'Mark', 'Richards', 'markrichards@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'PROFESSOR');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('9srinath', 'Srinath', 'Jagarlamudi', 'jagarlamudisrinath99@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('9mahesh', 'Mahesh', 'Kumar', 'mahesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('9suresh', 'Suresh', 'Kaproo', 'suresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('9naresh', 'Naresh', 'Reddy', 'naresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('9vijay', 'Vijay', 'Naidu', 'vijay@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('9sahil', 'Sahil', 'Seth', 'sahil@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('9raja', 'Raja', 'Simha', 'raja@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('9ganesh', 'Ganesh', 'Mekala', 'ganesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('9harish', 'Harish', 'Thumma', 'harish@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');

-- class script
insert into classes (id, prof_id, ga_id, title) values ('9-CS', '9mark', '9srinath', 'Data Structures');

-- class_students script
insert into class_students (id, student_id, class_id) values ('9-CS-9srinath', '9srinath', '9-CS');
insert into class_students (id, student_id, class_id) values ('9-CS-8mahesh', '9mahesh', '9-CS');
insert into class_students (id, student_id, class_id) values ('9-CS-8naresh', '9naresh', '9-CS');
insert into class_students (id, student_id, class_id) values ('9-CS-8suresh', '9suresh', '9-CS');
insert into class_students (id, student_id, class_id) values ('9-CS-8sahil', '9sahil', '9-CS');
insert into class_students (id, student_id, class_id) values ('9-CS-8vijay', '9vijay', '9-CS');

-- assignments
insert into assignments(id, class_id, title, no_of_groups, created_by) values ('9-CS-9-AS', '9-CS', 'What is Encapsulation?', 3, '9mark');

insert into groups(id, group_id, assignment_id, answer, active) values ('9-CS-9-AS-g1', 'g1', '9-CS-9-AS', 'test',  true);

insert into group_students(id, group_id, student_id) values ('9-CS-9-AS-g1-9srinath', '9-CS-9-AS-g1', '9srinath');
insert into group_students(id, group_id, student_id) values ('9-CS-9-AS-g1-9mahesh', '9-CS-9-AS-g1', '9mahesh');
