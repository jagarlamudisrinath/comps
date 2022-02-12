-- users script
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('8mark', 'Mark', 'Richards', 'markrichards@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'PROFESSOR');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('8srinath', 'Srinath', 'Jagarlamudi', 'jagarlamudisrinath99@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('8mahesh', 'Mahesh', 'Kumar', 'mahesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('8suresh', 'Suresh', 'Kaproo', 'suresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('8naresh', 'Naresh', 'Reddy', 'naresh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('8vijay', 'Vijay', 'Naidu', 'vijay@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('8sahil', 'Sahil', 'Seth', 'sahil@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('8raja', 'Raja', 'Simha', 'raja@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('8ganesh', 'Ganesh', 'Mekala', 'ganesh@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');
insert into users (id, first_name, last_name, email, password, type) VALUES
    ('8harish', 'Harish', 'Thumma', 'harish@gmail.com', '{bcrypt}$2a$12$FNQCtNKN5SOTzjgL9q6AS.s6YF74t4c.R84VJoW/EfxVm8651UQQS', 'STUDENT');

-- class script
insert into classes (id, prof_id, ga_id, title) values ('8-CS', '8mark', '8srinath', 'Data Structures');

-- class_students script
insert into class_students (id, student_id, class_id) values ('8-CS-8srinath', '8srinath', '8-CS');
insert into class_students (id, student_id, class_id) values ('8-CS-8mahesh', '8mahesh', '8-CS');
insert into class_students (id, student_id, class_id) values ('8-CS-8naresh', '8naresh', '8-CS');
insert into class_students (id, student_id, class_id) values ('8-CS-8suresh', '8suresh', '8-CS');
insert into class_students (id, student_id, class_id) values ('8-CS-8sahil', '8sahil', '8-CS');
insert into class_students (id, student_id, class_id) values ('8-CS-8vijay', '8vijay', '8-CS');

-- assignments
insert into assignments(id, class_id, title, no_of_groups, created_by) values ('8-CS-8-AS', '8-CS', 'What is Encapsulation?', 3, 'mark8');
insert into assignments(id, class_id, title, no_of_groups, created_by) values ('8-CS-8-AS2', '8-CS', 'What is Polymorphism?', 3, 'mark8');

insert into groups(id, group_id, assignment_id, answer, active) values ('8-CS-8-AS-g1', 'g1', '8-CS-8-AS', 'test',  true);
insert into groups(id, group_id, assignment_id, answer, active) values ('8-CS-8-AS-g2', 'g2', '8-CS-8-AS', 'test',  true);
insert into groups(id, group_id, assignment_id, answer, active) values ('8-CS-8-AS-g3', 'g3', '8-CS-8-AS', 'test',  true);

insert into groups(id, group_id, assignment_id, answer, active) values ('8-CS-8-AS2-g1', 'g1', '8-CS-8-AS2', 'test',  true);
insert into groups(id, group_id, assignment_id, answer, active) values ('8-CS-8-AS2-g2', 'g2', '8-CS-8-AS2', 'test',  true);
insert into groups(id, group_id, assignment_id, answer, active) values ('8-CS-8-AS2-g3', 'g3', '8-CS-8-AS2', 'test',  true);

insert into group_students(id, group_id, student_id) values ('8-CS-8-AS-g1-srinath8', '8-CS-8-AS-g1', '8srinath');
insert into group_students(id, group_id, student_id) values ('8-CS-8-AS-g2-mahesh8', '8-CS-8-AS-g2', '8mahesh');

SELECT * FROM users WHERE id in (select student_id from group_students where group_id = '8-CS-8-AS-g1');

SELECT * FROM users WHERE id in (select student_id from class_students where class_id = '8-CS')
                      and id not in (select student_id from group_students where group_id in (select id from groups where assignment_id = '8-CS-8-AS'));