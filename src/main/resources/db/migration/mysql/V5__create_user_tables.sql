CREATE TABLE IF NOT EXISTS bm_teacher (
  id VARCHAR(64) NOT NULL,
  PRIMARY KEY  (id),
  INDEX `ix_bm_teacher`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS bm_student (
  id VARCHAR(64) NOT NULL,
  PRIMARY KEY  (id),
  INDEX `ix_bm_student`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS bm_training_camp_teacher_mapping (
  id BIGINT NOT NULL,
  training_camp_id BIGINT NOT NULL,
  teacher_id VARCHAR(64) NOT NULL,
  PRIMARY KEY  (id),
  INDEX `ix_bm_student`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;