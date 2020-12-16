use PersonalTrainer;

-- Use an aggregate to count the number of Clients.
-- 500 rows
--------------------
select count(*) from client;


-- Use an aggregate to count Client.BirthDate.
-- The number is different than total Clients. Why?
-- 463 rows
--------------------
select count(c.birthdate) from client c;


-- Group Clients by City and count them.
-- Order by the number of Clients desc.
-- 20 rows
--------------------
select c.city, count(*) from client c
	group by c.city
	order by count(*) desc;
    

-- Calculate a total per invoice using only the InvoiceLineItem table.
-- Group by InvoiceId.
-- You'll need an expression for the line item total: Price * Quantity.
-- Aggregate per group using SUM().
-- 1000 rows
--------------------
select il.invoiceid, sum(price * quantity) as 'InvoiceTotal' from invoicelineitem il
	group by il.InvoiceId;
    

-- Calculate a total per invoice using only the InvoiceLineItem table.
-- (See above.)
-- Only include totals greater than $500.00.
-- Order from lowest total to highest.
-- 234 rows
--------------------
select il.invoiceid, sum(price * quantity) as 'InvoiceTotal' from invoicelineitem il
	group by il.InvoiceId
    having sum(price * quantity) > 500
	order by sum(price * quantity);
    

-- Calculate the average line item total
-- grouped by InvoiceLineItem.Description.
-- 3 rows
--------------------
select il.description, avg(price * quantity) as 'AverageItemTotal' from invoicelineitem il
	group by il.Description;
    

-- Select ClientId, FirstName, and LastName from Client
-- for clients who have *paid* over $1000 total.
-- Paid is Invoice.InvoiceStatus = 2.
-- Order by LastName, then FirstName.
-- 146 rows
--------------------
select c.ClientId, c.FirstName, c.LastName, sum(price * quantity) as 'TotalAmountPaid' from client c
	join invoice i on c.ClientId = i.ClientId
    join invoicelineitem il on i.InvoiceId = il.InvoiceId
    where i.InvoiceStatus = 2
    group by c.ClientId
    having sum(price * quantity) > 1000
    order by c.FirstName and c.LastName;


-- Count exercises by category.
-- Group by ExerciseCategory.Name.
-- Order by exercise count descending.
-- 13 rows
--------------------
select ec.name, count(e.ExerciseId) from exercise e
	left join exercisecategory ec on e.ExerciseCategoryId = ec.ExerciseCategoryId
    group by e.ExerciseCategoryId
    order by count(e.ExerciseId);


-- Select Exercise.Name along with the minimum, maximum,
-- and average ExerciseInstance.Sets.
-- Order by Exercise.Name.
-- 64 rows
--------------------
select e.Name, min(ei.Sets) as 'MinSets', max(ei.Sets) as 'MaxSets' from exercise e
	join exerciseinstance ei on e.ExerciseId = ei.ExerciseId
    group by ei.ExerciseId
    order by e.Name;

-- Find the minimum and maximum Client.BirthDate
-- per Workout.
-- 26 rows
-- Sample: 
-- WorkoutName, EarliestBirthDate, LatestBirthDate
-- '3, 2, 1... Yoga!', '1928-04-28', '1993-02-07'
--------------------
select w.Name, min(c.BirthDate) as 'MinBirthDate', max(c.BirthDate) as 'MaxBirthDate' from workout w
	join clientworkout cw on w.WorkoutId = cw.WorkoutId
    join client c on cw.ClientId = c.ClientId
    group by w.WorkoutId;
    
    
-- Count client goals.
-- Be careful not to exclude rows for clients without goals.
-- 500 rows total
-- 50 rows with no goals
--------------------
select c.FirstName, c.LastName, count(cg.ClientId) as 'GoalsCount' from client c
	left join clientgoal cg on c.ClientId = cg.ClientId
    group by c.ClientId;


-- Select Exercise.Name, Unit.Name, 
-- and minimum and maximum ExerciseInstanceUnitValue.Value
-- for all exercises with a configured ExerciseInstanceUnitValue.
-- Order by Exercise.Name, then Unit.Name.
-- 82 rows
--------------------




-- Modify the query above to include ExerciseCategory.Name.
-- Order by ExerciseCategory.Name, then Exercise.Name, then Unit.Name.
-- 82 rows
--------------------

-- Select the minimum and maximum age in years for
-- each Level.
-- To calculate age in years, use the MySQL function DATEDIFF.
-- 4 rows
--------------------

-- Stretch Goal!
-- Count logins by email extension (.com, .net, .org, etc...).
-- Research SQL functions to isolate a very specific part of a string value.
-- 27 rows (27 unique email extensions)
--------------------

-- Stretch Goal!
-- Match client goals to workout goals.
-- Select Client FirstName and LastName and Workout.Name for
-- all workouts that match at least 2 of a client's goals.
-- Order by the client's last name, then first name.
-- 139 rows
--------------------