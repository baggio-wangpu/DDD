ALTER TABLE bm_training_camp MODIFY client_name VARCHAR(64) NULL;
ALTER TABLE bm_training_camp MODIFY status VARCHAR(64) NOT NULL;

ALTER TABLE bm_task MODIFY sort_number int(2) NOT NULL;