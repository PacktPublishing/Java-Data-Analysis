--  CreateBooks.sql
--  Creates the Books table.
--  Data Analysis with Java
--  John R. Hubbard
--  May 4 2017

create table Books (
    title      varchar(64),
    edition    int          default 1,
    cover      char(4)      check(cover in ('HARD','SOFT')),
    publisher  char(4)      references Publishers,
    pubYear    int,
    isbn       char(13)     primary key,
    numPages   int
);
