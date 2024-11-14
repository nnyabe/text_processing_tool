-- Creates the databse if not existing and creates all database tables needed;
DROP DATABASE IF EXISTS library;
CREATE DATABASE library;

USE library;
DROP TABLE IF EXISTS patrons;
CREATE TABLE patrons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS admins;
CREATE TABLE admins (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    role ENUM('LIBRARIAN', 'ADMIN', 'MANAGER') NOT NULL
);

DROP TABLE IF EXISTS books;
CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    available_state BOOLEAN NOT NULL,
    title VARCHAR(255) NOT NULL,
    publisher VARCHAR(255),
    total_copies INT DEFAULT 1,
    copies_left INT DEFAULT 1,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    edition INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS magazines;
CREATE TABLE magazines (
    id INT PRIMARY KEY AUTO_INCREMENT,
    available_state BOOLEAN NOT NULL,
    title VARCHAR(255),
    publisher VARCHAR(255),
    total_copies INT DEFAULT 1,
    copies_left INT DEFAULT 1,
    editor VARCHAR(255)NOT NULL,
    issn VARCHAR(20) UNIQUE NOT NULL,
    volume INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS transactions;
CREATE TABLE IF NOT EXISTS transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    approved_date TIMESTAMP,
    return_date TIMESTAMP,
    status ENUM('PENDING', 'APPROVED', 'RETURNED', 'REJECTED') NOT NULL,
    approved_by VARCHAR(50),
    ordered_by VARCHAR(50),
    resource_type VARCHAR(20),
    resource_id int
);

ALTER TABLE transactions ADD CONSTRAINT
    resource_id FOREIGN KEY (resource_id) REFERENCES books(id);
ALTER TABLE transactions ADD CONSTRAINT
    approved_by FOREIGN KEY (approved_by) REFERENCES admins(email);
ALTER TABLE transactions ADD CONSTRAINT
    ordered_by FOREIGN KEY (ordered_by) REFERENCES patrons(email);
