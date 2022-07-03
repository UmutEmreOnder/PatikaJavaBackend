DELETE FROM employee
WHERE name = 'Denis'
RETURNING *;