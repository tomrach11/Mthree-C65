use hotelschemas;

-- 1. Write a query that returns a list of reservations that end in July 2023, including the name of the guest, the room number(s), and the reservation dates.
SELECT CONCAT(g.FirstName, " ", g.LastName) AS 'Name', r.RoomID, r.StartDate, r.EndDate  FROM Reservation r
	JOIN Guest g ON r.GuestID = g.GuestID
    WHERE StartDate < '2023/07/01'
    ORDER BY StartDate DESC;
    
-- 2. Write a query that returns a list of all reservations for rooms with a jacuzzi, displaying the guest's name, the room number, and the dates of the reservation.
SELECT CONCAT(g.FirstName, " ", g.LastName) AS 'Name', r.RoomID, r.StartDate, r.EndDate FROM Reservation r
	JOIN Guest g ON r.GuestID = g.GuestID
	JOIN RoomAmenity ra ON r.RoomID = ra.RoomID
    JOIN Amenity a ON ra.AmenityID = a.AmenityID -- use this so join so that I don't have to look up for jacuzzi ID
    WHERE a.Name = 'jacuzzi';
    
-- 3. Write a query that returns all the rooms reserved for a specific guest, including the guest's name, the room(s) reserved, the starting date of the reservation, and how many people were included in the reservation. 
-- (Choose a guest's name from the existing data.)
SELECT CONCAT(g.FirstName, " ", g.LastName) AS 'Name', r.RoomID, r.StartDate, r.EndDate, (r.Adults + r.Children) as 'Total Guests', r.ReservationID FROM Reservation r
	JOIN Guest g ON r.GuestID = g.GuestID
    WHERE CONCAT(g.FirstName, " ", g.LastName) = 'Mack Simmer';
    
-- 4. Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. The results should include all rooms, whether or not there is a reservation associated with the room.
									-- (basePrice + (extraPerson * extraPersonPrice)) * numberOfDays
SELECT r.RoomID, rv.ReservationID, (r.BasePrice + (IF((rv.Adults - r.StandardOccupancy) > 0, (rv.Adults - r.StandardOccupancy), 0) * IFNULL(r.ExtraPerson, 0))) * DATEDIFF(rv.EndDate, rv.StartDate) as 'TotalRoomCost' 
	FROM Room r
	LEFT JOIN Reservation rv ON r.RoomID = rv.RoomID;
    
-- 5. Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date in April 2023.
SELECT rv.RoomID, rv.StartDate, rv.EndDate, rv.Adults, rv.Children from Reservation rv
	WHERE rv.Adults + rv.Children >= 3 AND 
		rv.StartDate BETWEEN '2023-4-1' AND '2023-4-30';
        
-- 6. Write a query that returns a list of all guest names and the number of reservations per guest, sorted starting with the guest with the most reservations and then by the guest's last name.
SELECT CONCAT(g.FirstName, " ", g.LastName)  AS 'Name', COUNT(rv.GuestID) AS 'TotalReservation' FROM Reservation rv
	LEFT JOIN Guest g ON rv.GuestID = g.GuestID
    GROUP BY rv.GuestID;


-- 7. Write a query that displays the name, address, and phone number of a guest based on their phone number. (Choose a phone number from the existing data.)
SELECT CONCAT(g.FirstName, " ", g.LastName)  AS 'Name', CONCAT(g.Address, ", ", g.City, ", ", g.State, ", ", g.Zipcode) AS Address, g.phone from Guest g
	WHERE g.phone = '1234567890';
