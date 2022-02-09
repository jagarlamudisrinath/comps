-- users script
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('mark', 'Mark', 'Richards', 'markrichards@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'PROFESSOR');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('srinath', 'Srinath', 'Jagarlamudi', 'jagarlamudisrinath99@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');

-- class script
insert into classes (id, prof_id, ga_id, title) values ('2022-CS001', 'mark', 'srinath', 'Algorithms');

insert into assignments(id, class_id, question, no_of_groups, created_by) values ('2022-CS001-001', '2022-CS001', 'What is Object Oriented Design?', 3, 'mark');