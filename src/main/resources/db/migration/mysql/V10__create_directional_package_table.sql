CREATE TABLE IF NOT EXISTS bm_directional_package (
  id BIGINT NOT NULL,
  name VARCHAR(64) NOT NULL,
  description VARCHAR(255) NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;