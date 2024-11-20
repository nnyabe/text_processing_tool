package com.example.library_management_system.views;

import com.example.library_management_system.HelloApplication;
import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.modles.UserSession;
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

/**
 * The {@code LoginUserView} class handles the login functionality for the users of the library management system.
 * It allows users to log in using their username and password, and redirects them to the appropriate panel based on their role.
 */
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

    /**
     * Initializes the login view and sets the actions for the login and registration buttons.
     * This method is automatically called when the FXML file is loaded.
     */
    @FXML
    private void initialize() {
        loginButton.setOnAction(e -> handleLogin());
        registerButton.setOnAction(e -> handleRegisterRedirect());
    }

    /**
     * Handles the login process by validating user credentials.
     * It authenticates the user and redirects them to the appropriate dashboard based on their role.
     */
    @FXML
    public void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        // Check if username or password are empty
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            displayErrorMessage("Username and password must not be empty!");
            return;
        }

        // Authenticate the user
        String role = authenticateUser(username, password);
        if (role == null) {
            displayErrorMessage("Wrong username or password!");
        } else {
            // Set the user session and redirect to the appropriate panel
            UserSession.getInstance().setUsername(username);
            redirectToAppropriatePanel(role);
        }
    }

    /**
     * Authenticates the user by checking the credentials against both the admins and patrons tables.
     * If the user is found in the admins table, their role is returned; otherwise, it checks the patrons table.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return The role of the authenticated user ("ADMIN" or "PATRON"), or null if not found.
     */
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

    /**
     * Redirects the user to the appropriate panel based on their role.
     * If the user is a patron, they are redirected to the user dashboard.
     * If the user is an admin, they are redirected to the admin panel.
     *
     * @param role The role of the authenticated user ("PATRON" or "ADMIN").
     */
    private void redirectToAppropriatePanel(String role) {
        if ("PATRON".equalsIgnoreCase(role)) {
            redirectToUserDashboard();
        } else {
            redirectToAdminPanel();
        }
    }

    /**
     * Redirects the user to the user dashboard.
     * This method loads the user dashboard FXML and sets it as the current scene.
     */
    private void redirectToUserDashboard() {
        System.out.println("Redirecting to User Dashboard...");
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("dashboard-user-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("User Dashboard");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Redirects the user to the admin panel.
     * This method loads the admin panel FXML and sets it as the current scene.
     */
    private void redirectToAdminPanel() {
        System.out.println("Redirecting to Admin Panel...");
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("admin-dashboard-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Panel");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeCurrentWindow();
    }

    /**
     * Closes the current login window.
     * This method is called after the user has been successfully redirected to the appropriate panel.
     */
    private void closeCurrentWindow() {
        Stage stage = (Stage) loginButton.getScene().getWindow();
//        stage.close();
    }

    /**
     * Displays an error message on the screen.
     * This method is used to show validation or authentication errors.
     *
     * @param message The error message to be displayed.
     */
    private void displayErrorMessage(String message) {
        errorMessageLabel.setText(message);
        errorMessageLabel.setOpacity(1.0);
    }

    /**
     * Redirects the user to the registration view.
     * This method is called when the user clicks the "Register" button.
     */
    @FXML
    private void handleRegisterRedirect() {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("register-user-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Register User");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
