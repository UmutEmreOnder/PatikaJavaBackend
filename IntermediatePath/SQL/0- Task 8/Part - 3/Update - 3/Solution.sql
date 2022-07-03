UPDATE employee
SET email = 'umutemreonder@gmail.com'
WHERE name = 'Umut'
RETURNING *;