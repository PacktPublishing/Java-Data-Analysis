--  CreateEuropeanBooksView.sql
--  Data Analysis with Java
--  John R. Hubbard
--  May 4 2017

create view EuropeanBooks as
    select isbn
    from Books, Publishers
    where publisher = id
    and country <> 'US';
