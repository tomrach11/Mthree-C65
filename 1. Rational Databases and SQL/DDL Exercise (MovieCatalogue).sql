DROP DATABASE moviecatalogue;
CREATE DATABASE moviecatalogue;
USE moviecatalogue;

CREATE TABLE Movie (
	MovieID int AUTO_INCREMENT PRIMARY KEY,
    Title varchar(128) NOT NULL,
    ReleaseDate DATE,
    GenreID int NOT NULL,
    DirectorID int,
    RatingID int
);

CREATE TABLE Genre (
	GenreID int AUTO_INCREMENT PRIMARY KEY,
    GenreName varchar(30)
);

CREATE TABLE Director (
	DirectorID int AUTO_INCREMENT PRIMARY KEY,
    FirstName varchar(30) NOT NULL,
    LastName varchar(30) NOT NULL,
    BirthDate date
);

CREATE TABLE Rating (
	RatingID int AUTO_INCREMENT PRIMARY KEY,
    RatingName varchar(5) NOT NULL
);

CREATE TABLE Actor (
	ActorID int AUTO_INCREMENT PRIMARY KEY,
    FirstName varchar(30) NOT NULL,
    LastName varchar(30) NOT NULL,
    BirthDate date
);

CREATE TABLE CastMembers (
	CastMemberID int AUTO_INCREMENT PRIMARY KEY,
    Role varchar(50) NOT NULL,
    ActorID int NOT NULL,
    MovieID int NOT NULL,
    FOREIGN KEY fk_CastMember_Actor(ActorID)
		REFERENCES Actor(ActorID),
	FOREIGN KEY fk_CastMember_Movie(MovieID)
		REFERENCES Movie(MovieID)
);

ALTER TABLE Movie
	ADD CONSTRAINT
		FOREIGN KEY fk_Movie_Genre(GenreID)
		REFERENCES Genre(GenreID),
	ADD CONSTRAINT
		FOREIGN KEY fk_Movie_Director(DirectorID)
		REFERENCES Director(DirectorID),
	ADD CONSTRAINT
		FOREIGN KEY fk_Movie_Rating(RatingID)
		REFERENCES Rating(RatingID);

