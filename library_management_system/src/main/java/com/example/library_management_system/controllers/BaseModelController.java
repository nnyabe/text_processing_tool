package com.example.library_management_system.controllers;

import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base controller class providing CRUD operations for model entities.
 * Subclasses should implement abstract methods to handle specific model logic.
 *
 * @param <T> the type of the model class this controller handles
 */
public abstract class BaseModelController<T> {

    /**
     * Abstract method that subclasses should implement to convert a ResultSet row to a model object.
     *
     * @param resultSet the ResultSet from which the model object will be created
     * @return the model object corresponding to the current row in the ResultSet
     * @throws SQLException if there is an error accessing the ResultSet
     */
    protected abstract T mapRowToModel(ResultSet resultSet) throws SQLException;

    /**
     * Retrieves all records from the corresponding table in the database.
     *
     * @return a list of all model objects from the table
     * @throws SQLException if there is an error accessing the database
     */
    public List<T> getAll() throws SQLException {
        List<T> results = new ArrayList<>();
        String query = "SELECT * FROM " + getTableName();

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

    /**
     * Retrieves a record by its ID from the corresponding table in the database.
     *
     * @param id the ID of the record to retrieve
     * @return the model object corresponding to the record, or null if not found
     * @throws SQLException if there is an error accessing the database
     */
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

    /**
     * Deletes a record by its ID from the corresponding table in the database.
     *
     * @param id the ID of the record to delete
     * @return true if the record was successfully deleted, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
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

    /**
     * Updates a record by its ID in the corresponding table in the database.
     *
     * @param model the model object containing the updated data
     * @return true if the record was successfully updated, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    public boolean updateById(T model) throws SQLException {
        String query = getUpdateQuery();
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

    /**
     * Creates a new record in the corresponding table in the database.
     *
     * @param model the model object containing the data to be inserted
     * @return true if the record was successfully created, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    public boolean createOne(T model) throws SQLException {
        String query = getCreateQuery();
        int rowAffected;
        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            setCreateParameters(preparedStatement, model);
            rowAffected = preparedStatement.executeUpdate();
        } catch (MySQLConnectionException e) {
            throw new SQLException("Error Occured Creating new record: " + e.getMessage());
        }

        return rowAffected > 0;
    }

    /**
     * Method to get the table name.
     * This should be implemented in the subclass to provide the appropriate table name.
     *
     * @return the name of the table
     */
    protected abstract String getTableName();

    /**
     * Method to get the SQL query for updating records.
     * This should be implemented in the subclass to provide the appropriate update query.
     *
     * @return the SQL update query string
     */
    protected abstract String getUpdateQuery();

    /**
     * Method to get the SQL query for creating new records.
     * This should be implemented in the subclass to provide the appropriate create query.
     *
     * @return the SQL create query string
     */
    protected abstract String getCreateQuery();

    /**
     * Method to set update parameters for the PreparedStatement.
     * This should be implemented in the subclass to set the appropriate parameters for updating a record.
     *
     * @param preparedStatement the PreparedStatement to set the parameters on
     * @param model the model object containing the data to set
     * @throws SQLException if there is an error setting the parameters
     */
    protected abstract void setUpdateParameters(PreparedStatement preparedStatement, T model)
            throws SQLException;

    /**
     * Method to set create parameters for the PreparedStatement.
     * This should be implemented in the subclass to set the appropriate parameters for creating a record.
     *
     * @param preparedStatement the PreparedStatement to set the parameters on
     * @param model the model object containing the data to set
     * @throws SQLException if there is an error setting the parameters
     */
    protected abstract void setCreateParameters(PreparedStatement preparedStatement, T model)
            throws SQLException;
}
