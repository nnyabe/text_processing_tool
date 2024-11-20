# Advanced Library Management System

## Overview

The **Advanced Library Management System** is a fully-fledged Java-based application designed to handle library operations like managing books, patrons, transactions, and reservations. This system leverages key software development concepts such as **Object-Oriented Programming (OOP)**, **Data Structures**, **Database Design**, **SQL**, and **JDBC** to efficiently manage library resources. The user interface is built using **JavaFX**, providing a modern and interactive experience for users.

## Features

- **Book Management**:
    - Add, update, search, and view books in the library.
    - Manage book reservations and borrow status.

- **Patron Management**:
    - Track patron details (e.g., name, membership status).
    - View patron borrowing history.

- **Transaction Handling**:
    - Borrow and return books.
    - Reserve books for later borrowing.
    - Track transaction logs (borrow, return, reserve).

- **Search Functionality**:
    - Search for books and other resources by title, author, genre, or other criteria.

- **Admin Dashboard**:
    - Admins can view and approve transactions.
    - Manage library logs and user data.

- **Error Handling**:
    - Robust error management ensures system reliability and smooth user experience, including handling common database connection issues, invalid user input, etc.

## Technologies Used

- **Java 21 (LTS)**: Used for the core logic and object-oriented implementation.
- **JavaFX**: For building the graphical user interface (GUI).
- **MySQL**: Relational database management system (RDBMS) used for storing library data.
- **JDBC**: For database connectivity and interaction.
- **Maven**: For managing project dependencies and builds.

## Setup Instructions

### Prerequisites

Before running the application, ensure that you have the following installed:

- **Java 21 (LTS)** or later
- **MySQL**
- **Maven**
- **IDE** (IntelliJ IDEA, Eclipse, etc.)

### Setting Up the Database

1. **Install MySQL** if not already installed.

2. **Create Users** username in the .env file.
``````
library-management-system/
├── src/
│   └── java/
│       └── com/
│           └── example/
│               └── library_management_system/
│                   ├── controllers/          # Contains the main controller classes (BookController, TransactionController, etc.)
│                   ├── models/               # Contains the model classes for Books, Patrons, etc.
│                   ├── views/                # Contains the JavaFX views (UI components like BookView, PatronView, etc.)
│                   ├── utils/                # Contains utility classes like DatabaseConnection
│                   └── HelloApplication.java             # Entry point for the application
├── resources/
│   ├── com.example.library_management_system/                 # Contains all the FXML files for the views
│       ├── add-book-view.fxml     # FXML for the book management view
│       ├── dashboard-user-view.fxml   # FXML for the patron management view
│       ├── transactions-user-view.fxml  # FXML for the transaction management view
│       ├── admin-dashboard-view.fxml  # FXML for the admin dashboard
│       └── load-books-view.fxml   # FXML for the search functionality view on books)
├── pom.xml    # Dependency management file for Maven
└── README.md                 # Documentation
``````

## Building the Project

### Clone or Download the Repository

Clone or download this project from GitHub:

```bash
git clone https://github.com/nnyabe/library-management-system.git
```

### Import into IDE
Import the project into your preferred IDE (e.g., IntelliJ IDEA, Eclipse, etc.).

### Build the Project
If you are using Maven, run the following command to build the project:
```bash
mvn clean install
```
### Run the Application
Run the Main.java file as the entry point for the application. This will launch the JavaFX application.

### ERD Diagram
![ERD Diagram](https://github.com/nnyabe/labs/blob/main/library_management_system/ER-diagram.svg)

