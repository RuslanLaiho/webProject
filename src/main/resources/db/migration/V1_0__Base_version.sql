CREATE TABLE person(
	person_id  SERIAL,
	PRIMARY KEY(person_id),
	name VARCHAR,
	occupation VARCHAR
);

CREATE TABLE Class (
	class_id  SERIAL,
	PRIMARY KEY(class_id),
	class_name VARCHAR,
	class_year INT,
	teacher_id INT,
	FOREIGN KEY(teacher_id) REFERENCES Person(person_id) ON DELETE SET NULL
);

CREATE TABLE Subjects (
	subject VARCHAR,
	PRIMARY KEY(subject)
);

CREATE TABLE class_student (
	student_id INT,
	FOREIGN KEY(student_id) REFERENCES Person(person_id),
	class_id INT,
	FOREIGN KEY(class_id) REFERENCES Class(class_id)
);

CREATE TABLE Marks (
	subject VARCHAR,
	FOREIGN KEY(subject) REFERENCES Subjects(subject),
	mark int,
	student_id int,
	FOREIGN KEY(student_id) REFERENCES Person(person_id),
	teacher_id int,
	FOREIGN KEY(teacher_id) REFERENCES Person(person_id)
);
