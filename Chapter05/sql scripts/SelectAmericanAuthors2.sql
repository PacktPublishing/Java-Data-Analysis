--  SelectAmericanAuthors2.sql
--  Data Analysis with Java
--  John R. Hubbard
--  May 4 2017

select firstName, lastName
from AmericanAuthors
where substr(firstName,1,1) = 'J';
