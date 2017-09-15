--  CreateView.sql
--  Data Analysis with Java
--  John R. Hubbard
--  May 4 2017

create view AmericanAuthors as
    select distinct lastName, firstName, country
    from AuthorsBooks, Authors, Books, Publishers
    where author = Authors.ID
    and book = isbn
    and publisher = Publishers.id
    and country = 'US'
    order by lastName;
