package com.example.library_management_system.views;

import com.example.library_management_system.controllers.PatronController;
import com.example.library_management_system.controllers.TransactionController;
import com.example.library_management_system.exceptions.MySQLUserNotFound;
import com.example.library_management_system.modles.PatronModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class ReserveBookView {
    @FXML
    private ComboBox<String> resourceTypeComboBox;;
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

    @FXML
    public void initialize() {
        reserveButton.setOnAction(event -> handleReserveBook());
    }

    @FXML
    private void handleReserveBook() {
        String bookId = resourceIdField.getText();
        String userId = userIdField.getText();
        String resourceType =resourceTypeComboBox.getValue();
        if (bookId.isEmpty() || userId.isEmpty() || resourceType == null ) {
            statusLabel.setText("Please fill all fields.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        try{
            PatronController patron = new PatronController();


            PatronModel user = new PatronModel();
            user = patron.getById(Integer.parseInt(userId));

            if (user == null){
                throw new MySQLUserNotFound("Patron not found in the records.");
            }else{
                TransactionController reserve = new TransactionController();
                reserve.reserveResource(userId, Integer.parseInt(bookId), resourceType);
            }
        } catch (SQLException | MySQLUserNotFound e) {
            throw new RuntimeException(e);
        }


        statusLabel.setText("Resource reserved successfully!");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

}
