package com.example.library_management_system.views;

import com.example.library_management_system.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;

public class AdminDashboardView {


        @FXML
        private Button deleteBookButton, addBookButton,approveAllButton, booksButton, magazinesButton, reservationButton, borrowButton, logsButton, logOutButton;

        @FXML
        private FlowPane flowPaneForContent;

        @FXML
        private TableView<Tab> tableView;

        @FXML
        public void initialize() {

            booksButton.setOnAction(e -> loadPage("load-books-view.fxml"));
            magazinesButton.setOnAction(e -> loadPage("load-magazines-view.fxml"));
            logsButton.setOnAction(e -> loadPage("transactions-user-view.fxml"));
            addBookButton.setOnAction(e -> loadPage("add-book-view.fxml"));
            approveAllButton.setOnAction(e -> loadPage("approve-view.fxml"));
            deleteBookButton.setOnAction(e -> loadPage("delete-book-view.fxml"));
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

