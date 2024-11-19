package com.example.library_management_system.views;

import com.example.library_management_system.controllers.TransactionController;
import com.example.library_management_system.modles.TransactionModel;
import com.example.library_management_system.modles.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class TransactionsView {
    @FXML
    private TableView<TransactionModel> transactionsTable;
    @FXML
    private TableColumn<TransactionModel, Integer> transactionIdColumn;
    @FXML
    private TableColumn<TransactionModel, String> orderDateColumn;
    @FXML
    private TableColumn<TransactionModel, String> approvedDateColumn;
    @FXML
    private TableColumn<TransactionModel, String> returnDateColumn;
    @FXML
    private TableColumn<TransactionModel, String> statusColumn;
    @FXML
    private TableColumn<TransactionModel, String> resourceTypeColumn;
    @FXML
    private TableColumn<TransactionModel, String> transactionTypeColumn;
    @FXML
    private TableColumn<TransactionModel, Void> actionColumn;

    @FXML
    private Button refreshButton;

    private final ObservableList<TransactionModel> transactions = FXCollections.observableArrayList();
    private final String username = UserSession.getInstance().getUsername();

    private final TransactionController transactionController = new TransactionController();

    @FXML
    public void initialize() {
        // Bind columns to Transaction properties
        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        approvedDateColumn.setCellValueFactory(new PropertyValueFactory<>("approvedDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        resourceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("resourceType"));
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));

        // Add actions column
//        addActionButtons();

        // Load initial data
        try {
            loadTransactions();
        } catch (SQLException e) {
            showError("Failed to load transactions", e.getMessage());
        }

        // Refresh button handler
        refreshButton.setOnAction(event -> {
            try {
                loadTransactions();
            } catch (SQLException e) {
                showError("Failed to refresh transactions", e.getMessage());
            }
        });
    }

//    private void addActionButtons() {
//        actionColumn.setCellFactory(col -> new TableCell<>() {
//            private final Button cancelButton = new Button("Cancel");
//
//            {
//                cancelButton.setOnAction(event -> {
//                    TransactionModel transaction = getTableView().getItems().get(getIndex());
//                    if ("PENDING".equals(transaction.getStatus())) {
//                        try {
//                            cancelTransaction(transaction);
//                        } catch (SQLException e) {
//                            showError("Failed to cancel transaction", e.getMessage());
//                        }
//                    }
//                });
//            }
//
//            @Override
//            protected void updateItem(Void item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    setGraphic(cancelButton);
//                }
//            }
//        });
//    }

    private void loadTransactions() throws SQLException {
        System.out.println(username);

        transactions.clear();
        transactions.addAll(transactionController.getUserTransactions(username));
        transactionsTable.setItems(transactions);
    }

    private void cancelTransaction(TransactionModel transaction) throws SQLException {
        if (transactionController.deleteById(transaction.getId())) {
            transactions.remove(transaction); // Remove the canceled transaction from the table
            showSuccess("Transaction Canceled", "The transaction has been successfully canceled.");
        } else {
            showError("Cancellation Failed", "The transaction could not be canceled.");
        }
    }

    private void showError(String title, String message) {
        System.err.println(title + ": " + message); // Replace with JavaFX Alert for production
    }

    private void showSuccess(String title, String message) {
        System.out.println(title + ": " + message); // Replace with JavaFX Alert for production
    }
}
