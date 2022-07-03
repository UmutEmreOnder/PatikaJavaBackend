DELETE FROM employee
WHERE email LIKE '%com'
RETURNING *;