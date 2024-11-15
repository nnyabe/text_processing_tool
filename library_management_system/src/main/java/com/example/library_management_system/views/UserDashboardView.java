package com.example.library_management_system.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;


import com.example.library_management_system.HelloApplication;

import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;


public class UserDashboardView {

    @FXML
    private Button booksButton, magazinesButton, reservationButton, borrowButton, logsButton, logOutButton;

    @FXML
    private FlowPane flowPaneForContent;

    @FXML
    private TableView<Tab> tableView;

    @FXML
    public void initialize() {

        booksButton.setOnAction(e -> loadPage("load-books-view.fxml"));
        magazinesButton.setOnAction(e -> loadPage("load-magazines-view.fxml"));
        reservationButton.setOnAction(e -> loadPage("load-reservations-view.fxml"));
        borrowButton.setOnAction(e -> loadPage("load-borrow-view.fxml"));
        logsButton.setOnAction(e -> loadPage("load-logs-view.fxml"));
        logOutButton.setOnAction(e -> loadPage("log-out.fxml"));
    }

    public void loadPage(String pageName) {
        try{
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(pageName));
            Parent page = loader.load();

            flowPaneForContent.getChildren().clear();
            flowPaneForContent.getChildren().add(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logOut() {
        // Handle log out
    }
}
