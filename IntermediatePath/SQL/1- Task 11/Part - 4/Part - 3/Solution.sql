(
    SELECT first_name FROM actor
)

EXCEPT ALL

(
    SELECT first_name FROM customer
);