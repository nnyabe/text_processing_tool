package com.example.library.controllers;


import com.example.library.modles.BookModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BookController extends BaseModelController<BookModel> {

    // Implement the abstract method to map the ResultSet to a BookModel
    @Override
    protected BookModel mapRowToModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String isbn = resultSet.getString("isbn");
        int categoryId = resultSet.getInt("categoryId");
        boolean availabilityStatus = resultSet.getBoolean("availabilityStatus");

        return new BookModel(id, title, author, isbn, categoryId, availabilityStatus);
    }

    // Implement the method to return the table name
    @Override
    protected String getTableName() {
        return "books";
    }

    // Implement the method to get the query for updating a book
    @Override
    protected String getUpdateQuery() {
        return "UPDATE books SET title = ?, author = ?, isbn = ?, categoryId = ?, availabilityStatus = ? WHERE id = ?";
    }

    // Implement the method to set the update parameters for the prepared statement
    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, BookModel book) throws SQLException {
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.setString(3, book.getIsbn());
//        preparedStatement.setInt(4, book.getCategoryId());
//        preparedStatement.setBoolean(5, book.isAvailabilityStatus());
        preparedStatement.setInt(6, book.getId());
    }
}
