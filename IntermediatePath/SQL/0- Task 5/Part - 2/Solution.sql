SELECT title, length FROM film
WHERE title LIKE ('%n')
ORDER BY length
OFFSET 5
LIMIT 5;