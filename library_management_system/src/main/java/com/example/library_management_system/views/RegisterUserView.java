package com.example.library_management_system.views;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterUserView {
    @FXML
    private AnchorPane register_background;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button createAccountButton;

    @FXML
    public void initialize() {
        createAccountButton.setOnAction(e -> handleRegister());
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // Basic validation
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            errorMessageLabel.setText("All fields are required.");
            errorMessageLabel.setOpacity(1);
        } else if (!isEmailValid(email)) {
            errorMessageLabel.setText("Invalid email format.");
            errorMessageLabel.setOpacity(1);
        } else if (!isPasswordStrong(password)) {
            errorMessageLabel.setText("Password is too weak.");
            errorMessageLabel.setOpacity(1);
        } else if (isEmailOrUsernameTaken(username, email)) {
            errorMessageLabel.setText("Username or email already taken.");
            errorMessageLabel.setOpacity(1);
        } else {
            if (registerNewPatron(username, email, password)) {
                errorMessageLabel.setText("");
                redirectToDashboard();
            } else {
                errorMessageLabel.setText("Registration failed. Please try again.");
                errorMessageLabel.setOpacity(1);
            }
        }
    }

    private boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    private boolean isPasswordStrong(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*") && password.matches(".*\\d.*")
                && password.matches(".*[!@#$%^&*].*");
    }

    private boolean isEmailOrUsernameTaken(String username, String email) {
        String query = "SELECT COUNT(*) FROM patrons WHERE username = ? OR email = ?";

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (MySQLConnectionException e) {
            errorMessageLabel.setText("Database error occurred.");
            errorMessageLabel.setOpacity(1);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private boolean registerNewPatron(String username, String email, String password) {
        String query = "INSERT INTO patrons (username, email) VALUES (?, ?)";

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Database error occurred.");
            errorMessageLabel.setOpacity(1);
        } catch (MySQLConnectionException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    private void redirectToDashboard() {
        // Load the FXML file for the user dashboard
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/user-dashboard-view.fxml"));
            Parent root = loader.load();

            // Create a new scene using the loaded FXML
            Scene scene = new Scene(root);

            // Get the current window (stage) and set the new scene
            Stage stage = (Stage) createAccountButton.getScene().getWindow();
            stage.setScene(scene);

            // Optionally, set the title for the new scene
            stage.setTitle("User Dashboard");

            // Show the new scene
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Failed to load the dashboard.");
            errorMessageLabel.setOpacity(1);
        }
    }
}
