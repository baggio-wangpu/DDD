CREATE TABLE IF NOT EXISTS bm_training_camp_student_mapping (
  id BIGINT NOT NULL,
  training_camp_id BIGINT NOT NULL,
  student_id VARCHAR(64) NOT NULL,
  PRIMARY KEY  (id),
  INDEX `ix_bm_student`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;