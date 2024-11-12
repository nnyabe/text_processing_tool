-- Creating the required databases.
CREATE TABLE IF NOT EXISTS books(
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    availability_status BOOLEAN DEFAULT TRUE
);