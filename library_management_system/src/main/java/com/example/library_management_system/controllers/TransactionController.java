package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.Enums;
import com.example.library_management_system.modles.TransactionModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
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
        return new TransactionModel(id, orderDate, approveDate, returnDate, status,approvedBy, orderedBy,resourceId , resourceType);
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
                "approved_by, ordered_by, resource_id, resource_type)" +
                " VALUES ( ?, ?, ?, ?, ?, ?,?)";
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

    public boolean approveTransaction(TransactionModel transaction, String adminEmail){

        TransactionController transact = new TransactionController();
//        transact.updateById(transaction)

        return true;
    }

}