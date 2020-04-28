delete from bm_teacher where id = 'teach@email.com';
INSERT INTO bm_teacher(id) values ('teach@email.com');

delete from bm_student where id = 'student@email.com';
INSERT INTO bm_student values ('student@email.com');

delete from bm_student where id = 'teachstudent@email.com';
INSERT INTO bm_student values ('teachstudent@email.com');

delete from bm_teacher where id = 'teachstudent@email.com';
INSERT INTO bm_teacher(id) values ('teachstudent@email.com');