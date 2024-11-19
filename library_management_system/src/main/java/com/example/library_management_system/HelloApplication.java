package com.example.library_management_system;

import com.example.library_management_system.utils.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DBConnection.runSQLInitialization("src/main/java/com/example/library_management_system/utils/initailize_database.sql");
        DBConnection.runSQLDataInputs("src/main/java/com/example/library_management_system/utils/adding_data.sql");


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-user-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}