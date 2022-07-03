SELECT title, rental_rate, replacement_cost
FROM film
WHERE rental_rate =
(
    SELECT MIN(rental_rate) FROM film
)
UNION

SELECT title, rental_rate, replacement_cost
FROM film
WHERE replacement_cost =
(
    SELECT MIN(replacement_cost) FROM film
);