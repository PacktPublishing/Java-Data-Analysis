--  CreateTables.sql
--  Creates the four tables for the Library database.
--  Data Analysis with Java
--  John R. Hubbard
--  May 4 2017

drop table AuthorsBooks;
drop table Authors;
drop table Books;
drop table Publishers;

create table Publishers (
    id         char(4)      primary key,
    name       varchar(32),
    city       varchar(32),
    country    char(2),
    url        varchar(32)
);

create table Books (
    title      varchar(64),
    edition    int          default 1,
    cover      char(4)      check(cover in ('HARD','SOFT')),
    publisher  char(4)      references Publishers,
    pubYear    int,
    isbn       char(13)     primary key,
    numPages   int
);

create table Authors (
    id         char(8)      primary key,
    lastName   varchar(16)  not null,
    firstName  varchar(16),
    yob        int          default 0
);

create table AuthorsBooks (
    author     char(8)      references Authors,
    book       char(13)     references Books,
    primary key (author, book)
);
