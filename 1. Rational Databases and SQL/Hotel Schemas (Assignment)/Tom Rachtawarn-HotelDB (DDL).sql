drop database hotelschemas;
create database hotelschemas;
use hotelschemas;

CREATE TABLE Amenity (
	AmenityID int AUTO_INCREMENT PRIMARY KEY,
    Name varchar(15) NOT NULL
);

CREATE TABLE Room (
	RoomID int PRIMARY KEY,
    Type varchar(7) NOT NULL,
    AdaAccessible bit NOT NULL DEFAULT 1,
    StandardOccupancy tinyint NOT NULL DEFAULT 1,
    MaxOccupancy tinyint NOT NULL,
	BasePrice decimal(7,2) NOT NULL,
    ExtraPerson decimal(7,2)
);

CREATE TABLE RoomAmenity (
	RoomID int NOT NULL,
    AmenityID int NOT NULL,
    FOREIGN KEY fk_RoomAmenity_Room(RoomID)
		REFERENCES Room(RoomID),
	FOREIGN KEY fk_RoomAmenity_Amenity(AmenityID)
		REFERENCES Amenity(AmenityID)
);

CREATE TABLE Guest (
	GuestID int AUTO_INCREMENT PRIMARY KEY,
    FirstName varchar(30) NOT NULL,
    LastName varchar(30) NOT NULL,
    Address varchar(30) NOT NULL,
    City varchar(30) NOt NULL,
    State char(2) NOT NULL,
    Zipcode char(5) NOT NULL,
    Phone char(10) NOT NULL
);

CREATE TABLE Reservation (
	ReservationID int AUTO_INCREMENT PRIMARY KEY,
    RoomID int NOT NULL,
    GuestID int NOT NULL,
    Adults int NOT NULL,
    Children int NOT NULL,
    StartDate date NOT NULL,
    EndDate date NOT NULL
);







