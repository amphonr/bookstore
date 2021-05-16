
 INSERT INTO users (username, password,name, surname, date_of_birth) VALUES
  ( 'john.doe', 'thisismysecret','john', 'doe', '1990-01-19' );

 INSERT INTO orders(id, book_id, username) values
                    (1, 1, 'john.doe');
 INSERT INTO orders(id, book_id, username) values
                    (2, 4, 'john.doe');