package com.example.library_management_system.views;

import com.example.library_management_system.HelloApplication;
import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.utils.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUserView {

    @FXML
    private VBox login_background;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    private void initialize() {
        loginButton.setOnAction(e -> handleLogin());
        registerButton.setOnAction(e -> handleRegisterRedirect());

    }

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            displayErrorMessage("Username and password must not be empty!");
            return;
        }

        String role = authenticateUser(username, password);
        if (role == null) {
            displayErrorMessage("Wrong username or password!");
        } else {
            redirectToAppropriatePanel(role);
        }
    }


    private String authenticateUser(String username, String password) {
        // Query for admins
        String adminQuery = "SELECT role FROM admins WHERE username = ? AND password = ?";
        try (Connection connection = DBConnection.createConnection();
             PreparedStatement adminStmt = connection.prepareStatement(adminQuery)) {

            adminStmt.setString(1, username);
            adminStmt.setString(2, password);

            try (ResultSet adminResult = adminStmt.executeQuery()) {
                if (adminResult.next()) {
                    return adminResult.getString("role");
                }
            }

            // If not found in admins, check patrons
            String patronQuery = "SELECT 'PATRON' AS role FROM patrons WHERE username = ?";
            try (PreparedStatement patronStmt = connection.prepareStatement(patronQuery)) {
                patronStmt.setString(1, username);

                try (ResultSet patronResult = patronStmt.executeQuery()) {
                    if (patronResult.next()) {
                        return "PATRON";
                    }
                }
            }
        } catch (MySQLConnectionException | SQLException e) {
            throw new RuntimeException(e);
        }

        return null; // User not found in either table
    }


    private void redirectToAppropriatePanel(String role) {
        if ("PATRON".equalsIgnoreCase(role)) {
            redirectToUserDashboard();
        } else {
            redirectToAdminPanel();
        }
    }

    private void redirectToUserDashboard() {
        System.out.println("Redirecting to User Dashboard...");
        try {
            // Load the registration FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("dashboard-user-view.fxml"));
            Parent root = loader.load();

            // Create a new scene and set it to the stage
            Scene scene = new Scene(root);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);

            // Optionally, set the title
            stage.setTitle("Register User");

            // Show the new stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void redirectToAdminPanel() {
        System.out.println("Redirecting to Admin Panel...");
        // Implement logic to load and display the admin panel
        closeCurrentWindow();
    }

    private void closeCurrentWindow() {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    private void displayErrorMessage(String message) {
        errorMessageLabel.setText(message);
        errorMessageLabel.setOpacity(1.0);
    }

    @FXML
    private void handleRegisterRedirect() {
        try {
            // Load the registration FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("register-user-view.fxml"));
            Parent root = loader.load();

            // Create a new scene and set it to the stage
            Scene scene = new Scene(root);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);

            // Optionally, set the title
            stage.setTitle("Register User");

            // Show the new stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}