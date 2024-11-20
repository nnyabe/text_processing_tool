package com.example.library_management_system.views;

import com.example.library_management_system.modles.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import com.example.library_management_system.HelloApplication;
import javafx.stage.Stage;

/**
 * The {@code UserDashboardView} class represents the user dashboard where users can navigate through various sections
 * of the library system such as books, magazines, reservations, borrowed items, and transaction logs.
 * It also handles user logout functionality.
 */
public class UserDashboardView {

    @FXML
    private Button booksButton, magazinesButton, reservationButton,logsButton, borrowButton, logOutButton;

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
            System.err.println(e.getMessage());
        }
    }

    /**
     * Logs the user out by clearing the session and redirecting to the login page.
     */
    private void logOut() {
        try {
            // Set the username to null (or handle this as required)
            UserSession.getInstance().setUsername("null");

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("login-user-view.fxml"));
            Scene scene = new Scene(loader.load(), 480, 500);

            Stage currentStage = (Stage) flowPaneForContent.getScene().getWindow();
            Stage newStage = new Stage();
            currentStage.setFullScreen(true);
            newStage.setScene(scene);
            newStage.show();

            currentStage.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
