package com.example.library_management_system.views;

import com.example.library_management_system.controllers.PatronController;
import com.example.library_management_system.controllers.TransactionController;
import com.example.library_management_system.exceptions.MySQLUserNotFound;
import com.example.library_management_system.modles.PatronModel;
import com.example.library_management_system.modles.UserSession;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * The {@code ReserveBookView} class handles the UI and functionality for reserving a book or resource.
 * It validates input and interacts with the backend to reserve a resource for a patron.
 */
public class ReserveBookView {

    @FXML
    private ComboBox<String> resourceTypeComboBox;
    @FXML
    private TextField resourceIdField;
    @FXML
    private TextField bookIdField;
    @FXML
    private TextField userIdField;
    @FXML
    private Button reserveButton;
    @FXML
    private Label statusLabel;

    private final String username = UserSession.getInstance().getUsername();

    /**
     * Initializes the reserve button's action.
     */
    @FXML
    public void initialize() {
        reserveButton.setOnAction(event -> handleReserveBook());
    }

    /**
     * Handles the book/resource reservation process by validating input,
     * checking if the patron exists, and calling the controller to reserve the resource.
     */
    @FXML
    private void handleReserveBook() {
        String bookId = resourceIdField.getText();
        String userId = userIdField.getText();
        String resourceType = resourceTypeComboBox.getValue();


        if (bookId.isEmpty() || userId.isEmpty() || resourceType == null) {
            statusLabel.setText("Please fill all fields.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {

            PatronController patronController = new PatronController();
            PatronModel user = patronController.getById(Integer.parseInt(userId));


            if (user == null) {
                throw new MySQLUserNotFound("Patron not found in the records.");
            } else {

                TransactionController transactionController = new TransactionController();
                transactionController.reserveResource(user.getEmail(), Integer.parseInt(bookId), resourceType);
            }
        } catch (SQLException | MySQLUserNotFound e) {
            throw new RuntimeException(e);
        }

        statusLabel.setText("Resource reserved successfully!");
        statusLabel.setStyle("-fx-text-fill: green;");
    }
}
