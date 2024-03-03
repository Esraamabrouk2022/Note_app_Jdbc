-- Create the database
CREATE DATABASE note_app;

-- Create the user and grant all privileges
CREATE USER 'note_app_11'@'localhost' IDENTIFIED BY '123456789';
GRANT ALL PRIVILEGES ON *.* TO 'note_app_11'@'localhost' WITH GRANT OPTION;

-- Flush privileges to apply changes
FLUSH PRIVILEGES;

-- Use note_app DATABASE
USE note_app;

-- Table for user
CREATE TABLE `user` (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255) NOT NULL
);

-- Table for note
CREATE TABLE note (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    date TIMESTAMP NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);