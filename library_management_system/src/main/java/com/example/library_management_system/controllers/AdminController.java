package com.example.library_management_system.controllers;

import com.example.library_management_system.modles.AdminModel;
import com.example.library_management_system.modles.Enums;
import com.example.library_management_system.modles.PatronModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminController extends BaseModelController<AdminModel> {

    @Override
    protected AdminModel mapRowToModel(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        Enums.Roles role = Enums.Roles.valueOf(resultSet.getString("role"));
        Date createdAt = resultSet.getDate("created_at");
        Date updatedAt = resultSet.getDate("updated_at");
        return new AdminModel(id, username, email, createdAt, updatedAt, role, password);
    }

    @Override
    protected String getTableName(){
        return "admins";
    }

    @Override
    protected String getUpdateQuery(){
        return "UPDATE admins SET  username = ?, email = ?, role = ? , password = ?" +
                "WHERE id = ?";
    }

    @Override
    protected String getCreateQuery(){
        return "INSERT INTO patrons (username, email, role, password)" +
                " VALUES ( ?, ?, ?, ?)";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement preparedStatement, AdminModel admin)
            throws SQLException{
        preparedStatement.setString(1, admin.getUsername());
        preparedStatement.setString(2, admin.getEmail());
        preparedStatement.setString(3, admin.getRole().name());
        preparedStatement.setString(4, admin.getPassword());
        preparedStatement.setInt(5, admin.getId());
    }

    @Override
    protected void setCreateParameters(PreparedStatement preparedStatement, AdminModel admin)
            throws SQLException{
        preparedStatement.setString(1, admin.getUsername());
        preparedStatement.setString(2, admin.getEmail());
        preparedStatement.setString(3, admin.getRole().name());
        preparedStatement.setString(4, admin.getPassword());

    }

    @Override
    public boolean createOne(AdminModel admin) throws SQLException{
        return super.createOne(admin);
    }

    @Override
    public List<AdminModel> getAll()throws SQLException{
        return super.getAll();
    }

    @Override
    public AdminModel getById(int id) throws SQLException{
        return super.getById(id);
    }

    @Override
    public boolean deleteById(int id) throws SQLException{
        return super.deleteById(id);
    }

    @Override
    public boolean updateById(AdminModel admin) throws SQLException{
        return super.updateById(admin);
    }
}
