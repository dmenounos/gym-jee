-- 1. Create the database schema

CREATE SCHEMA IF NOT EXISTS gym_db DEFAULT CHARACTER SET utf8;
USE gym_db;

CREATE TABLE IF NOT EXISTS gym_user (
  id INT NOT NULL AUTO_INCREMENT,
  role VARCHAR(255),
  username VARCHAR(255),
  password VARCHAR(255),
  email VARCHAR(255),
  fname VARCHAR(255),
  lname VARCHAR(255),
  age VARCHAR(255),
  sex VARCHAR(255),
  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS gym_program (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  description VARCHAR(255),
  cost INT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS gym_class (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  day VARCHAR(255) NULL,
  start VARCHAR(255) NULL,
  end VARCHAR(255) NULL,
  program_id INT NOT NULL,
  PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE gym_class ADD INDEX       (program_id);
ALTER TABLE gym_class ADD FOREIGN KEY (program_id) REFERENCES gym_program (id) ON DELETE CASCADE;

CREATE TABLE IF NOT EXISTS gym_user_program (
  user_id INT NOT NULL,
  program_id INT NOT NULL,
  PRIMARY KEY (user_id, program_id)
) ENGINE = InnoDB;

ALTER TABLE gym_user_program ADD INDEX       (user_id);
ALTER TABLE gym_user_program ADD FOREIGN KEY (user_id) REFERENCES gym_user (id) ON DELETE NO ACTION;
ALTER TABLE gym_user_program ADD INDEX       (program_id);
ALTER TABLE gym_user_program ADD FOREIGN KEY (program_id) REFERENCES gym_program (id) ON DELETE NO ACTION;

-- 2. Populate the database with test data

INSERT INTO gym_user (role, username, password, email, fname, lname, age, sex) VALUES ('admin', 'admin', 'admin', 'admin@example.org', 'Dimitrios', 'Menounos', '40', 'MALE');
INSERT INTO gym_user (role, username, password, email, fname, lname, age, sex) VALUES ('user', 'user', 'user', 'user@example.org', 'Dimitrios', 'Menounos', '40', 'MALE');

INSERT INTO gym_program (name, description, cost) VALUES ('AEROBICS', 'Lorem Ipsum ...', 30);
INSERT INTO gym_program (name, description, cost) VALUES ('CROSSFIT', 'Lorem Ipsum ...', 50);
