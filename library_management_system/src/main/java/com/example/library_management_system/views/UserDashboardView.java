package com.example.library_management_system.views;

import com.example.library_management_system.modles.UserSession;
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
        reservationButton.setOnAction(e -> loadPage("reserve-book-user-view.fxml"));
        borrowButton.setOnAction(e -> loadPage("borrow-book-view.fxml"));
        logsButton.setOnAction(e -> loadPage("transactions-user-view.fxml"));
        logOutButton.setOnAction(e -> logOut());
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
        try{
            UserSession.getInstance().setUsername("null");
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("login-user-view.fxml"));
            Parent page = loader.load();

            flowPaneForContent.getChildren().clear();
            flowPaneForContent.getChildren().add(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
