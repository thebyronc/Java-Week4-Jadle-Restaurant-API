SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS restaurants (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  address VARCHAR,
  zipcode VARCHAR,
  phone VARCHAR,
  website VARCHAR,
  email VARCHAR,
  image VARCHAR,
);

CREATE TABLE IF NOT EXISTS foodtypes (
  id int PRIMARY KEY auto_increment,
  name VARCHAR
);

CREATE TABLE IF NOT EXISTS reviews (
  id int PRIMARY KEY auto_increment,
  writtenby VARCHAR,
  rating VARCHAR,
  createdat TIMESTAMP,
  content VARCHAR,
  restaurantid INTEGER
);

CREATE TABLE IF NOT EXISTS restaurants_foodtypes (
  id int PRIMARY KEY auto_increment,
  restaurantId INTEGER,
  foodtypeId INTEGER
);