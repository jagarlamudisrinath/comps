-- users script
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('6mark', 'Mark', 'Richards', 'markrichards@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'PROFESSOR');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('6srinath', 'Srinath', 'Jagarlamudi', 'jagarlamudisrinath99@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');

-- class script
insert into classes (id, prof_id, ga_id, title) values ('6-2022-CS001', '6mark', '6srinath', 'Algorithms');

insert into assignments(id, class_id, title, no_of_groups, created_by) values ('6-2022-CS001-001', '6-2022-CS001', 'What is Object Oriented Design?', 3, '6mark');