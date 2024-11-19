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

/**
 * The {@code TransactionsView} class represents the view for displaying and managing the user's transactions.
 * It handles the loading, displaying, and refreshing of transaction data in a table.
 */
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

    /**
     * Initializes the view by binding table columns, loading initial data, and setting up button actions.
     */
    @FXML
    public void initialize() {
        bindColumns();
        loadInitialData();
        setRefreshButtonAction();
    }

    /**
     * Binds the table columns to the properties of the {@code TransactionModel}.
     */
    private void bindColumns() {
        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        approvedDateColumn.setCellValueFactory(new PropertyValueFactory<>("approvedDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        resourceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("resourceType"));
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
    }

    /**
     * Loads the initial transaction data for the user and displays it in the table.
     */
    private void loadInitialData() {
        try {
            loadTransactions();
        } catch (SQLException e) {
            showError("Failed to load transactions", e.getMessage());
        }
    }

    /**
     * Sets the action for the refresh button to reload the transaction data.
     */
    private void setRefreshButtonAction() {
        refreshButton.setOnAction(event -> {
            try {
                loadTransactions();
            } catch (SQLException e) {
                showError("Failed to refresh transactions", e.getMessage());
            }
        });
    }

    /**
     * Loads the transactions for the user from the backend and updates the table view.
     *
     * @throws SQLException if an error occurs while retrieving transaction data from the database.
     */
    private void loadTransactions() throws SQLException {
        transactions.clear();
        transactions.addAll(transactionController.getUserTransactions(username));
        transactionsTable.setItems(transactions);
    }

    /**
     * Cancels a transaction and updates the table accordingly.
     *
     * @param transaction The transaction to be canceled.
     * @throws SQLException if an error occurs while canceling the transaction.
     */
    private void cancelTransaction(TransactionModel transaction) throws SQLException {
        if (transactionController.deleteById(transaction.getId())) {
            transactions.remove(transaction); // Remove the canceled transaction from the table
            showSuccess("Transaction Canceled", "The transaction has been successfully canceled.");
        } else {
            showError("Cancellation Failed", "The transaction could not be canceled.");
        }
    }

    /**
     * Displays an error message in the console (can be replaced with a JavaFX alert in production).
     *
     * @param title   The title of the error message.
     * @param message The error message to be displayed.
     */
    private void showError(String title, String message) {
        System.err.println(title + ": " + message);
    }

    /**
     * Displays a success message in the console (can be replaced with a JavaFX alert in production).
     *
     * @param title   The title of the success message.
     * @param message The success message to be displayed.
     */
    private void showSuccess(String title, String message) {
        System.out.println(title + ": " + message);
    }
}
