(
    SELECT first_name FROM customer
)

INTERSECT

(
    SELECT first_name FROM actor
);