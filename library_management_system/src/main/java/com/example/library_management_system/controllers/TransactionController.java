package com.example.library_management_system.controllers;

import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.modles.Enums;
import com.example.library_management_system.modles.TransactionModel;
import com.example.library_management_system.utils.DBConnection;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing transactions in the library system.
 * This class handles CRUD operations and specific transaction actions like borrowing, reserving,
 * approving, and returning resources.
 */
public class TransactionController extends BaseModelController<TransactionModel> {

    /**
     * Maps a ResultSet row to a TransactionModel object.
     *
     * @param resultSet the ResultSet containing the data to be mapped
     * @return a TransactionModel object representing the row
     * @throws SQLException if there is an error accessing the ResultSet
     */
    @Override
    protected TransactionModel mapRowToModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        Date orderDate = resultSet.getDate("order_date");
        Date approveDate = resultSet.getDate("approved_date");
        Date returnDate = resultSet.getDate("return_date");
        String approvedBy = resultSet.getString("approved_by");
        String orderedBy = resultSet.getString("ordered_by");
        int resourceId = resultSet.getInt("resource_id");
        String resourceType = resultSet.getString("resource_type");
        Enums.Stautus status = Enums.Stautus.valueOf(resultSet.getString("status"));
        Enums.Types transactionType = Enums.Types.valueOf(resultSet.getString("transaction_type"));
        return new TransactionModel(id, orderDate, approveDate, returnDate, status, approvedBy, orderedBy, resourceId, resourceType, transactionType);
    }

    /**
     * Returns the name of the table used for transaction records in the database.
     *
     * @return the name of the table ("transactions")
     */
    @Override
    protected String getTableName() {
        return "transactions";
    }

    /**
     * Returns the SQL query for updating a transaction record.
     *
     * @return the SQL update query string
     */
    @Override
    protected String getUpdateQuery() {
        return "UPDATE transactions SET  approved_date = ?, return_date = ?, status = ? " +
                "approved_by = ?, ordered_by = ? , resource_id = ? " +
                "WHERE id = ?";
    }

    /**
     * Returns the SQL query for creating a new transaction record.
     *
     * @return the SQL insert query string
     */
    @Override
    protected String getCreateQuery() {
        return "INSERT INTO transactions (approved_date, return_date, status, " +
                "approved_by, ordered_by, resource_id, resource_type, transaction_type)" +
                " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    /**
     * Sets the parameters for the update query in the PreparedStatement.
     *
     * @param preparedStatement the PreparedStatement to set parameters on
     * @param transactions the TransactionModel object containing the updated data
     * @throws SQLException if there is an error setting the parameters
     */
    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, TransactionModel transactions)
            throws SQLException {
        preparedStatement.setDate(1, transactions.getApprovedDate());
        preparedStatement.setDate(2, transactions.getReturnDate());
        preparedStatement.setString(3, transactions.getStatus().toString());
        preparedStatement.setString(4, transactions.getApprovedBy());
        preparedStatement.setString(5, transactions.getOrderedBy());
        preparedStatement.setInt(6, transactions.getResourceId());
        preparedStatement.setInt(7, transactions.getId());
    }

    /**
     * Sets the parameters for the create query in the PreparedStatement.
     *
     * @param preparedStatement the PreparedStatement to set parameters on
     * @param transactions the TransactionModel object containing the data to be inserted
     * @throws SQLException if there is an error setting the parameters
     */
    @Override
    protected void setCreateParameters(PreparedStatement preparedStatement, TransactionModel transactions)
            throws SQLException {
        preparedStatement.setDate(1, transactions.getApprovedDate());
        preparedStatement.setDate(2, transactions.getReturnDate());
        preparedStatement.setString(3, transactions.getStatus().toString());
        preparedStatement.setString(4, transactions.getApprovedBy());
        preparedStatement.setString(5, transactions.getOrderedBy());
        preparedStatement.setInt(6, transactions.getResourceId());
        preparedStatement.setString(7, transactions.getResourceType());
        preparedStatement.setString(8, transactions.getTransactionType().toString());
    }

    /**
     * Creates a new transaction in the database.
     *
     * @param transaction the TransactionModel object containing the data to be inserted
     * @return true if the transaction was successfully created, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean createOne(TransactionModel transaction) throws SQLException {
        return super.createOne(transaction);
    }

    /**
     * Retrieves all transactions from the database.
     *
     * @return a list of all TransactionModel objects
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public List<TransactionModel> getAll() throws SQLException {
        return super.getAll();
    }

    /**
     * Retrieves a transaction by its ID from the database.
     *
     * @param id the ID of the transaction to retrieve
     * @return the TransactionModel object corresponding to the ID, or null if not found
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public TransactionModel getById(int id) throws SQLException {
        return super.getById(id);
    }

    /**
     * Deletes a transaction by its ID from the database.
     *
     * @param id the ID of the transaction to delete
     * @return true if the transaction was successfully deleted, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean deleteById(int id) throws SQLException {
        return super.deleteById(id);
    }

    /**
     * Updates a transaction in the database by its ID.
     *
     * @param transaction the TransactionModel object containing the updated data
     * @return true if the transaction was successfully updated, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean updateById(TransactionModel transaction) throws SQLException {
        return super.updateById(transaction);
    }

    /**
     * Retrieves all transactions associated with a specific user.
     *
     * @param username the username of the patron to retrieve transactions for
     * @return a list of TransactionModel objects associated with the user
     * @throws SQLException if there is an error accessing the database
     */
    public List<TransactionModel> getUserTransactions(String username) throws SQLException {
        String query = """
        SELECT t.*
        FROM transactions t
        INNER JOIN patrons p ON t.ordered_by = p.email
         WHERE p.username = ?
        """;
        List<TransactionModel> transactions = new ArrayList<>();

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    transactions.add(mapRowToModel(resultSet));
                }
            }
        } catch (MySQLConnectionException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }

    /**
     * Approves a transaction by updating its status and related resource information.
     *
     * @param transactionId the ID of the transaction to approve
     * @param librarianName the name of the librarian approving the transaction
     * @throws SQLException if there is an error accessing the database
     * @throws MySQLConnectionException if there is an error establishing a database connection
     */
    public void approveTransaction(int transactionId, String librarianName) throws SQLException, MySQLConnectionException {
        String getResourceInfo = "SELECT resource_id, resource_type FROM transactions WHERE id = ?";
        String resourceType = "";

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getResourceInfo)) {
            preparedStatement.setInt(1, transactionId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resourceType = resultSet.getString("resource_type");
            } else {
                throw new SQLException("Transaction not found or not in Pending state");
            }
        }

        if ("BOOK".equalsIgnoreCase(resourceType)) {
            String query = "UPDATE transactions t JOIN books b ON t.resource_id = b.id " +
                    "SET t.status = 'APPROVED', t.approved_date = CURRENT_TIMESTAMP, t.approved_by = ?, " +
                    "b.copies_left = b.copies_left - 1 " +
                    "WHERE t.id = ? AND b.copies_left > 0";
            executeUpdate(query, preparedStatement -> {
                        preparedStatement.setString(1, librarianName);
                        preparedStatement.setInt(2, transactionId);
                    }, "Transaction Approved", "The transaction has been successfully approved.",
                    "Approval Failed", "The transaction could not be approved or is not in a pending state.");
        } else if ("MAGAZINE".equalsIgnoreCase(resourceType)) {
            String query = "UPDATE transactions t JOIN magazines m ON t.resource_id = m.id " +
                    "SET t.status = 'APPROVED', t.approved_date = CURRENT_TIMESTAMP, t.approved_by = ?, " +
                    "m.copies_left = m.copies_left - 1 " +
                    "WHERE t.id = ? AND m.copies_left > 0";

            executeUpdate(query, preparedStatement -> {
                        preparedStatement.setString(1, librarianName);
                        preparedStatement.setInt(2, transactionId);
                    }, "Transaction Approved", "The transaction has been successfully approved.",
                    "Approval Failed", "The transaction could not be approved or is not in a pending state.");
        } else {
            throw new SQLException("Invalid resource type.");
        }
    }

    /**
     * Returns a resource associated with a transaction.
     *
     * @param transactionId the ID of the transaction to return
     * @throws SQLException if there is an error accessing the database
     * @throws MySQLConnectionException if there is an error establishing a database connection
     */
    public void returnResource(int transactionId) throws SQLException, MySQLConnectionException {
        String getResourceInfo = "SELECT resource_id, resource_type FROM transactions WHERE id = ? AND status = 'APPROVED'";
        String resourceType = "";

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getResourceInfo)) {
            preparedStatement.setInt(1, transactionId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resourceType = resultSet.getString("resource_type");
            } else {
                throw new SQLException("Transaction not found or not in Pending state");
            }
        }

        if ("BOOK".equalsIgnoreCase(resourceType)) {
            String query = "UPDATE transactions t JOIN books b ON t.resource_id = b.id " +
                    "SET t.status = 'RETURNED', t.return_date = CURRENT_TIMESTAMP, " +
                    "b.copies_left = b.copies_left + 1 " +
                    "WHERE t.id = ? AND b.copies_left >= 0";

            executeUpdate(query, preparedStatement -> {
                        preparedStatement.setInt(1, transactionId);
                    }, "Transaction Approved", "The transaction has been successfully approved.",
                    "Approval Failed", "The transaction could not be approved or is not in a pending state.");
        } else if ("MAGAZINE".equalsIgnoreCase(resourceType)) {
            String query = "UPDATE transactions t JOIN magazines m ON t.resource_id = m.id " +
                    "SET t.status = 'RETURNED', t.return_date = CURRENT_TIMESTAMP, " +
                    "m.copies_left = m.copies_left + 1 " +
                    "WHERE t.id = ? AND m.copies_left >= 0";

            executeUpdate(query, preparedStatement -> {
                        preparedStatement.setInt(1, transactionId);
                    }, "Transaction Approved", "The transaction has been successfully approved.",
                    "Approval Failed", "The transaction could not be approved or is not in a pending state.");
        } else {
            throw new SQLException("Invalid resource type.");
        }
    }

    /**
     * Creates a borrow request for a resource.
     *
     * @param orderedBy the username of the patron ordering the resource
     * @param resourceId the ID of the resource to borrow
     * @param resourceType the type of resource being borrowed (e.g., "BOOK" or "MAGAZINE")
     * @throws SQLException if there is an error accessing the database
     */
    public void borrowResource(String orderedBy, int resourceId, String resourceType) throws SQLException {
        String query = "INSERT INTO transactions (ordered_by, resource_id, resource_type, transaction_type, status) " +
                "VALUES (?, ?, ?, 'BORROW', 'PENDING')";

        executeUpdate(query, preparedStatement -> {
                    preparedStatement.setString(1, orderedBy);
                    preparedStatement.setInt(2, resourceId);
                    preparedStatement.setString(3, resourceType);
                }, "Borrow Request Successful", "The borrow request has been submitted.",
                "Borrow Request Failed", "An error occurred while processing the borrow request.");
    }

    /**
     * Creates a reservation request for a resource.
     *
     * @param orderedBy the username of the patron reserving the resource
     * @param resourceId the ID of the resource to reserve
     * @param resourceType the type of resource being reserved (e.g., "BOOK" or "MAGAZINE")
     * @throws SQLException if there is an error accessing the database
     */
    public void reserveResource(String orderedBy, int resourceId, String resourceType) throws SQLException {
        String query = "INSERT INTO transactions (ordered_by, resource_id, resource_type, transaction_type, status) " +
                "VALUES (?, ?, ?, 'RESERVATION', 'PENDING')";

        executeUpdate(query, preparedStatement -> {
                    preparedStatement.setString(1, orderedBy);
                    preparedStatement.setInt(2, resourceId);
                    preparedStatement.setString(3, resourceType);
                }, "Reservation Successful", "The resource has been reserved.",
                "Reservation Failed", "An error occurred while reserving the resource.");
    }

    /**
     * Executes an update query with the provided parameters and shows alerts based on the result.
     *
     * @param query the SQL query to execute
     * @param parameterSetter a lambda to set parameters on the PreparedStatement
     * @param successTitle the title for a success alert
     * @param successMessage the message for a success alert
     * @param failureTitle the title for a failure alert
     * @param failureMessage the message for a failure alert
     * @throws SQLException if there is an error executing the query
     */
    private void executeUpdate(String query, SQLConsumer<PreparedStatement> parameterSetter,
                               String successTitle, String successMessage,
                               String failureTitle, String failureMessage) throws SQLException {
        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            parameterSetter.accept(preparedStatement);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, successTitle, successMessage);
            } else {
                showAlert(Alert.AlertType.WARNING, failureTitle, failureMessage);
            }

        } catch (MySQLConnectionException e) {
            throw new SQLException("Error fetching data: " + e.getMessage());
        }
    }

    /**
     * Displays an alert with the given type, title, and content.
     *
     * @param alertType the type of the alert
     * @param title the title of the alert
     * @param content the content of the alert
     */
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Functional interface for handling SQL-related operations on PreparedStatements.
     */
    @FunctionalInterface
    interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }
}
