(
    SELECT first_name FROM customer
)
UNION ALL
(
    SELECT first_name FROM actor
);