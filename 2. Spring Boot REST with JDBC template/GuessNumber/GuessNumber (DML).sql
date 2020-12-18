USE guessnumberDB;

INSERT INTO Game (Number) VALUES
	("1234"),
    ("2679"),
    ("7894");
    
INSERT INTO Round (GameId, Guess, Result) VALUE (1, "1278", "e0:p0");
INSERT INTO Round (GameId, Guess, Result) VALUE (1, "5712", "e0:p0");
INSERT INTO Round (GameId, Guess, Result) VALUE (1, "3412", "e0:p0");
INSERT INTO Round (GameId, Guess, Result) VALUE (1, "1234", "e0:p0");
INSERT INTO Round (GameId, Guess, Result) VALUE (2, "5712", "e0:p0");
INSERT INTO Round (GameId, Guess, Result) VALUE (2, "2091", "e0:p0");
    
UPDATE Game SET
	Finished = 1
	WHERE GameId = 1;