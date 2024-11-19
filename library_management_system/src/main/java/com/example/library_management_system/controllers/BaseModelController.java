package com.example.library_management_system.controllers;

import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseModelController<T> {


    // Abstract method that subclasses should implement to convert a ResultSet row to a model object
    protected abstract T mapRowToModel(ResultSet resultSet) throws SQLException;

    // Get all records from the table
    public List<T> getAll() throws SQLException {
        List<T> results = new ArrayList<>();
        String query = "SELECT * FROM " + getTableName();  // The table name should be provided by subclass

        System.out.println("No problem at this point");
        try (Connection connection = DBConnection.createConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                T newEntity = mapRowToModel(result);
                results.add(newEntity);
            }
        } catch (MySQLConnectionException e) {
            throw new SQLException("Error fetching data: " + e.getMessage());
        }

        return results;
    }

    // Get record by ID
    public T getById(int id) throws SQLException {
        String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        T model = null;

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    model = mapRowToModel(result);
                }
            }
        } catch (MySQLConnectionException e) {
            throw new SQLException("Error fetching data by ID: " + e.getMessage());
        }

        return model;
    }

    // Delete record by ID
    public boolean deleteById(int id) throws SQLException {
        String query = "DELETE FROM " + getTableName() + " WHERE id = ?";
        int rowsAffected = 0;

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            rowsAffected = preparedStatement.executeUpdate();
        } catch (MySQLConnectionException e) {
            throw new SQLException("Error deleting record: " + e.getMessage());
        }

        return rowsAffected > 0;
    }

    // Update record by ID
    public boolean updateById(T model) throws SQLException {
        String query = getUpdateQuery();  // This should be implemented in the subclass
        int rowsAffected = 0;

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setUpdateParameters(preparedStatement, model);  // Set the parameters using the model
            rowsAffected = preparedStatement.executeUpdate();
        } catch (MySQLConnectionException e) {
            throw new SQLException("Error updating record: " + e.getMessage());
        }

        return rowsAffected > 0;
    }

    public boolean createOne(T model) throws SQLException{
        String query = getCreateQuery();
        int rowAffected;
        try(Connection connection = DBConnection.createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){


            setCreateParameters(preparedStatement, model);
          rowAffected = preparedStatement.executeUpdate();
        }catch(MySQLConnectionException e){
            throw new SQLException("Error Occured Creating new record: " + e.getMessage());
        }

        return rowAffected > 0;
    }

    // Method to get the table name (should be implemented in the subclass)
    protected abstract String getTableName();

    // Method to get the query for updating records (should be implemented in the subclass)
    protected abstract String getUpdateQuery();

    protected abstract String getCreateQuery();

    // Method to set update parameters for the PreparedStatement (should be implemented in the subclass)
    protected abstract void setUpdateParameters(PreparedStatement preparedStatement, T model) throws SQLException;

    protected abstract void setCreateParameters(PreparedStatement preparedStatement, T model)
        throws SQLException;
}
