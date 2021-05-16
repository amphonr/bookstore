CREATE TABLE user_login (
  username VARCHAR(15) PRIMARY KEY,
  user_id INT NOT NULL,
  password VARCHAR(250) NOT NULL
);

CREATE TABLE users (
  id INT  PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  date_of_birth DATE NOT NULL
);

CREATE TABLE orders (
  id INT  PRIMARY KEY,
  user_id INT NOT NULL,
  book_id INT NOT NULL
);