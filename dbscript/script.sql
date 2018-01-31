-- -----------------------------------------------------
-- TABLE : consumer_role
-- -----------------------------------------------------

CREATE TABLE consumer_role 
(
  role_id INT NOT NULL,
  role_name VARCHAR(30) DEFAULT NULL,
  role_precedence INT NOT NULL,
  role_display VARCHAR(100) DEFAULT NULL
);

ALTER TABLE consumer_role
ADD CONSTRAINT pk_consumer_role PRIMARY KEY
(
	role_id
);

INSERT INTO consumer_role (role_id, role_name, role_precedence, role_display) VALUES (1, 'ROLE_SUPER_ADMIN', 1, 'Super Admin');
INSERT INTO consumer_role (role_id, role_name, role_precedence, role_display) VALUES (2, 'ROLE_INSTRUCTOR', 100, 'Instructor');
INSERT INTO consumer_role (role_id, role_name, role_precedence, role_display) VALUES (3, 'ROLE_STUDENT', 200, 'Student');

-- -----------------------------------------------------
-- TABLE : consumer
-- -----------------------------------------------------

CREATE TABLE consumer
(
  user_id INT NOT NULL,
  name VARCHAR(100) DEFAULT NULL,
  email VARCHAR(100) DEFAULT NULL,
  password VARCHAR(100) DEFAULT NULL,
  create_date TIMESTAMP,
  invalid_login_count INT DEFAULT 0, 
  status INT NOT NULL,
  role_id INT NOT NULL
);

ALTER TABLE consumer
ADD CONSTRAINT pk_consumer PRIMARY KEY
(
	user_id
);

ALTER TABLE consumer
ADD CONSTRAINT fk_cnsmr_2_cr FOREIGN KEY (role_id)
REFERENCES consumer_role(role_id);

CREATE INDEX idx_fk_cnsmr_2_cr ON consumer (role_id ASC);

CREATE UNIQUE INDEX idx_uq_cnsmr_email ON consumer (email ASC);

ALTER TABLE consumer
CHANGE COLUMN user_id
user_id INT(11) NOT NULL AUTO_INCREMENT ;


-- change super admin details as appropriate
-- psw is 12345678
INSERT INTO consumer (user_id, name, email, password, create_date, invalid_login_count, status, role_id) 
VALUES (1, 'super admin', 'super.admin@abc.com', '$2a$10$Njsq.obNd7z7XeqjB6L3NOttR8RnIcD813K1SE.ivUQedWbRCeEAy',
STR_TO_DATE('24-01-2018','%d-%m-%Y'), 0, 1, 1);