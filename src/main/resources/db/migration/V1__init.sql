CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  login VARCHAR(45) NULL,
  uuid VARCHAR(45) NULL,
  password VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  first_name VARCHAR(45) NULL,
  last_name VARCHAR(45) NULL,
  active VARCHAR(45) NULL,
  gender VARCHAR(45) NULL,
  date_of_birthday VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE role (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE permission (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE role_user_mapping (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  INDEX fk_role_id_idx (role_id ASC),
  CONSTRAINT fk_user_id
  FOREIGN KEY (user_id)
  REFERENCES user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_role_id
  FOREIGN KEY (role_id)
  REFERENCES role (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE permission_user_mapping (
  permission_id INT NOT NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (permission_id, user_id),
  INDEX fk_user_idx (user_id ASC),
  CONSTRAINT fk_permission
  FOREIGN KEY (permission_id)
  REFERENCES permission (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_user
  FOREIGN KEY (user_id)
  REFERENCES user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE address (
  id INT NOT NULL AUTO_INCREMENT,
  address_line VARCHAR(45) NULL,
  country VARCHAR(45) NULL,
  PRIMARY KEY (id));

ALTER TABLE user
  ADD COLUMN address_id INT NULL AFTER date_of_birthday,
  ADD INDEX fk_address_idx (address_id ASC);
ALTER TABLE user
  ADD CONSTRAINT fk_address
FOREIGN KEY (address_id)
REFERENCES address (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;


