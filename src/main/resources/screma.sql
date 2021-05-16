

CREATE TABLE users (
  username VARCHAR(15) PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  surname VARCHAR(50) NOT NULL,
  date_of_birth DATE NOT NULL,
  password VARCHAR(20) NOT NULL
);

CREATE TABLE orders (
  id INT  PRIMARY KEY,
  username VARCHAR(15) NOT NULL,
  book_id INT NOT NULL
);