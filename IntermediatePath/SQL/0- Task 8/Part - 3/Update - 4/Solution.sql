UPDATE employee
SET birthday = '2001/04/25'
WHERE birthday = '1961/11/13'
RETURNING *;