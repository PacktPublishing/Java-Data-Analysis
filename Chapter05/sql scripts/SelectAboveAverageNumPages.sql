--  SelectAboveAverageNumPages.sql
--  Data Analysis with Java
--  John R. Hubbard
--  May 4 2017

select title, numPages
from Books
where numPages > (
    select avg(numPages)
	from Books
)
order by numPages desc
