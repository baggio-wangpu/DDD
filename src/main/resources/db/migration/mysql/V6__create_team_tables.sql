CREATE TABLE IF NOT EXISTS bm_team (
  id BIGINT NOT NULL,
  training_camp_id BIGINT NOT NULL,
  name varchar(128) NULL,
  PRIMARY KEY  (id),
  INDEX `ix_bm_team`(`id`,`training_camp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS bm_team_student_mapping (
  id BIGINT NOT NULL,
  team_id BIGINT NOT NULL,
  student_id varchar(64) NOT NULL,
  PRIMARY KEY  (id),
  INDEX `ix_bm_team_student_mapping`(`team_id`,`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

ALTER TABLE bm_training_camp_teacher_mapping DROP INDEX `ix_bm_student`;
ALTER TABLE bm_training_camp_teacher_mapping ADD INDEX `ix_bm_training_camp_teacher_mapping`(`training_camp_id`,`teacher_id`);

ALTER TABLE bm_task ADD COLUMN training_camp_id BIGINT NOT NULL;
ALTER TABLE bm_task DROP INDEX `ix_bm_task`;
ALTER TABLE bm_task ADD INDEX `ix_bm_task`(`training_camp_id`,`id`);