(
    SELECT first_name FROM customer
)
UNION
(
    SELECT first_name FROM actor
);