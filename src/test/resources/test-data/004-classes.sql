-- users script
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('4mark', 'Mark', 'Richards', 'markrichards@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'PROFESSOR');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('4steve', 'Steve', 'Smith', 'steve@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'PROFESSOR');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('4srinath', 'Srinath', 'Jagarlamudi', 'jagarlamudisrinath99@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('4mahesh', 'Mahesh', 'Kumar', 'mahesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('4suresh', 'Suresh', 'Kaproo', 'suresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('4naresh', 'Naresh', 'Reddy', 'naresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('4vijay', 'Vijay', 'Naidu', 'vijay@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('4sahil', 'Sahil', 'Seth', 'sahil@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('4raja', 'Raja', 'Simha', 'raja@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('4ganesh', 'Ganesh', 'Mekala', 'ganesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('4harish', 'Harish', 'Thumma', 'harish@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');

-- class script
insert into classes (id, prof_id, ga_id, title) values ('4-CS-1', '4mark', '4srinath', 'Data Structures');
insert into classes (id, prof_id, ga_id, title) values ('4-CS-2', '4mark', '4srinath', 'Compilers');
insert into classes (id, prof_id, ga_id, title) values ('4-CS-3', '4steve', '4srinath', 'Algorithms');

