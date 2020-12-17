USE guessnumberDB;

INSERT INTO Game (Number) VALUES
	("1234"),
    ("2679"),
    ("7894");
    
INSERT INTO Round (GameId, Guess) VALUE (1, "1278");
INSERT INTO Round (GameId, Guess) VALUE (1, "5712");
INSERT INTO Round (GameId, Guess) VALUE (1, "3412");
INSERT INTO Round (GameId, Guess) VALUE (1, "1234");
INSERT INTO Round (GameId, Guess) VALUE (2, "5712");
INSERT INTO Round (GameId, Guess) VALUE (2, "2091");
    
UPDATE Game SET
	Finished = 1
	WHERE GameId = 1;