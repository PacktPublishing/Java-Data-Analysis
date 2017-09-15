--  SelectEuropeanBooksAuthors.sql
--  Data Analysis with Java
--  John R. Hubbard
--  May 4 2017

select author
from AuthorsBooks
where book in (
    select isbn
    from EuropeanBooks
)
