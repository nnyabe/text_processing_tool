package com.example.library_management_system.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BorrowBookView{
    @FXML
    private TextField bookIdField;

    @FXML
    private TextField userIdField;

    @FXML
    private DatePicker borrowDatePicker;

    @FXML
    private Button borrowButton;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        borrowButton.setOnAction(event -> handleBorrowBook());
    }

    private void handleBorrowBook() {
        String bookId = bookIdField.getText();
        String userId = userIdField.getText();
        if (bookId.isEmpty() || userId.isEmpty() || borrowDatePicker.getValue() == null) {
            statusLabel.setText("Please fill all fields.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Add database logic here for borrowing a book

        statusLabel.setText("Book borrowed successfully!");
        statusLabel.setStyle("-fx-text-fill: green;");
    }
}
