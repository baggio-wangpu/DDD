DROP TABLE bm_training_camp;
DROP TABLE bm_task;

CREATE TABLE IF NOT EXISTS bm_training_camp (
    id BIGINT NOT NULL,
    name VARCHAR(64) NOT NULL,
		client_name VARCHAR(64) NOT NULL,
    start_time  TIMESTAMP NULL,
    end_time   TIMESTAMP NULL,
		status VARCHAR(64),
		created_time       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
		last_modified_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY  (id),
    INDEX `ix_bm_trainingcamp`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS bm_task (
  id BIGINT NOT NULL,
  name VARCHAR(64) NOT NULL,
  description text null,
  sort_number int(2) null,
  visible int(1) NOT NULL,
  start_time  TIMESTAMP NULL,
  end_time   TIMESTAMP NULL,
  created_time       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
	last_modified_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (id),
  INDEX `ix_bm_task`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

