USE db_spring_vanyok;

DELIMITER //
CREATE TRIGGER AfterInsertVlasnykAvtosalon
AFTER INSERT
ON vlasnyk_avtosalon FOR EACH ROW
BEGIN
	DECLARE name_vlasnyk VARCHAR(50);
    DECLARE name_avtosalon VARCHAR(90);
    SELECT CONCAT(surname, ' ', name) INTO name_vlasnyk
    FROM vlasnyk WHERE vlasnyk.vlasnyk_id=new.vlasnyk_id;
    SELECT CONCAT(avtosalon_name, ' / ', Author) INTO name_avtosalon
    FROM avtosalon WHERE avtosalon.avtosalon_id=new.avtosalon_id;
	INSERT INTO logger (vlasnyk, avtosalon, action,
								time_stamp, user)
	VALUES(name_vlasnyk,  name_avtosalon, 'GOT', NOW(), USER() );
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER AfterDeleteVlasnykAvtosalon
AFTER DELETE
ON vlasnyk_avtosalon FOR EACH ROW
BEGIN
	DECLARE name_vlasnyk VARCHAR(50);
    DECLARE name_avtosalon VARCHAR(90);
    SELECT CONCAT(surname, ' ', name) INTO name_vlasnyk
    FROM vlasnyk WHERE vlasnyk.vlasnyk_id=old.vlasnyk_id;
    SELECT CONCAT(avtosalon_name, ' / ', author) INTO name_avtosalon
    FROM avtosalon WHERE avtosalon.avtosalon_id=old.avtosalon_id;
	INSERT INTO Logger (vlasnyk, avtosalon, action,
                      time_stamp, user)
	VALUES(name_vlasnyk,  name_avtosalon, 'GAVEBACK', NOW(), USER() );
END //
DELIMITER ;






