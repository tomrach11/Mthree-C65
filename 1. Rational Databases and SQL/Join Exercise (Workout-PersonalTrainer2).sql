USE PersonalTrainer;

-- Select all columns from ExerciseCategory and Exercise.
-- The tables should be joined on ExerciseCategoryId.
-- This query returns all Exercises and their associated ExerciseCategory.
-- 64 rows
--------------------
select * from exercisecategory ec
	join exercise e on ec.ExerciseCategoryId = e.ExerciseCategoryId;

    
-- Select ExerciseCategory.Name and Exercise.Name
-- where the ExerciseCategory does not have a ParentCategoryId (it is null).
-- Again, join the tables on their shared key (ExerciseCategoryId).
-- 9 rows
--------------------
select ec.name, e.name from exercisecategory ec
	join exercise e on ec.ExerciseCategoryId = e.ExerciseCategoryId
    where ec.ParentCategoryId is Null;

-- The query above is a little confusing. At first glance, it's hard to tell
-- which Name belongs to ExerciseCategory and which belongs to Exercise.
-- Rewrite the query using an aliases. 
-- Alias ExerciseCategory.Name as 'CategoryName'.
-- Alias Exercise.Name as 'ExerciseName'.
-- 9 rows
--------------------
select ec.name as 'CategoryName', e.name as 'ExerciseName' from exercisecategory ec
	join exercise e on ec.ExerciseCategoryId = e.ExerciseCategoryId
    where ec.ParentCategoryId is Null;

-- Select FirstName, LastName, and BirthDate from Client
-- and EmailAddress from Login 
-- where Client.BirthDate is in the 1990s.
-- Join the tables by their key relationship. 
-- What is the primary-foreign key relationship?
-- 35 rows
--------------------
select c.firstname, c.lastname, c.birthdate, l.emailaddress from client c 
	join login l on c.ClientId = l.ClientId
    where c.BirthDate between '1990/1/1' and '1999/12/31';
	

-- Select Workout.Name, Client.FirstName, and Client.LastName
-- for Clients with LastNames starting with 'C'?
-- How are Clients and Workouts related?
-- 25 rows
--------------------
select w.name, c.firstname, c.lastname from client c
	join clientworkout cw on cw.ClientId = c.ClientId
	join workout w on cw.WorkoutId = w.WorkOutId
    where c.LastName like "C%";

-- Select Names from Workouts and their Goals.
-- This is a many-to-many relationship with a bridge table.
-- Use aliases appropriately to avoid ambiguous columns in the result.
--------------------
select w.name as 'WorkoutName', g.name as 'GoalName' from workout w
	join workoutgoal wg on wg.WorkoutId = w.WorkoutId
    join goal g on wg.GoalId = g.GoalId;
    SELECT
	w.`Name` WorkoutName,
	g.`Name` GoalName
FROM Workout w
INNER JOIN WorkoutGoal wg ON w.WorkoutId = wg.WorkoutId
INNER JOIN Goal g ON wg.GoalId = g.GoalId;

-- Select FirstName and LastName from Client.
-- Select ClientId and EmailAddress from Login.
-- Join the tables, but make Login optional.
-- 500 rows
--------------------
select c.firstname, c.lastname, l.clientid, l.emailaddress from client c 
	left join login l on l.ClientId = c.ClientId;

-- Using the query above as a foundation, select Clients
-- who do _not_ have a Login.
-- 200 rows
--------------------
select c.firstname, c.lastname from client c 
	left join login l on l.ClientId = c.ClientId
    where l.ClientId is null;

