--  CreatePublishers.sql
--  Creates the Publishers table.
--  Data Analysis with Java
--  John R. Hubbard
--  May 4 2017

create table Publishers (
    id         char(4)      primary key,
    name       varchar(32),
    city       varchar(32),
    country    char(2),
    url        varchar(32)
);
