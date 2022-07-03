SELECT customer_id, COUNT(payment_id),
    (
        SELECT CONCAT(first_name, ' ', last_name) AS "Name and Surname" FROM customer
        WHERE payment.customer_id = customer.customer_id
    )
FROM payment
GROUP BY customer_id
ORDER BY COUNT(payment_id) DESC;