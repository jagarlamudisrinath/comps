-- users script
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('5mark', 'Mark', 'Richards', 'markrichards@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'PROFESSOR');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('5srinath', 'Srinath', 'Jagarlamudi', 'jagarlamudisrinath99@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('5mahesh', 'Mahesh', 'Kumar', 'mahesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('5suresh', 'Suresh', 'Kaproo', 'suresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('5naresh', 'Naresh', 'Reddy', 'naresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('5vijay', 'Vijay', 'Naidu', 'vijay@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('5sahil', 'Sahil', 'Seth', 'sahil@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('5raja', 'Raja', 'Simha', 'raja@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('5ganesh', 'Ganesh', 'Mekala', 'ganesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('5harish', 'Harish', 'Thumma', 'harish@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');

-- class script
insert into classes (id, prof_id, ga_id, title) values ('5-CS', '5mark', '5srinath', 'Algorithms');
insert into classes (id, prof_id, ga_id, title) values ('5-CS2', '5mark', '5srinath', 'Algorithms');

insert into class_students (id, class_id, student_id) values ('5-CS2-5srinath', '5-CS2', '5srinath');
insert into class_students (id, class_id, student_id) values ('5-CS2-5mahesh', '5-CS2', '5mahesh');

