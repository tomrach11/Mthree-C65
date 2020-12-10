USE moviecatalogue;

INSERT INTO Actor (FirstName, LastName, BirthDate) VALUES
	('Bill', 'Murray', '1950/9/21'),
    ('Dan', 'Aykroyd', '1952/7/1'),
    ('John', 'Candy', '1950/10/31'),
    ('Steve', 'Martin', NULL),
    ('Sylvester', 'Stallone', NULL);
	
INSERT INTO Director (FirstName, LastName, BirthDate) VALUES
	('Ivan', 'Reitman', '1946/10/27'),
    ('Ted', 'Kotcheff', NULL);
    
INSERT INTO Rating (RatingName) VALUES
	('G'),
    ('PG'),
    ('PG-13'),
    ('R');
    
INSERT INTO Genre (GenreName) VALUES
	('Action'),
    ('Comedy'),
    ('Drama'),
    ('Horror');
    
INSERT INTO Movie (GenreID, DirectorID, RatingID, Title, ReleaseDate) VALUES
	(1, 2, 4, 'Rambo: First Blood', '1982/10/22'),
    (2, NULL, 4, 'Planes, Train & Automobiles', '1987/11/25'),
    (2, 1, 2, 'Ghostbuster', NULL),
    (2, NULL, 2, 'The Great Outdoor', '1988/6/17');
    
INSERT INTO CastMembers (ActorID, MovieID, Role) VALUES
	(5, 1, 'John Rambo'),
    (4, 2, 'Neil Page'),
    (3, 2, 'Del Griffith'),
    (1, 3, 'Dr. Peter Venkman'),
    (2, 3, 'Dr. Raymond Stanz'),
    (2, 4, 'Roman Craig'),
    (3, 4, 'Chet Ripley');
    
UPDATE Movie SET
	Title = 'GhostBuster (1984)',
    ReleaseDate = '1984/6/8'
    WHERE Title = 'Ghostbuster';
    
UPDATE Genre SET 
	GenreName = "Action/Adventure"
    WHERE GenreName = "Action";

DELETE FROM CastMembers 
	WHERE MovieID = 1;
    
DELETE FROM Movie 
	WHERE Title LIKE ('Rambo%');