package com.example.library_management_system.views;

import com.example.library_management_system.controllers.BookController;
import com.example.library_management_system.modles.BookModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * The {@code LoadBooksView} class represents the view for displaying the list of books in the system.
 * It handles the initialization and binding of book data to a {@code TableView} for visualization.
 */
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

    /**
     * Initializes the view by setting up the table columns and loading the book data into the {@code TableView}.
     * This method is automatically called by the FXMLLoader when the view is created.
     */
    @FXML
    public void initialize() {
        setUpTableColumns();
        loadBookData();
    }

    /**
     * Sets up the table columns to bind the data from the {@code BookModel} to the table.
     * Each column is linked to a specific property of the {@code BookModel} using the {@code PropertyValueFactory}.
     */
    private void setUpTableColumns() {
        availableStateColumn.setCellValueFactory(new PropertyValueFactory<>("availableState"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
        copiesLeftColumn.setCellValueFactory(new PropertyValueFactory<>("copiesLeft"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        editionColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    }

    /**
     * Loads the book data from the database using the {@code BookController} and populates the {@code TableView}.
     * The data is retrieved as a list, which is then converted to an {@code ObservableList} to be displayed in the table.
     */
    private void loadBookData() {
        try {
            List<BookModel> bookList = new BookController().getAll();
            ObservableList<BookModel> books = FXCollections.observableArrayList();
            books.addAll(bookList);
            bookTableView.setItems(books);
        } catch (SQLException e) {
            throw new RuntimeException("Error loading book data", e);
        }
    }
}
