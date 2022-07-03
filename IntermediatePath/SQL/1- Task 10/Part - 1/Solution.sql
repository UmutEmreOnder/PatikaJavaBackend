SELECT country, city FROM city
LEFT JOIN country on country.country_id = city.country_id;