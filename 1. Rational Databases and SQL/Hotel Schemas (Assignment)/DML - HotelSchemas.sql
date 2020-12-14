insert into Amenity (Name) VALUES
	('Microwave'),
    ('Jacuzzi'),
    ('Refrigerator'),
    ('Oven');
    
insert into Room (RoomID, Type, AdaAccessible, StandardOccupancy, MaxOccupancy, BasePrice, ExtraPerson) VALUES
	(201, 'Double', 0, 2, 4, 199.99, 10),
    (202, 'Double', 1, 2, 4, 174.99, 10),
    (203, 'Double', 0, 2, 4, 199.99, 10),
    (204, 'Double', 1, 2, 4, 174.99, 10),
    (205, 'Single', 0, 2, 2, 174.99, null),
    (206, 'Single', 1, 2, 2, 149.99, null),
    (207, 'Single', 0, 2, 2, 174.99, null),
    (208, 'Single', 1, 2, 2, 149.99, null),
    (301, 'Double', 0, 2, 4, 199.99, 10),
    (302, 'Double', 1, 2, 4, 174.99, 10),
    (303, 'Double', 0, 2, 4, 199.99, 10),
    (304, 'Double', 1, 2, 4, 174.99, 10),
    (305, 'Single', 0, 2, 2, 174.99, null),
    (306, 'Single', 1, 2, 2, 149.99, null),
    (307, 'Single', 0, 2, 2, 174.99, null),
    (308, 'Single', 1, 2, 2, 149.99, null),
    (401, 'Suite', 1, 3, 8, 399.99, 20),
    (402, 'Suite', 1, 3, 8, 399.99, 20);
    
insert into RoomAmenity (RoomID, AmenityID) VALUES
	(201, 1), (201, 2),
    (202, 3),
    (203, 1), (203, 2),
    (204, 3),
    (205, 1), (205, 2), (205, 3),
    (206, 1), (206, 3),
    (207, 1), (207, 2), (207, 3),
    (208, 1), (208, 3),
    (301, 1), (301, 2),
    (302, 3),
    (303, 1), (303, 2),
    (304, 3),
    (305, 1), (305, 2), (305, 3),
    (306, 1), (306, 3),
    (307, 1), (307, 2), (307, 3),
    (308, 1), (308, 3),
    (401, 1), (401, 3), (401, 4),
    (402, 1), (402, 3), (402, 4);

insert into Guest (FirstName, Lastname, Address, City, State, Zipcode, Phone) VALUES
	('Tom', 'Rachtawarn', '1234 Street', 'Jersey City', 'NJ', '12345', '1234567890'),
    ('Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', '51501', '2915530508'),
    ('Bettyann', 'Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK', '99654', '4782779632'),
    ('Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', '78552', '3084940198'),
    ('Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', '2147300298'),
    ('Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', '48601', '3775070974'),
    ('Zachery', 'Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', '80003', '8144852615'),
    ('Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', '60099', '2794910906'),
    ('Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', '02864', '4463966785'),
    ('Wilfred', 'Vise', '77 West Surrey Street', 'Oswego', 'NY', '13126', '8347271001'),
    ('Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', '22015', '4663516860'),
    ('Joleen', 'Tison', '87 Queen St.', 'Drexel Hill', 'PA', '19026', '2318932755');
    
insert into Reservation (RoomID, GuestID, Adults, Children, StartDate, ENdDate) VALUES 
	(308, 2, 1, 0, '2023/2/2', '2023/2/4'),
    (203, 3, 2, 1, '2023/2/5', '2023/2/10'),
    (305, 4, 2, 0, '2023/2/22', '2023/2/24'),
    (201, 5, 2, 2, '2023/3/6', '2023/3/7'),
    (307, 1, 1, 1, '2023/3/17', '2023/3/20'),
    (302, 6, 3, 0, '2023/3/18', '2023/3/23'),
    (202, 7, 2, 2, '2023/3/29', '2023/3/31'),
    (304, 8, 2, 0, '2023/3/31', '2023/4/5'),
    (301, 9, 1, 0, '2023/4/9', '2023/4/13'),
    (207, 10, 1, 1, '2023/4/23', '2023/4/24'),
    (401, 11, 2, 4, '2023/5/30', '2023/6/2'),
    (206, 12, 2, 0, '2023/6/10', '2023/6/14'),
    (208, 12, 1, 0, '2023/6/10', '2023/6/14'),
    (304, 6, 3, 0, '2023/6/17', '2023/6/18'),
    (205, 1, 2, 0, '2023/6/28', '2023/7/2'),
    (204, 9, 3, 1, '2023/7/13', '2023/7/14'),
    (401, 10, 4, 2, '2023/7/18', '2023/7/21'),
    (303, 3, 2, 1, '2023/7/28', '2023/7/29'),
    (305, 3, 1, 0, '2023/8/30', '2023/9/1'),
    (208, 2, 2, 0, '2023/9/16', '2023/9/17'),
    (203, 5, 2, 2, '2023/9/13', '2023/9/15'),
	(401, 4, 2, 2, '2023/11/22', '2023/11/25'),
    (206, 2, 2, 0, '2023/11/22', '2023/11/25'),
    (301, 2, 2, 2, '2023/11/22', '2023/11/25'),
    (302, 11, 2, 0, '2023/12/24', '2023/12/28');

-- Delete
select * from guest where firstname = 'Jeremiah' and lastname = 'Pendergrass';
delete from reservation where guestid = 8;
delete from guest where guestid = 8;
-- Check
select * from guest where guestid = 8;
select * from reservation where guestid = 8;

    
    
	
    
    