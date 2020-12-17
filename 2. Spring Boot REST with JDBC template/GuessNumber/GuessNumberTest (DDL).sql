DROP DATABASE IF EXISTS guessnumberDBtest;
CREATE DATABASE guessnumberDBtest;

USE guessnumberDBtest;

CREATE TABLE Game (
	GameId int AUTO_INCREMENT PRIMARY KEY,
    Number char(4) NOT NULL,
    Finished bit DEFAULT 0 NOT NULL
);

CREATE TABLE Round (
	RoundId int AUTO_INCREMENT PRIMARY Key,
    Guess char(4) NOT NULL,
    GameId int NOT NULL,
    Time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);