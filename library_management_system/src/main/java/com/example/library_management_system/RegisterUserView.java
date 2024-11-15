package com.example.library.views;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterUserView {
    public AnchorPane register_background;
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
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        if (!isEmailValid(email)) {
            errorMessageLabel.setText("Invalid email format.");
            errorMessageLabel.setOpacity(1);
        } else if (!isPasswordStrong(password)) {
            errorMessageLabel.setText("Password is too weak.");
            errorMessageLabel.setOpacity(1);
        } else {
            errorMessageLabel.setText("");
            redirectToDashboard();
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

    private void redirectToDashboard() {
        // Add code to load and display the dashboard
        System.out.println("Redirecting to Dashboard...");

        // Close current window
        Stage stage = (Stage) createAccountButton.getScene().getWindow();
        stage.close();
    }
}
