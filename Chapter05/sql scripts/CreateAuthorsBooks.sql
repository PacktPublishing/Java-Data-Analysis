--  CreateAuthorsBooks.sql
--  Creates the AuthorsBooks table.
--  Data Analysis with Java
--  John R. Hubbard
--  May 4 2017

create table AuthorsBooks (
  author  char(8) references Authors,
  book    char(13) references Books,
  primary key(author, book)
)
