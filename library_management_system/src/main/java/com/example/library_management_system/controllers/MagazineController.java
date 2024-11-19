package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.MagazineModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller class for managing magazines in the library system.
 * This class handles CRUD operations for magazine records in the database.
 */
public class MagazineController extends BaseModelController<MagazineModel> {

    /**
     * Maps a ResultSet row to a MagazineModel object.
     *
     * @param resultSet the ResultSet containing the data to be mapped
     * @return a MagazineModel object representing the row
     * @throws SQLException if there is an error accessing the ResultSet
     */
    @Override
    protected MagazineModel mapRowToModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String editor = resultSet.getString("editor");
        String issn = resultSet.getString("issn");
        boolean availabilityStatus = resultSet.getBoolean("available_state");
        String publisher = resultSet.getString("publisher");
        int totalCopies = resultSet.getInt("total_copies");
        int copiesLeft = resultSet.getInt("copies_left");
        int volume = resultSet.getInt("volume");
        Date createdAt = resultSet.getDate("created_at");
        Date updatedAt = resultSet.getDate("updated_at");

        return new MagazineModel(id, availabilityStatus, title, publisher, totalCopies,
                copiesLeft, editor, issn, volume, createdAt, updatedAt);
    }

    /**
     * Returns the name of the table used for magazine records in the database.
     *
     * @return the name of the table ("magazines")
     */
    @Override
    protected String getTableName() {
        return "magazines";
    }

    /**
     * Returns the SQL query for updating a magazine record.
     *
     * @return the SQL update query string
     */
    @Override
    protected String getUpdateQuery() {
        return "UPDATE magazines SET available_state = ?, title = ?, publisher = ?, total_copies = ? " +
                "copies_left = ?, editor = ? , issn = ? , volume = ? " +
                "WHERE id = ?";
    }

    /**
     * Sets the parameters for the update query in the PreparedStatement.
     *
     * @param preparedStatement the PreparedStatement to set parameters on
     * @param magazine the MagazineModel object containing the updated data
     * @throws SQLException if there is an error setting the parameters
     */
    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, MagazineModel magazine)
            throws SQLException {
        preparedStatement.setBoolean(1, magazine.isAvailableState());
        preparedStatement.setString(2, magazine.getTitle());
        preparedStatement.setString(3, magazine.getPublisher());
        preparedStatement.setInt(4, magazine.getTotalCopies());
        preparedStatement.setInt(5, magazine.getCopiesLeft());
        preparedStatement.setString(6, magazine.getEditor());
        preparedStatement.setString(7, magazine.getIssn());
        preparedStatement.setInt(8, magazine.getVolume());
        preparedStatement.setInt(9, magazine.getId());
    }

    /**
     * Returns the SQL query for creating a new magazine record.
     *
     * @return the SQL insert query string
     */
    @Override
    protected String getCreateQuery() {
        return "INSERT INTO books (available_state, title, publisher, total_copies, copies_left, editor, issn, volume)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    /**
     * Sets the parameters for the create query in the PreparedStatement.
     *
     * @param preparedStatement the PreparedStatement to set parameters on
     * @param magazine the MagazineModel object containing the data to be inserted
     * @throws SQLException if there is an error setting the parameters
     */
    @Override
    protected void setCreateParameters(PreparedStatement preparedStatement, MagazineModel magazine)
            throws SQLException {
        preparedStatement.setBoolean(1, magazine.isAvailableState());
        preparedStatement.setString(2, magazine.getTitle());
        preparedStatement.setString(3, magazine.getPublisher());
        preparedStatement.setInt(4, magazine.getTotalCopies());
        preparedStatement.setInt(5, magazine.getCopiesLeft());
        preparedStatement.setString(6, magazine.getEditor());
        preparedStatement.setString(7, magazine.getIssn());
        preparedStatement.setInt(8, magazine.getVolume());
    }

    /**
     * Retrieves all magazines from the database.
     *
     * @return a list of all MagazineModel objects
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public List<MagazineModel> getAll() throws SQLException {
        return super.getAll();
    }

    /**
     * Retrieves a magazine by its ID from the database.
     *
     * @param id the ID of the magazine to retrieve
     * @return the MagazineModel object corresponding to the ID, or null if not found
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public MagazineModel getById(int id) throws SQLException {
        return super.getById(id);
    }

    /**
     * Updates a magazine in the database by its ID.
     *
     * @param magazine the MagazineModel object containing the updated data
     * @return true if the magazine was successfully updated, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean updateById(MagazineModel magazine) throws SQLException {
        return super.updateById(magazine);
    }

    /**
     * Creates a new magazine in the database.
     *
     * @param magazine the MagazineModel object containing the data to be inserted
     * @return true if the magazine was successfully created, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean createOne(MagazineModel magazine) throws SQLException {
        return super.createOne(magazine);
    }

    /**
     * Deletes a magazine by its ID from the database.
     *
     * @param id the ID of the magazine to delete
     * @return true if the magazine was successfully deleted, false otherwise
     * @throws SQLException if there is an error accessing the database
     */
    @Override
    public boolean deleteById(int id) throws SQLException {
        return super.deleteById(id);
    }
}
