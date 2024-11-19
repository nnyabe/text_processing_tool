package com.example.library_management_system.views;

import com.example.library_management_system.modles.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import com.example.library_management_system.HelloApplication;

/**
 * The {@code UserDashboardView} class represents the user dashboard where users can navigate through various sections
 * of the library system such as books, magazines, reservations, borrowed items, and transaction logs.
 * It also handles user logout functionality.
 */
public class UserDashboardView {

    @FXML
    private Button booksButton, magazinesButton, reservationButton, borrowButton, logsButton, logOutButton;

    @FXML
    private FlowPane flowPaneForContent;

    @FXML
    private TableView<Tab> tableView;

    /**
     * Initializes the dashboard view by setting up event handlers for the buttons.
     */
    @FXML
    public void initialize() {
        booksButton.setOnAction(e -> loadPage("load-books-view.fxml"));
        magazinesButton.setOnAction(e -> loadPage("load-magazines-view.fxml"));
        reservationButton.setOnAction(e -> loadPage("reserve-book-user-view.fxml"));
        borrowButton.setOnAction(e -> loadPage("borrow-book-view.fxml"));
        logsButton.setOnAction(e -> loadPage("transactions-user-view.fxml"));
        logOutButton.setOnAction(e -> logOut());
    }

    /**
     * Loads the specified page into the dashboard content area.
     *
     * @param pageName The name of the FXML file to be loaded.
     */
    public void loadPage(String pageName) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(pageName));
            Parent page = loader.load();

            flowPaneForContent.getChildren().clear();
            flowPaneForContent.getChildren().add(page);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging purposes
        }
    }

    /**
     * Logs the user out by clearing the current session and loading the login page.
     */
    private void logOut() {
        try {
            UserSession.getInstance().setUsername("null");
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("login-user-view.fxml"));
            Parent page = loader.load();

            flowPaneForContent.getChildren().clear();
            flowPaneForContent.getChildren().add(page);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging purposes
        }
    }
}
