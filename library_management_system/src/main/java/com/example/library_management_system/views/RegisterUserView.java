package com.example.library_management_system.views;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The {@code RegisterUserView} class handles the registration functionality for new users (patrons).
 * It validates input fields, checks if the username or email is already taken, and registers a new patron
 * in the system if all validations pass.
 */
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

    /**
     * Initializes the registration view by setting up the event handler for the
     * "Create Account" button.
     */
    @FXML
    public void initialize() {
        createAccountButton.setOnAction(e -> handleRegister());
    }

    /**
     * Handles the registration process by validating the user inputs, checking if the
     * username or email is already taken, and registering the new patron if all checks pass.
     *
     * @throws RuntimeException if any database errors occur during validation or registration.
     */
    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // Basic validation for empty fields
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            errorMessageLabel.setText("All fields are required.");
            errorMessageLabel.setOpacity(1);
        }
        // Check if the email format is valid
        else if (!isEmailValid(email)) {
            errorMessageLabel.setText("Invalid email format.");
            errorMessageLabel.setOpacity(1);
        }
        // Check if the password is strong enough
        else if (!isPasswordStrong(password)) {
            errorMessageLabel.setText("Password is too weak.");
            errorMessageLabel.setOpacity(1);
        }
        // Check if the email or username is already taken
        else if (isEmailOrUsernameTaken(username, email)) {
            errorMessageLabel.setText("Username or email already taken.");
            errorMessageLabel.setOpacity(1);
        }
        // If all validations pass, register the new patron
        else {
            if (registerNewPatron(username, email, password)) {
                errorMessageLabel.setText("");
                UserSession.getInstance().setUsername(username);
                redirectToDashboard();
            } else {
                errorMessageLabel.setText("Registration failed. Please try again.");
                errorMessageLabel.setOpacity(1);
            }
        }
    }

    /**
     * Validates the email format using a regular expression.
     *
     * @param email the email address to be validated.
     * @return true if the email format is valid, false otherwise.
     */
    private boolean isEmailValid(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    /**
     * Validates the strength of the password. The password is considered strong if it
     * has at least 8 characters, contains both upper and lowercase letters, contains
     * at least one digit, and has at least one special character.
     *
     * @param password the password to be validated.
     * @return true if the password is strong, false otherwise.
     */
    private boolean isPasswordStrong(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*") && password.matches(".*\\d.*")
                && password.matches(".*[!@#$%^&*].*");
    }

    /**
     * Checks if the given username or email already exists in the database.
     *
     * @param username the username to be checked.
     * @param email the email to be checked.
     * @return true if the username or email is already taken, false otherwise.
     */
    private boolean isEmailOrUsernameTaken(String username, String email) {
        String query = "SELECT COUNT(*) FROM patrons WHERE username = ? OR email = ?";

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // If count is > 0, username/email is taken
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

    /**
     * Registers a new patron by inserting the given username and email into the database.
     *
     * @param username the username of the new patron.
     * @param email the email of the new patron.
     * @param password the password of the new patron.
     * @return true if the registration was successful, false otherwise.
     */
    private boolean registerNewPatron(String username, String email, String password) {
        String query = "INSERT INTO patrons (username, email) VALUES (?, ?)";

        try (Connection connection = DBConnection.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true; // Successfully registered the new patron
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Database error occurred.");
            errorMessageLabel.setOpacity(1);
        } catch (MySQLConnectionException e) {
            throw new RuntimeException(e);
        }

        return false; // Registration failed
    }

    /**
     * Redirects the user to the dashboard view after successful registration.
     *
     * @throws RuntimeException if the dashboard FXML file cannot be loaded.
     */
    private void redirectToDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/user-dashboard-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) createAccountButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("User Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            errorMessageLabel.setText("Failed to load the dashboard.");
            errorMessageLabel.setOpacity(1);
        }
    }
}
