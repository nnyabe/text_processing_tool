package com.example.library_management_system.views;

import com.example.library_management_system.controllers.BookController;
import com.example.library_management_system.modles.BookModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;

public class LoadBooksView {

    @FXML
    private TableView<BookModel> bookTableView;
    @FXML
    private TableColumn<BookModel, Boolean> availableStateColumn;
    @FXML
    private TableColumn<BookModel, String> titleColumn;
    @FXML
    private TableColumn<BookModel, String> publisherColumn;
    @FXML
    private TableColumn<BookModel, Integer> totalCopiesColumn;
    @FXML
    private TableColumn<BookModel, Integer> copiesLeftColumn;
    @FXML
    private TableColumn<BookModel, String> authorColumn;
    @FXML
    private TableColumn<BookModel, String> isbnColumn;
    @FXML
    private TableColumn<BookModel, Integer> editionColumn;

    @FXML
    public void initialize() {
        // Set up the columns using the PropertyValueFactory
        availableStateColumn.setCellValueFactory(new PropertyValueFactory<>("availableState"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
        copiesLeftColumn.setCellValueFactory(new PropertyValueFactory<>("copiesLeft"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        editionColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        loadBookData();
    }
    private void loadBookData() {
        // Sample data (replace with database query or other source)
        try{
            List<BookModel> book = new BookController().getAll();
            ObservableList<BookModel> books = FXCollections.observableArrayList();

            books.addAll(book);
            bookTableView.setItems(books);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }





    }
}
