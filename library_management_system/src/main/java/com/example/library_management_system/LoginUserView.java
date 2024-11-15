package com.example.library.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginUserView {
    public AnchorPane login_background;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button loginButton;

    @FXML
    private void initialize(){
        loginButton.setOnAction(e -> handleLogin());
    }
    @FXML
    public void handleLogin( ) {

        String username = usernameField.getText();
        String password = passwordField.getText();
        if(password != null || username != null){
            loginButton.setDisable(false);
        }
        boolean isValid = true;
        /** Log in controller call with username and password to return bool */
        if(isValid){
            redirectToDashboard();
        }else {
            errorMessageLabel.setText("Wrong username or password!");
        }
    }

    private void redirectToDashboard() {
        // Add code to load and display the dashboard
        System.out.println("Redirecting to Dashboard...");

        // Close current window
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }
}
