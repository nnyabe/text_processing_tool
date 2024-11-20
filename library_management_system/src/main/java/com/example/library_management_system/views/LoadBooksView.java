package com.example.library_management_system.views;

import com.example.library_management_system.controllers.BookController;
import com.example.library_management_system.modles.BookModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * The {@code LoadBooksView} class represents the view for displaying the list of books in the system.
 * It handles the initialization and binding of book data to a {@code TableView} for visualization.
 */
public class LoadBooksView {

    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;
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

    private List<BookModel> bookList;

    /**
     * Initializes the view by setting up the table columns and loading the book data into the {@code TableView}.
     * This method is automatically called by the FXMLLoader when the view is created.
     */
    @FXML
    public void initialize() {
        setUpTableColumns();
        loadBookData();

        searchButton.setOnAction(e-> handleSearch());
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
            bookList = new BookController().getAll();

            ObservableList<BookModel> books = FXCollections.observableArrayList(bookList);
            bookTableView.setItems(books);
        } catch (SQLException e) {
            throw new RuntimeException("Error loading book data", e);
        }
    }

    /**
     * Handles the search functionality for books based on the user's input.
     * It filters the book list by title and updates the table view with the filtered results.
     *
     * <p>The search is case-insensitive and matches books whose titles contain the search text.</p>
     *
     * <p>Note: This method assumes that the {@code bookList} has been previously initialized
     * and contains all the available book data.</p>
     */
    private void handleSearch() {
        String searchText = searchTextField.getText();
        List<BookModel> filteredBooks = bookList.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        ObservableList<BookModel> books = FXCollections.observableArrayList(filteredBooks);
        bookTableView.setItems(books);
    }

}
