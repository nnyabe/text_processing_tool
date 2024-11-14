INSERT INTO patrons (username, email) VALUES
('john_doe', 'john.doe@example.com'),
('jane_smith', 'jane.smith@example.com'),
('mike_jones', 'mike.jones@example.com'),
('alice_williams', 'alice.williams@example.com'),
('bob_brown', 'bob.brown@example.com'),
('charlie_davis', 'charlie.davis@example.com'),
('david_miller', 'david.miller@example.com'),
('eve_white', 'eve.white@example.com'),
('frank_lee', 'frank.lee@example.com'),
('grace_kim', 'grace.kim@example.com');

INSERT INTO admins (username, password, email, role) VALUES
('librarian_1', 'password123', 'librarian1@example.com', 'LIBRARIAN'),
('admin_1', 'adminpass123', 'admin1@example.com', 'ADMIN'),
('manager_1', 'managerpass123', 'manager1@example.com', 'MANAGER'),
('librarian_2', 'password123', 'librarian2@example.com', 'LIBRARIAN'),
('admin_2', 'adminpass123', 'admin2@example.com', 'ADMIN'),
('manager_2', 'managerpass123', 'manager2@example.com', 'MANAGER'),
('librarian_3', 'password123', 'librarian3@example.com', 'LIBRARIAN'),
('admin_3', 'adminpass123', 'admin3@example.com', 'ADMIN'),
('manager_3', 'managerpass123', 'manager3@example.com', 'MANAGER'),
('librarian_4', 'password123', 'librarian4@example.com', 'LIBRARIAN');

INSERT INTO books (available_state, title, publisher, total_copies, copies_left, author, isbn, edition) VALUES
(TRUE, 'The Great Gatsby', 'Scribner', 5, 5, 'F. Scott Fitzgerald', '9780743273565', 1),
(FALSE, '1984', 'Secker & Warburg', 3, 1, 'George Orwell', '9780451524935', 2),
(TRUE, 'To Kill a Mockingbird', 'J.B. Lippincott & Co.', 7, 7, 'Harper Lee', '9780060935467', 1),
(TRUE, 'Moby Dick', 'Harper & Brothers', 10, 9, 'Herman Melville', '9781503280786', 1),
(TRUE, 'Pride and Prejudice', 'T. Egerton', 4, 4, 'Jane Austen', '9781503290563', 1),
(FALSE, 'War and Peace', 'The Russian Messenger', 6, 3, 'Leo Tolstoy', '9781420953801', 1),
(TRUE, 'The Odyssey', 'Penguin Classics', 8, 8, 'Homer', '9780140268867', 1),
(TRUE, 'The Catcher in the Rye', 'Little, Brown and Company', 5, 5, 'J.D. Salinger', '9780316769488', 1),
(TRUE, 'Crime and Punishment', 'The Russian Messenger', 6, 6, 'Fyodor Dostoevsky', '9780486415871', 1),
(FALSE, 'The Hobbit', 'George Allen & Unwin', 10, 0, 'J.R.R. Tolkien', '9780345339683', 3);

INSERT INTO magazines (available_state, title, publisher, total_copies, copies_left, editor, issn, volume) VALUES
(TRUE, 'National Geographic', 'National Geographic Society', 5, 5, 'Susan Johnson', '0027-9358', 1),
(FALSE, 'Time', 'Time Inc.', 10, 2, 'John Doe', '0040-781X', 2),
(TRUE, 'Scientific American', 'Springer Nature', 6, 6, 'Richard Roe', '0036-8733', 3),
(TRUE, 'The Economist', 'The Economist Group', 8, 8, 'Jane Smith', '0013-0613', 4),
(FALSE, 'The New Yorker', 'Condé Nast', 3, 0, 'Peter Anderson', '0028-792X', 1),
(TRUE, 'Popular Science', 'Bonnier', 7, 7, 'Michael Williams', '0032-4558', 5),
(TRUE, 'Forbes', 'Forbes Media', 5, 5, 'Mary Johnson', '0015-6914', 6),
(TRUE, 'Wired', 'Condé Nast', 6, 6, 'James Brown', '1059-1028', 7),
(FALSE, 'Vogue', 'Condé Nast', 4, 1, 'Anna Wintour', '0042-8000', 2),
(TRUE, 'Bloomberg Businessweek', 'Bloomberg L.P.', 8, 8, 'David Smith', '0007-7135', 8);


INSERT INTO transactions (order_date, approved_date, return_date, status, approved_by, ordered_by, resource_id) VALUES
('2024-01-01', '2024-01-02', '2024-01-10', 'RETURNED', 'admin1@example.com', 'john.doe@example.com', 1),
('2024-01-05', '2024-01-06', '2024-01-15', 'PENDING', 'admin2@example.com', 'jane.smith@example.com', 2),
('2024-01-07', NULL, NULL, 'PENDING', 'admin1@example.com', 'mike.jones@example.com', 3),
('2024-01-10', '2024-01-12', '2024-01-20', 'APPROVED', 'admin3@example.com', 'alice.williams@example.com', 4),
('2024-01-12', NULL, NULL, 'PENDING', 'manager1@example.com', 'bob.brown@example.com', 5),
('2024-01-15', NULL, NULL, 'PENDING', 'librarian1@example.com', 'charlie.davis@example.com', 6),
('2024-01-17', '2024-01-18', '2024-01-25', 'RETURNED', 'admin1@example.com', 'david.miller@example.com', 7),
('2024-01-20', NULL, NULL, 'PENDING', 'manager2@example.com', 'eve.white@example.com', 8),
('2024-01-22', '2024-01-23', '2024-01-30', 'APPROVED', 'librarian3@example.com', 'frank.lee@example.com', 9),
('2024-01-25', '2024-01-26', '2024-02-05', 'RETURNED', 'admin2@example.com', 'grace.kim@example.com', 10);
