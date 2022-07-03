(
    SELECT first_name FROM customer
)

INTERSECT ALL

(
    SELECT first_name FROM actor
);