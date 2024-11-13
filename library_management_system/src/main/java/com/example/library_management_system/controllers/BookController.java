package com.example.library_management_system.controllers;


import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.modles.BookModel;
import com.example.library_management_system.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookController {

    public List<BookModel> getAllBooks() throws SQLException{
        List<BookModel> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try(Connection connection = DBConnection.createConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);){


            while(result.next()){
                int book_id = result.getInt("_id");
                String title = result.getNString("title");
                String author = result.getNString("author");
                String isbn = result.getNString("isbn");
                int categoryId = result.getInt("categoryId");
                boolean availabilityStatus = result.getBoolean("availabilityStatus");

                BookModel newBook = new BookModel();
                books.add(newBook);
            }
        } catch (MySQLConnectionException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

//    public BookModel getBookById(int book_id) throws SQLException{
//        String query = "SELECT * FROM books WHERE _id = ?";
//
//        BookModel newBook = null;
//        try(Connection connection = DBConnection.connectDB();
//            PreparedStatement preparedstatement = connection.prepareStatement(query)){
//
//            preparedstatement.setInt(1, book_id);
//            try(ResultSet result = preparedstatement.executeQuery()){
//                if(result.next()){
//                    String title = result.getString("title");
//                    String author = result.getString("author");
//                    String isbn = result.getString("isbn");
//                    int categoryId = result.getInt("categoryId");
//                    boolean availabilityStatus = result.getBoolean("availabilityStatus");
//
//                    newBook = new BookModel(book_id, title, author,
//                            isbn, categoryId, availabilityStatus);
//                }
//            }
//            catch(SQLException e) {
//                System.out.println("Couldn't find Book by Id:" + e.getMessage());
//
//            }
//
//
//        }
//
//        return newBook;
//    }
//
//    public boolean deleteBookById(int book_id) throws SQLException {
//        String query = "DELETE FROM books WHERE _id = ?";
//        int rowsAffected = 0;
//
//        try (Connection connection = DBConnection.connectDB();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setInt(1, book_id);
//            rowsAffected = preparedStatement.executeUpdate();
//        }
//
//        return rowsAffected > 0;
//    }
//
//    public boolean updateBookById(BookModel book) throws SQLException {
//        String query = "UPDATE books SET title = ?, author = ?, isbn = ?, categoryId = ?, availabilityStatus = ? WHERE _id = ?";
//        int rowsAffected = 0;
//
//        try (Connection connection = DBConnection.connectDB();
//             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setString(1, book.getTitle());
//            preparedStatement.setString(2, book.getAuthor());
//            preparedStatement.setString(3, book.getIsbn());
//            preparedStatement.setInt(4, book.getCategoryId());
//            preparedStatement.setBoolean(5, book.isAvailabilityStatus());
//            preparedStatement.setInt(6, book.getBookId());
//
//            rowsAffected = preparedStatement.executeUpdate();
//        }
//
//        return rowsAffected > 0;
//    }

}