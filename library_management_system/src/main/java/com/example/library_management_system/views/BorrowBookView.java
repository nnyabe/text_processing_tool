package com.example.library_management_system.views;

import com.example.library_management_system.controllers.PatronController;
import com.example.library_management_system.controllers.TransactionController;
import com.example.library_management_system.exceptions.MySQLUserNotFound;
import com.example.library_management_system.modles.PatronModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class BorrowBookView {
    @FXML
    private TextField resourceIdField;

    @FXML
    private ComboBox<String> resourceTypeComboBox;

    @FXML
    private TextField bookIdField;

    @FXML
    private TextField userIdField;

    @FXML
    private Button borrowButton;

    @FXML
    private Label statusLabel;

    /**
     * Initializes the view by setting the action for the borrow button.
     */
    @FXML
    public void initialize() {
        borrowButton.setOnAction(event -> {
            try {
                handleBorrowBook();
            } catch (MySQLUserNotFound e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Handles the borrowing process by validating input fields and performing the borrowing transaction.
     *
     * @throws MySQLUserNotFound if the patron is not found in the database.
     */
    private void handleBorrowBook() throws MySQLUserNotFound {
        String bookId = resourceIdField.getText();
        String userId = userIdField.getText();
        String resourceType = resourceTypeComboBox.getValue();

        // Validate input fields
        if (bookId.isEmpty() || userId.isEmpty() || resourceType == null) {
            statusLabel.setText("Please fill all fields.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Retrieve patron by userId
            PatronController patron = new PatronController();
            PatronModel user = patron.getById(Integer.parseInt(userId));

            if (user == null) {
                throw new MySQLUserNotFound("Patron not found in the records.");
            } else {
                // Borrow the resource
                TransactionController borrow = new TransactionController();
                borrow.borrowResource(user.getEmail(), Integer.parseInt(bookId), resourceType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Update the status label on successful borrow
        statusLabel.setText("Book borrowed successfully!");
        statusLabel.setStyle("-fx-text-fill: green;");
    }
}
