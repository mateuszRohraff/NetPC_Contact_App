CREATE TABLE IF NOT EXISTS USERS (
    userid INT PRIMARY KEY auto_increment,
    username VARCHAR(20),
    salt VARCHAR,
    password VARCHAR,
    firstname VARCHAR(20),
    lastname VARCHAR(20)
    );

CREATE TABLE IF NOT EXISTS CONTACTS (
    contactId INT PRIMARY KEY auto_increment,
    firstname VARCHAR (30),
    lastname VARCHAR (30),
    email VARCHAR (50),
    phoneNumber INT,
    key VARCHAR,
    password VARCHAR,
    dateOfBirth DATE
    );

CREATE TABLE IF NOT EXISTS CATEGORY (
    categoryId INT PRIMARY KEY auto_increment,
    name VARCHAR (30)
);

