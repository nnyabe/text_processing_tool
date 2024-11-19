package com.example.library_management_system.views;

import com.example.library_management_system.HelloApplication;
import com.example.library_management_system.modles.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

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
            try {
                // Set the username to null (or handle this as required)
                UserSession.getInstance().setUsername("null");

                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("login-user-view.fxml"));
                Scene scene = new Scene(loader.load(), 480, 500);

                Stage currentStage = (Stage)flowPaneForContent.getScene().getWindow();
                Stage newStage = new Stage();
                currentStage.setFullScreen(true);
                newStage.setScene(scene);
                newStage.show();

                currentStage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

