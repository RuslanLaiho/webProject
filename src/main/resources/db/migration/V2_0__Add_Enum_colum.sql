CREATE type occupation as enum('Учитель','Ученик');

ALTER TABLE person
ALTER COLUMN occupation TYPE occupation USING occupation::occupation;