USE db_spring_vanyok;

INSERT INTO avtosalon (avtosalon_name, author, seller, year_of_creating, amount) VALUES
  ('BMW','Vanya','Germany',1994,50000);

INSERT INTO city (city_id,city) VALUES
(1,'Herson'),(2,'Kyiv'),(3,'Lviv'),(4,'Poltava'),(5,'Ternopil');

INSERT INTO vlasnyk (surname, name, email, city_id, street, avtosalonname) VALUES
('Veres','Zenoviy','zenoviy.veres@gmail.com',3,'Hutorivka','bmw');


