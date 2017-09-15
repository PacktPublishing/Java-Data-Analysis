--  CreateAuthors.sql
--  Creates the Authors table.
--  Data Analysis with Java
--  John R. Hubbard
--  May 4 2017

create table Authors (
  id         char(8) primary key,
  lastName   varchar(16),
  firstName  varchar(16),
  yob        int
)
