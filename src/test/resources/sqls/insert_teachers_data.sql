delete from bm_teacher where id = 'teacher@email.com';
INSERT INTO bm_teacher(id, name) values ('teacher@email.com', 'teacher');
delete from bm_teacher where id = 'teacher2@email.com';
INSERT INTO bm_teacher(id, name, avatar) values ('teacher2@email.com', 'teacher2', 'avatar');

delete from bm_student where id = 'student@email.com';
INSERT INTO bm_student(id) values ('student@email.com');
delete from bm_student where id = 'student2@email.com';
INSERT INTO bm_student(id) values ('student2@email.com');

delete from bm_training_camp where id = 1;
INSERT INTO bm_training_camp(id, name, client_name, status, start_time, end_time) values (1, 'camp1', 'TW', 'ACTIVE', NOW(), NOW());

delete from bm_training_camp where id = 2;
INSERT INTO bm_training_camp(id, name, client_name, status, start_time, end_time) values (2, 'camp2', 'TW', 'DELETED', NOW(), NOW());

select sleep(1);

delete from bm_training_camp where id = 3;
INSERT INTO bm_training_camp(id, name, client_name, status, start_time, end_time) values (3, 'camp3', 'TW', 'ACTIVE', NOW(), NOW());

delete from bm_task where id = 1;
INSERT INTO bm_task(id, name, training_camp_id, sort_number, visible) values (1, 'task1', 3, 1, 1);
delete from bm_task where id = 2;
INSERT INTO bm_task(id, name, training_camp_id, sort_number, visible) values (2, 'task2', 3, 2, 1);

delete from bm_training_camp_teacher_mapping where teacher_id = 'teacher@email.com';
delete from bm_training_camp_teacher_mapping where teacher_id = 'teacher2@email.com';
INSERT INTO bm_training_camp_teacher_mapping(id, teacher_id, training_camp_id) values (1, 'teacher@email.com', 1);
INSERT INTO bm_training_camp_teacher_mapping(id, teacher_id, training_camp_id) values (2, 'teacher@email.com', 2);
INSERT INTO bm_training_camp_teacher_mapping(id, teacher_id, training_camp_id) values (3, 'teacher@email.com', 3);
INSERT INTO bm_training_camp_teacher_mapping(id, teacher_id, training_camp_id) values (4, 'teacher2@email.com', 3);

delete from bm_training_camp_student_mapping where student_id = 'student@email.com';
delete from bm_training_camp_student_mapping where student_id = 'student2@email.com';

INSERT INTO bm_training_camp_student_mapping(id, student_id, training_camp_id) values (1, 'student@email.com', 1);
INSERT INTO bm_training_camp_student_mapping(id, student_id, training_camp_id) values (2, 'student@email.com', 3);
INSERT INTO bm_training_camp_student_mapping(id, student_id, training_camp_id) values (3, 'student2@email.com', 3);
