CREATE SCHEMA IF NOT EXISTS db_hibernate_vanyok DEFAULT CHARACTER SET utf8;
USE db_hibernate_vanyok;

CREATE TABLE Avtosalon
(
  AvtosalonName VARCHAR(25) NOT NULL,
  PRIMARY KEY (AvtosalonName)
) ENGINE = InnoDB;

CREATE TABLE  Vlasnyk
(
  IDVlasnyk INT NOT NULL AUTO_INCREMENT,
  Surname VARCHAR(25) NOT NULL,
  Name VARCHAR(25) NOT NULL,
  City VARCHAR(25) NOT NULL,
  Email VARCHAR(45) NULL,
  PRIMARY KEY (IDVlasnyk),
  CONSTRAINT FOREIGN KEY (City)
  REFERENCES Avtosalon (AvtosalonName)
) ENGINE = InnoDB;

CREATE TABLE Detail
(
  IDDetail INT NOT NULL AUTO_INCREMENT,
  DetailName VARCHAR(45) NOT NULL,
  Author VARCHAR(45) NOT NULL,
  Amount INT NOT NULL,
  PRIMARY KEY (IDDetail)
) ENGINE = InnoDB;

CREATE TABLE  VlasnykDetail (
  IDVlasnyk INT NOT NULL,
  IDDetail INT NOT NULL,
  PRIMARY KEY (IDVlasnyk, IDDetail),
  CONSTRAINT  FOREIGN KEY (IDVlasnyk)
  REFERENCES  Vlasnyk (IDVlasnyk),
  CONSTRAINT   FOREIGN KEY (IDDetail)
  REFERENCES  Detail (IDDetail)
) ENGINE = InnoDB;

INSERT INTO Detail VALUES
  (1,'Bible','St. mans',5),
  (2,'Kobzar','Shevchenko ',4),
  (3,'Harry Potter','J. K. Rowling',1),
  (4,'Zakhar Berkut','I. Franko',2),
  (5,'The Jungle Detail','Rudyard Kipling',1);

INSERT INTO Avtosalon VALUES ('Herson'),('Kyiv'),('Lviv'),('Poltava'),('Ternopil');

INSERT INTO Vlasnyk VALUES
  (1,'Koldovskyy','Vyacheslav','Lviv','koldovsky@gmail.com'),
  (2,'Pavelchak','Andrii','Poltava','apavelchak@gmail.com'),
  (3,'Soluk','Andrian','Herson','andriansoluk@gmail.com'),
  (4,'Dubyniak','Bohdan','Ternopil','bohdan.dub@gmail.com'),
  (5,'Faryna','Igor','Kyiv','farynaihor@gmail.com'),
  (6,'Kurylo','Volodymyr','Poltava','kurylo.volodymyr@gmail.com'),
  (7,'Matskiv','Marian','Herson','marian3912788@gmail.com'),
  (8,'Shyika','Tamara','Kyiv','tamara.shyika@gmail.com'),
  (9,'Tkachyk','Volodymyr','Ternopil','vova1234.tkachik@gmail.com');

INSERT INTO VlasnykDetail VALUES (4,1),(5,1),(8,1),(2,2),(6,2),(7,2),(1,3),(1,4),(9,4),(3,5);

DELIMITER //
CREATE PROCEDURE InsertVlasnykDetail
  (
    IN SurnameVlasnykIn varchar(25),
    IN DetailNameIN varchar(45)
  )
  BEGIN
    DECLARE msg varchar(40);

    -- checks for present Surname
    IF NOT EXISTS( SELECT * FROM Vlasnyk WHERE Surname=SurnameVlasnykIn)
    THEN SET msg = 'This Surname is absent';

    -- checks for present Detail
    ELSEIF NOT EXISTS( SELECT * FROM Detail WHERE DetailName=DetailNameIN)
      THEN SET msg = 'This Detail is absent';

    -- checks if there are this combination already
    ELSEIF EXISTS( SELECT * FROM vlasnykdetail
    WHERE IDVlasnyk = (SELECT IDVlasnyk FROM Vlasnyk WHERE Surname=SurnameVlasnykIn)
          AND IDDetail = (SELECT IDDetail FROM Detail WHERE DetailName=DetailNameIN)
    )
      THEN SET msg = 'This Vlasnyk already has this book';

    -- checks whether there is still such a book
    ELSEIF (SELECT Amount FROM Detail WHERE DetailName=DetailNameIN )
           <= (SELECT COUNT(*) FROM vlasnykdetail WHERE IDDetail=(SELECT IDDetail FROM Detail WHERE DetailName=DetailNameIN) )
      THEN SET msg = 'There are no this Detail already';

    -- makes insert
    ELSE
      INSERT vlasnykdetail (IDVlasnyk, IDDetail)
        Value ( (SELECT IDVlasnyk FROM Vlasnyk WHERE Surname=SurnameVlasnykIn),
                (SELECT IDDetail FROM Detail WHERE DetailName=DetailNameIN) );
      SET msg = 'OK';

    END IF;

    SELECT msg AS msg;

  END //
DELIMITER ;