-- Does the Client, Romeo Seaward, have a Login?
-- Decide using a single query.
-- nope :(
--------------------
select * from client c
	join login l on c.ClientId = l.ClientId
    where c.FirstName = 'Romeo' and c.LastName = 'Seaward';

-- Select ExerciseCategory.Name and its parent ExerciseCategory's Name.
-- This requires a self-join.
-- 12 rows
--------------------
select child.name as 'ExerciseName', parent.name as 'ParentExerciseName' from exercisecategory child
	join exercisecategory parent on child.ParentCategoryId = parent.ExerciseCategoryId;
    
-- Rewrite the query above so that every ExerciseCategory.Name is
-- included, even if it doesn't have a parent.
-- 16 rows
--------------------
select child.name as 'ExerciseName', parent.name as 'ParentExerciseName' from exercisecategory child
	left join exercisecategory parent on parent.ExerciseCategoryId = child.ParentCategoryId;
    
-- Are there Clients who are not signed up for a Workout?
-- 50 rows
--------------------
select c.firstname, c.lastname from client c
	left join clientworkout cw on c.ClientId = cw.ClientId
    where cw.ClientId is null;

-- Which Beginner-Level Workouts satisfy at least one of Shell Creane's Goals?
-- Goals are associated to Clients through ClientGoal.
-- Goals are associated to Workouts through WorkoutGoal.
-- 6 rows, 4 unique rows
--------------------
select l.name, w.name from client c 
	join clientgoal cg on c.ClientId = cg.ClientId
    join workoutgoal wg on cg.GoalId = wg.GoalId
    join workout w on wg.WorkoutId = w.WorkoutId
    join level l on w.LevelId = l.LevelId
    where c.FirstName = 'Shell' and c.LastName = 'Creane' and l.name = 'Beginner';

-- Select all Workouts. 
-- Join to the Goal, 'Core Strength', but make it optional.
-- You may have to look up the GoalId before writing the main query.
-- If you filter on Goal.Name in a WHERE clause, Workouts will be excluded.
-- Why?
-- 26 Workouts, 3 Goals
--------------------
select w.WorkoutId, w.Name, g.name from workout w
	left join workoutgoal wg on w.WorkoutId = wg.WorkoutId and wg.GoalId = 10
    left join goal g on wg.GoalId = g.GoalId;

-- The relationship between Workouts and Exercises is... complicated.
-- Workout links to WorkoutDay (one day in a Workout routine)
-- which links to WorkoutDayExerciseInstance 
-- (Exercises can be repeated in a day so a bridge table is required) 
-- which links to ExerciseInstance 
-- (Exercises can be done with different weights, repetions,
-- laps, etc...) 
-- which finally links to Exercise.
-- Select Workout.Name and Exercise.Name for related Workouts and Exercises.
--------------------
select w.name as 'WorkoutName', e.name as 'ExerciseName' from exercise e
	join exerciseinstance ei on e.ExerciseId = ei.ExerciseId
    join workoutdayexerciseinstance wdei on ei.ExerciseInstanceId = wdei.ExerciseInstanceId
    join workoutday wd on wdei.WorkoutDayId = wd.WorkoutDayId
    join workout w on wd.WorkoutId = w.WorkoutId;

-- from answer    
SELECT
	w.`Name` WorkoutName,
    e.`Name` ExerciseName
FROM Workout w
INNER JOIN WorkoutDay wd 
	ON w.WorkoutId = wd.WorkoutId
INNER JOIN WorkoutDayExerciseInstance wdei 
	ON wd.WorkoutDayId = wdei.WorkoutDayId
INNER JOIN ExerciseInstance ei 
	ON wdei.ExerciseInstanceId = ei.ExerciseInstanceId
INNER JOIN Exercise e 
	ON ei.ExerciseId = e.ExerciseId;
   
-- An ExerciseInstance is configured with ExerciseInstanceUnitValue.
-- It contains a Value and UnitId that links to Unit.
-- Example Unit/Value combos include 10 laps, 15 minutes, 200 pounds.
-- Select Exercise.Name, ExerciseInstanceUnitValue.Value, and Unit.Name
-- for the 'Plank' exercise. 
-- How many Planks are configured, which Units apply, and what 
-- are the configured Values?
-- 4 rows, 1 Unit, and 4 distinct Values
--------------------
select e.name, eiuv.value, u.name from exercise e 
	join exerciseinstance ei on e.ExerciseId = ei.ExerciseId
    join exerciseinstanceunitvalue eiuv on ei.ExerciseInstanceId = eiuv.ExerciseInstanceId
    join unit u on eiuv.UnitId = u.UnitId
    where e.name = 'Plank';
    
-- from answer
SELECT
	e.`Name` ExerciseName,
    uv.`Value`,
    u.`Name` UnitName
FROM Exercise e
INNER JOIN ExerciseInstance ei ON e.ExerciseId = ei.ExerciseId
LEFT OUTER JOIN ExerciseInstanceUnitValue uv
	ON ei.ExerciseInstanceId = uv.ExerciseInstanceId
LEFT OUTER JOIN Unit u On uv.UnitId = u.UnitId
WHERE e.`Name` = 'Plank';