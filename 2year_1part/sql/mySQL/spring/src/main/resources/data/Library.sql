
CREATE SCHEMA IF NOT EXISTS  db_spring_vanyok CHARACTER SET utf8 ;
USE db_spring_vanyok ;

CREATE TABLE IF NOT EXISTS Avtosalon (
  avtosalon_id BIGINT NOT NULL AUTO_INCREMENT,
  avtosalon_name VARCHAR(45) NOT NULL,
  author VARCHAR(45) NOT NULL,
  seller VARCHAR(50) NULL,
  year_of_creating INT NULL,
  amount INT NOT NULL,
  PRIMARY KEY (avtosalon_id)
  ) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS city (
  city_id BIGINT NOT NULL AUTO_INCREMENT,
  city VARCHAR(25) NOT NULL,
  PRIMARY KEY (city_id)
  ) ENGINE = InnoDB
AUTO_INCREMENT = 1 
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS Vlasnyk (
  vlasnyk_id BIGINT NOT NULL AUTO_INCREMENT,
  surname VARCHAR(25) NOT NULL,
  name VARCHAR(25) NOT NULL,
  email VARCHAR(45) NULL,
  city_id BIGINT NULL,
  street VARCHAR(30) NULL,
  antosalonname VARCHAR(10) NULL,
  PRIMARY KEY (vlasnyk_id),
  CONSTRAINT fk_vlasnyk_city1
    FOREIGN KEY (city_id)
    REFERENCES db_spring_vanyok.city (city_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS vlasnyk_avtosalon (
  vlasnyk_id BIGINT NOT NULL,
  avtosalon_id BIGINT NOT NULL,
  PRIMARY KEY (vlasnyk_id, avtosalon_id),
  CONSTRAINT drivercar_ibfk_1
    FOREIGN KEY (vlasnyk_id)
    REFERENCES db_spring_vanyok.vlasnyk (vlasnyk_id),
  CONSTRAINT vlasnykavtosalon_ibfk_2
    FOREIGN KEY (avtosalon_id)
    REFERENCES db_spring_vanyok.avtosalon (avtosalon_id)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS logger (
  logger_id BIGINT NOT NULL AUTO_INCREMENT,
  vlasnyk VARCHAR(50) NOT NULL,
  avtosalon VARCHAR(90) NOT NULL,
  action VARCHAR(10) NOT NULL,
  time_stamp DATETIME NOT NULL,
  user VARCHAR(50) NULL,
  PRIMARY KEY (logger_id)
) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;









