package com.example.library_management_system.views;

import com.example.library_management_system.controllers.BookController;
import com.example.library_management_system.controllers.MagazineController;
import com.example.library_management_system.modles.BookModel;
import com.example.library_management_system.modles.MagazineModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class DeleteResourceView{

    @FXML private ComboBox<String> resourceTypeComboBox;  // ComboBox to select resource type (Book or Magazine)
    @FXML private TextField searchField;  // Search field to enter the resource ID
    @FXML private VBox resourceDetails;  // VBox to display resource details
    @FXML private Button deleteButton;   // Button to delete the resource
    @FXML private Label resourceTitleLabel;  // Label for title
    @FXML private Label resourceAuthorLabel; // Label for author/editor
    @FXML private Label resourcePublisherLabel; // Label for publisher
    @FXML private Label resourceIdLabel;   // Label for resource ID
    @FXML private Label statusLabel;      // Status label for success or error messages
    @FXML private Button searchButton;

    private final BookController bookService = new BookController();  // Assuming BookService handles book data
    private final MagazineController magazineService = new MagazineController();  // Assuming MagazineService handles magazine data

    @FXML
    private void initialize() {
        // Initialize ComboBox to select resource type
        searchButton.setOnAction(e -> {
            System.out.println("Entered the click");
            try {
                handleSearchButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        deleteButton.setOnAction( e-> {
            try {
                handleDeleteButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        resourceTypeComboBox.getSelectionModel().selectFirst();
        resourceDetails.setVisible(false);

    }

    @FXML
    private void handleSearchButton() throws SQLException {
        String searchTerm = searchField.getText();

        if (searchTerm.isEmpty()) {
            statusLabel.setText("Please enter a resource ID.");
            return;
        }

        String selectedResourceType = resourceTypeComboBox.getSelectionModel().getSelectedItem();

        // Search for the resource based on selected type
        if (selectedResourceType.equals("Book")) {
            BookModel book = bookService.getById(Integer.parseInt(searchTerm));
            if (book != null) {
                displayBookDetails(book);
            } else {
                statusLabel.setText("Book not found.");
                resourceDetails.setVisible(false);
            }
        } else if (selectedResourceType.equals("Magazine")) {
            MagazineModel magazine = magazineService.getById(Integer.parseInt(searchTerm));
            if (magazine != null) {
                displayMagazineDetails(magazine);
            } else {
                statusLabel.setText("Magazine not found.");
                resourceDetails.setVisible(false);
            }
        }
    }

    private void displayBookDetails(BookModel book) {
        resourceTitleLabel.setText("Title: " + book.getTitle());
        resourceAuthorLabel.setText("Author: " + book.getAuthor());
        resourcePublisherLabel.setText("Publisher: " + book.getPublisher());
        resourceIdLabel.setText("ID: " + book.getId());
        resourceDetails.setVisible(true);
        statusLabel.setText("");  // Clear status label
    }

    private void displayMagazineDetails(MagazineModel magazine) {
        resourceTitleLabel.setText("Title: " + magazine.getTitle());
        resourceAuthorLabel.setText("Editor: " + magazine.getEditor());
        resourcePublisherLabel.setText("Publisher: " + magazine.getPublisher());
        resourceIdLabel.setText("ID: " + magazine.getId());
        resourceDetails.setVisible(true);
        statusLabel.setText("");  // Clear status label
    }

    @FXML
    private void handleDeleteButton() throws SQLException {
        String selectedResourceType = resourceTypeComboBox.getSelectionModel().getSelectedItem();
        int resourceId = Integer.parseInt(searchField.getText());

        System.out.println(resourceId);
        boolean success = false;

        if (selectedResourceType.equals("Book")) {
            success = bookService.deleteById(resourceId);
        } else if (selectedResourceType.equals("Magazine")) {

            success = magazineService.deleteById(resourceId);
//            System.out.println(success);
        }

        if (success) {
            statusLabel.setText(selectedResourceType + " deleted successfully.");
            resourceDetails.setVisible(false);
        } else {
            statusLabel.setText("Failed to delete " + selectedResourceType + ". Please try again.");
        }
    }
}
