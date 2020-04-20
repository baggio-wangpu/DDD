CREATE TABLE IF NOT EXISTS permissions (
                                                    id BIGINT NOT NULL,
                                                    title char(64) NOT NULL,
                                                    description text NOT NULL,
                                                    last_modified_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                    created_date       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                    PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS role_permissions (
                                                        role_id BIGINT NOT NULL,
                                                        permission_id BIGINT NOT NULL,
                                                        last_modified_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                        created_date       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                        PRIMARY KEY  (role_id,permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS roles (
                                              id BIGINT NOT NULL,
                                              title VARCHAR(128) NOT NULL,
                                              description text NOT NULL,
                                              last_modified_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                              created_date       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                              PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS user_roles (
                                                  user_id VARCHAR(50) NOT NULL,
                                                  role_id BIGINT NOT NULL,
                                                  last_modified_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                  created_date       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                  PRIMARY KEY  (user_id,role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
