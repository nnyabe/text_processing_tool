package com.example.library_management_system.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReserveBookView {
    @FXML
    private TextField bookIdField;

    @FXML
    private TextField userIdField;

    @FXML
    private DatePicker reservationDatePicker;

    @FXML
    private Button reserveButton;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        reserveButton.setOnAction(event -> handleReserveBook());
    }

    private void handleReserveBook() {
        String bookId = bookIdField.getText();
        String userId = userIdField.getText();
        if (bookId.isEmpty() || userId.isEmpty() || reservationDatePicker.getValue() == null) {
            statusLabel.setText("Please fill all fields.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Add database logic here for reserving a book

        statusLabel.setText("Book reserved successfully!");
        statusLabel.setStyle("-fx-text-fill: green;");
    }
}
