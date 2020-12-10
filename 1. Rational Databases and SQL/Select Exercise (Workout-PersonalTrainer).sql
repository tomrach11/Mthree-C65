-- Act1
select * from exercise;

-- Act2
select * from client;

-- Act3
select * from client
	where city = 'Metairie';
    
-- Act4
select * from client
	where clientid = '818u7faf-7b4b-48a2-bf12-7a26c92de20c';
    
-- Act5
select * from goal;

-- Act6
select name, levelid from workout;

-- Act7
select name, levelid, notes from workout
	where levelid = 2;
    
-- Act8
select firstname, lastname, city from client
	where city in ('Metairie', 'Kenner', 'Gretna');

-- Act9
select firstname, lastname, birthdate from client
	where birthdate between '1980/1/1' and '1989/12/31';
    
-- Act10
select firstname, lastname, birthdate from client
	where birthdate > '1980/1/1' and birthdate < '1989/12/31';
    
-- Act11
select * from login
	where emailaddress like '%.gov';
    
-- Act12
select * from login
	where emailaddress not like '%.com';
    
-- Act13
select firstname, lastname from client
	where birthdate is null;

-- Act14
select name from exercisecategory
	where parentcategoryid is not null;

-- Act15
select name, notes from workout
	where levelid = 3 and notes like '%you%';
    
-- Act16
select firstname, lastname, city from client
	where (lastname like 'L%' or lastname like 'M%' or lastname like 'N%')
		and city = 'LaPlace';
        
-- Act17
select invoiceid, description, price, quantity, price * quantity as line_item_total from invoicelineitem
	where price * quantity between 15 and 25;
    
-- Act18
select clientid, firstname, lastname from client
	where firstname = 'Estrella' and lastname = 'Bazely';
select * from login
	where clientid = '87976c42-9226-4bc6-8b32-23a8cd7869a5';
-- Answer is yes

-- Act19
select * from workout
	where name = 'This Is Parkour';
select * from workoutgoal
	where workoutid = 12;
select * from goal
	where goalid in (3, 8, 15);
    
    
    
    
    
    