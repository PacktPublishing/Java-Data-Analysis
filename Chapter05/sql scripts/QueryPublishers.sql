--  QueryPublishers.sql
--  Queries the Publishers table.
--  Data Analysis with Java
--  John R. Hubbard
--  May 4 2017

select name, city, country from Publishers
where country <> 'US'
order by name;
