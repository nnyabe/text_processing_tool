package com.example.library_management_system.controllers;

import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.modles.Enums;
import com.example.library_management_system.modles.TransactionModel;
import com.example.library_management_system.utils.DBConnection;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionController extends BaseModelController<TransactionModel>{

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
        return new TransactionModel(id, orderDate, approveDate, returnDate, status,approvedBy, orderedBy,resourceId , resourceType, transactionType);
    }

    @Override
    protected String getTableName(){
        return "transactions";
    }

    @Override
    protected String getUpdateQuery(){
        return "UPDATE transactions SET  approved_date = ?, return_date = ?, status = ? " +
                "approved_by = ?, ordered_by = ? , resource_id = ? " +
                "WHERE id = ?";
    }

    @Override
    protected String getCreateQuery(){
        return "INSERT INTO transactions (approved_date, return_date, status, " +
                "approved_by, ordered_by, resource_id, resource_type, transaction_type)" +
                " VALUES ( ?, ?, ?, ?, ?, ?,?, ?)";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, TransactionModel transactions)
        throws SQLException{
//        preparedStatement.setDate(1, transactions.getOrderDate());
        preparedStatement.setDate(1, transactions.getApprovedDate());
        preparedStatement.setDate(2, transactions.getReturnDate());
        preparedStatement.setString(3, transactions.getStatus().toString());
        preparedStatement.setString(4, transactions.getApprovedBy());
        preparedStatement.setString(5, transactions.getOrderedBy());
        preparedStatement.setInt(6, transactions.getResourceId());
        preparedStatement.setInt(7, transactions.getId());
    }

    @Override
    protected void setCreateParameters(PreparedStatement preparedStatement, TransactionModel transactions)
        throws SQLException{
        preparedStatement.setDate(1, transactions.getApprovedDate());
        preparedStatement.setDate(2, transactions.getReturnDate());
        preparedStatement.setString(3, transactions.getStatus().toString());
        preparedStatement.setString(4, transactions.getApprovedBy());
        preparedStatement.setString(5, transactions.getOrderedBy());
        preparedStatement.setInt(6, transactions.getResourceId());
        preparedStatement.setString(7, transactions.getResourceType());
        preparedStatement.setString(8, transactions.getTransactionType().toString());

    }

    @Override
    public boolean createOne(TransactionModel transaction) throws SQLException{
        return super.createOne(transaction);
    }

    @Override
    public List<TransactionModel>getAll()throws SQLException{
        return super.getAll();
    }

    @Override
    public TransactionModel getById(int id) throws SQLException{
        return super.getById(id);
    }

    @Override
    public boolean deleteById(int id) throws SQLException{
        return super.deleteById(id);
    }

    @Override
    public boolean updateById(TransactionModel transaction) throws SQLException{
        return super.updateById(transaction);
    }

    public List<TransactionModel> getUserTransactions(String username) throws SQLException {
        String query = "SELECT * FROM transactions WHERE ordered_by = ?";
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

    public void approveTransaction(int transactionId, String librarianName) throws SQLException {
        String query = "UPDATE transactions SET status = 'APPROVED', approved_date = CURRENT_TIMESTAMP, approved_by = ? " +
                "WHERE id = ? AND status = 'PENDING'";

        executeUpdate(query, preparedStatement -> {
                    preparedStatement.setString(1, librarianName);
                    preparedStatement.setInt(2, transactionId);
                }, "Transaction Approved", "The transaction has been successfully approved.",
                "Approval Failed", "The transaction could not be approved or is not in a pending state.");
    }


    public void returnResource(int transactionId) throws SQLException {
        String query = "UPDATE transactions SET status = 'RETURNED', return_date = CURRENT_TIMESTAMP " +
                "WHERE id = ? AND status = 'APPROVED'";

        executeUpdate(query, ps -> ps.setInt(1, transactionId),
                "Return Successful", "The resource has been successfully returned.",
                "Return Failed", "The resource could not be returned or is not approved.");
    }


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

        }catch (MySQLConnectionException e) {
            throw new SQLException("Error fetching data: " + e.getMessage());
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FunctionalInterface
    interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }

}