-- users script
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('7mark', 'Mark', 'Richards', 'markrichards@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'PROFESSOR');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('7srinath', 'Srinath', 'Jagarlamudi', 'jagarlamudisrinath99@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('7mahesh', 'Mahesh', 'Kumar', 'mahesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('7suresh', 'Suresh', 'Kaproo', 'suresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('7naresh', 'Naresh', 'Reddy', 'naresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('7vijay', 'Vijay', 'Naidu', 'vijay@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('7sahil', 'Sahil', 'Seth', 'sahil@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('7raja', 'Raja', 'Simha', 'raja@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('7ganesh', 'Ganesh', 'Mekala', 'ganesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('7harish', 'Harish', 'Thumma', 'harish@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');

-- class script
insert into classes (id, prof_id, ga_id, title) values ('7-CS', '7mark', '7srinath', 'Algorithms');

-- assignments
insert into assignments(id, class_id, question, no_of_groups, created_by) values ('7-CS-7-AS', '7-CS', 'What is Object Oriented Design?', 3, '7mark');

insert into groups(id, group_id, assignment_id, answer, active) values ('7-CS-7-AS-g2', 'g2', '7-CS-7-AS', 'test',  true);