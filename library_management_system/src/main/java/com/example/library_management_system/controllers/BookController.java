package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.BookModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller class for managing books in the library system.
 * This class handles CRUD operations for book records in the database.
 */
public class BookController extends BaseModelController<BookModel> {

    /**
     * Maps a ResultSet row to a BookModel object.
     *
     * @param resultSet the ResultSet containing the data to be mapped
     * @return a BookModel object representing the row
     * @throws SQLException if there is an error accessing the ResultSet
     */
    @Override
    protected BookModel mapRowToModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String isbn = resultSet.getString("isbn");
        boolean availabilityStatus = resultSet.getBoolean("available_state");
        String publisher = resultSet.getString("publisher");
        int totalCopies = resultSet.getInt("total_copies");
        int copiesLeft = resultSet.getInt("copies_left");
        int edition = resultSet.getInt("edition");
        Date createdAt = resultSet.getDate("created_at");
        Date updatedAt = resultSet.getDate("updated_at");

        return new BookModel(id, availabilityStatus, title, publisher,
                totalCopies, copiesLeft,
                author, isbn, edition, createdAt, updatedAt);
    }

    /**
     * Returns the name of the table used for book records in the database.
     *
     * @return the name of the table ("books")
     */
    @Override
    protected String getTableName() {
        return "books";
    }

    /**
     * Returns the SQL query for updating a book record.
     *
     * @return the SQL update query string
     */
    @Override
    protected String getUpdateQuery() {
        return "UPDATE books SET available_state = ?, title = ?, publisher = ?, total_copies = ? " +
                "copies_left = ?, author = ? , isbn = ? , edition = ? " +
                "WHERE id = ?";
    }

    /**
     * Sets the parameters for the update query in the PreparedStatement.
     *
     * @param preparedStatement the PreparedStatement to set parameters on
     * @param book the BookModel object containing the updated data
     * @throws SQLException if there is an error setting the parameters
     */
    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, BookModel book) throws SQLException {
        preparedStatement.setBoolean(1, book.isAvailableState());
        preparedStatement.setString(2, book.getTitle());
        preparedStatement.setString(3, book.getPublisher());
        preparedStatement.setInt(4, book.getTotalCopies());
        preparedStatement.setInt(5, book.getCopiesLeft());
        preparedStatement.setString(6, book.getAuthor());
        preparedStatement.setString(7, book.getIsbn());
        preparedStatement.setInt(8, book.getEdition());
        preparedStatement.setInt(9, book.getId());
    }

    /**
     * Returns the SQL query for creating a new book record.
     *
     * @return the SQL insert query string
     */
    @Override
    public String getCreateQuery() {
        return "INSERT INTO books (available_state, title, publisher, total_copies, copies_left, author, isbn, edition)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    /**
     * Sets the parameters for the create query in the PreparedStatement.
     *
     * @param preparedStatement the PreparedStatement to set parameters on
     * @param book the BookModel object containing the data to be inserted
     * @throws SQLException if there is an error setting the parameters
     */
    @Override
    protected void setCreateParameters(PreparedStatement preparedStatement, BookModel book) throws SQLException {
        preparedStatement.setBoolean(1, book.isAvailableState());
        preparedStatement.setString(2, book.getTitle());
        preparedStatement.setString(3, book.getPublisher());
        preparedStatement.setInt(4, book.getTotalCopies());
        preparedStatement.setInt(5, book.getCopiesLeft());
        preparedStatement.setString(6, book.getAuthor());
        preparedStatement.setString(7, book.getIsbn());
        preparedStatement.setInt(8, book.getEdition());
    }

    /**
     * Retrieves all books from the database.
     *
     * @return a list of all BookModel objects
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public List<BookModel> getAll() throws SQLException {
        return super.getAll();
    }

    /**
     * Retrieves a book by its ID from the database.
     *
     * @param id the ID of the book to retrieve
     * @return the BookModel object corresponding to the ID, or null if not found
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public BookModel getById(int id) throws SQLException {
        return super.getById(id);
    }

    /**
     * Deletes a book by its ID from the database.
     *
     * @param id the ID of the book to delete
     * @return true if the book was successfully deleted, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean deleteById(int id) throws SQLException {
        return super.deleteById(id);
    }

    /**
     * Updates a book in the database by its ID.
     *
     * @param book the BookModel object containing the updated data
     * @return true if the book was successfully updated, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean updateById(BookModel book) throws SQLException {
        return super.updateById(book);
    }

    /**
     * Creates a new book in the database.
     *
     * @param book the BookModel object containing the data to be inserted
     * @return true if the book was successfully created, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean createOne(BookModel book) throws SQLException {
        return super.createOne(book);
    }
}
