package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.PatronModel;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller class for managing patrons in the library system.
 * This class handles CRUD operations for patron records in the database.
 */
public class PatronController extends BaseModelController<PatronModel> {

    /**
     * Maps a ResultSet row to a PatronModel object.
     *
     * @param resultSet the ResultSet containing the data to be mapped
     * @return a PatronModel object representing the row
     * @throws SQLException if there is an error accessing the ResultSet
     */
    @Override
    protected PatronModel mapRowToModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        Date createdAt = resultSet.getDate("created_at");
        Date updatedAt = resultSet.getDate("updated_at");
        return new PatronModel(id, username, email, createdAt, updatedAt);
    }

    /**
     * Returns the name of the table used for patron records in the database.
     *
     * @return the name of the table ("patrons")
     */
    @Override
    protected String getTableName() {
        return "patrons";
    }

    /**
     * Returns the SQL query for updating a patron record.
     *
     * @return the SQL update query string
     */
    @Override
    protected String getUpdateQuery() {
        return "UPDATE patrons SET  username = ?, email = ?" +
                "WHERE id = ?";
    }

    /**
     * Sets the parameters for the update query in the PreparedStatement.
     *
     * @param preparedStatement the PreparedStatement to set parameters on
     * @param patron the PatronModel object containing the updated data
     * @throws SQLException if there is an error setting the parameters
     */
    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, PatronModel patron)
            throws SQLException {
        preparedStatement.setString(1, patron.getUsername());
        preparedStatement.setString(2, patron.getEmail());
        preparedStatement.setInt(3, patron.getId());
    }

    /**
     * Returns the SQL query for creating a new patron record.
     *
     * @return the SQL insert query string
     */
    @Override
    protected String getCreateQuery() {
        return "INSERT INTO patrons (username, email)" +
                " VALUES ( ?, ?)";
    }

    /**
     * Sets the parameters for the create query in the PreparedStatement.
     *
     * @param preparedStatement the PreparedStatement to set parameters on
     * @param patron the PatronModel object containing the data to be inserted
     * @throws SQLException if there is an error setting the parameters
     */
    @Override
    protected void setCreateParameters(PreparedStatement preparedStatement, PatronModel patron)
            throws SQLException {
        preparedStatement.setString(1, patron.getUsername());
        preparedStatement.setString(2, patron.getEmail());
    }

    /**
     * Creates a new patron in the database.
     *
     * @param patron the PatronModel object containing the data to be inserted
     * @return true if the patron was successfully created, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean createOne(PatronModel patron) throws SQLException {
        return super.createOne(patron);
    }

    /**
     * Retrieves all patrons from the database.
     *
     * @return a list of all PatronModel objects
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public List<PatronModel> getAll() throws SQLException {
        return super.getAll();
    }

    /**
     * Retrieves a patron by its ID from the database.
     *
     * @param id the ID of the patron to retrieve
     * @return the PatronModel object corresponding to the ID, or null if not found
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public PatronModel getById(int id) throws SQLException {
        return super.getById(id);
    }

    /**
     * Deletes a patron by its ID from the database.
     *
     * @param id the ID of the patron to delete
     * @return true if the patron was successfully deleted, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean deleteById(int id) throws SQLException {
        return super.deleteById(id);
    }

    /**
     * Updates a patron in the database by its ID.
     *
     * @param patron the PatronModel object containing the updated data
     * @return true if the patron was successfully updated, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean updateById(PatronModel patron) throws SQLException {
        return super.updateById(patron);
    }
}
