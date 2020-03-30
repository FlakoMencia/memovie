-- Meta data necesaria para iniciar Api
-- Author:  MARIO MENCIA
-- Created: 03-28-2020

INSERT INTO users(email, enabled, last_name, name, password, `role`, username) VALUES('', 1, 'PRINCIPAL', 'ADMINISTRADOR', '$2a$10$/MA.7HsumshqtPE4rhImaej0bwvBtZgdGOdRODjlYttYwUS7lL0wO', 'ROLE_ADMIN', 'admin');

--  INSERT INTO movies (id,stock,cover,rental_price,sales_price,sinopsis,tittle) VALUES (1, 1000,NULL,19.99,200, 
-- 'Nick Fury (Samuel L. Jackson), director of S.H.I.E.L.D. (Strategic Homeland Intervention, Enforcement, and Logistics Division), arrives at S.H.I.E.L.D. headquarters outside of Santa Fe, New Mexico, during 
--  an evacuation. The Tesseract, an energy source of unknown potential, has activated. It opens a portal through space and the exiled Norse god Loki (Tom Hiddleston) steps through, carrying a strange spear with 
--  a blue glowing tip. Loki takes the Tesseract and uses the spear to take control of the minds of several SHIELD personnel, including Dr. Erik Selvig (Stellan Skarsgård) , and Agent Clint "Hawkeye" Barton 
--  (Jeremy Renner), to aid him in his getaway. SHIELD personnel pull out of their base when an energy surge from the Tesseract causes the ground beneath the base to collapse and destroying it. A short pursuit of Loki fails to capture him.','The Avengers')
--  ;
--  
--  INSERT INTO movie_likes (likethismovie,movie_id,userlike_id) VALUES (1,1,1);
--  
--  INSERT INTO cast_of_movie (character_name,photo_actor,real_name,movie_id) VALUES ('Tony Stark / Iron Man',NULL,'Robert Downey Jr. ',1)
--  ,('Natasha Romanoff / Black Widow',NULL,'Scarlett Johansson ',1),('Thor',NULL,'Thor',1);