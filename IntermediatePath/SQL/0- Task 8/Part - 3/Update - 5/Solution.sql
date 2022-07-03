UPDATE employee
SET
    name = 'John Doe',
    birthday = '1999/10/24',
    email = 'john@doe.com'
WHERE id = 5
RETURNING *;