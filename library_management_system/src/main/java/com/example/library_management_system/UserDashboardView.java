package com.example.library.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class UserDashboardView {
    @FXML
    private AnchorPane contentArea;
    @FXML
    private Label contentLabel;
//    @FXML
//    private VBox sidebar;

    @FXML
    private void showBooks() {
        contentLabel.setText("Displaying all available books...");
        // Further code to populate the contentArea with book data
    }

    @FXML
    private void showMagazines() {
        contentLabel.setText("Displaying all available magazines...");
        // Further code to populate the contentArea with magazine data
    }

    @FXML
    private void showTransactions() {
        contentLabel.setText("Displaying all transactions...");
        // Further code to populate the contentArea with transaction data
    }
}
