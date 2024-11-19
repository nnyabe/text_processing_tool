package com.example.library_management_system.views;

import com.example.library_management_system.controllers.TransactionController;
import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.modles.Enums;
import com.example.library_management_system.modles.TransactionModel;
import com.example.library_management_system.modles.UserSession;
import com.example.library_management_system.utils.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ApproveResourceView {

    @FXML
    private VBox container;

    @FXML
    private TableView<TransactionModel> transactionsTable;

    @FXML
    private TableColumn<TransactionModel, String> requesterColumn;

    @FXML
    private TableColumn<TransactionModel, String> orderDateColumn;

    @FXML
    private TableColumn<TransactionModel, String> approvedDateColumn;

    @FXML
    private TableColumn<TransactionModel, String> statusColumn;

    @FXML
    private TableColumn<TransactionModel, String> actionColumn;

    @FXML
    private Label statusLabel;

    @FXML
    private Button refreshButton;

    private final ObservableList<TransactionModel> transactionList = FXCollections.observableArrayList();


    @FXML
    public void initialize() throws SQLException {
        refreshButton.setOnAction(e -> {
            try {
                handleRefresh();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        orderDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOrderDate().toString()));
        approvedDateColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getApprovedDate() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                return new SimpleStringProperty(cellData.getValue().getApprovedDate().toString());
            } else {
                return new SimpleStringProperty("Not Approved");
            }
        });
        requesterColumn.setCellValueFactory(new PropertyValueFactory<>("orderedBy"));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));

        // Set up action buttons in the action column
        actionColumn.setCellFactory(param -> new TableCell<TransactionModel, String>() {
            private final Button approveButton = new Button("Approve");
            private final Button returnButton = new Button("Return");
            {
                approveButton.setOnAction(event -> {
                    try {
                        handleApproveRequest(getTableRow().getItem());
                    } catch (SQLException | MySQLConnectionException e) {
                        throw new RuntimeException(e);
                    }
                });

            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    TransactionModel transaction = getTableRow().getItem();
                    if ("PENDING".equals(transaction.getStatus())) {
                        setGraphic(approveButton);
                    }
                }
            }
        });

        List<TransactionModel> transaction= new TransactionController().getAll();
        transactionList.clear();
        transactionList.addAll(transaction);
        transactionsTable.setItems(transactionList);


    }

    private void handleApproveRequest(TransactionModel transaction) throws MySQLConnectionException, SQLException {

            TransactionController transact = new TransactionController();
            String librarian = UserSession.getInstance().getUsername();
            String email = null;
            String query = "SELECT email FROM admins WHERE username = ?";
            try(Connection connection = DBConnection.createConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, librarian);

                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    email = resultSet.getString("email");
                }
                transact.approveTransaction(transaction.getId(), email);
                statusLabel.setText("Request Approved");
            }


    }

    @FXML
    public void handleRefresh() throws SQLException {
        // This simulates refreshing the table data (for now it clears and re-adds transactions)

        List<TransactionModel> transaction= new TransactionController().getAll();
        transactionList.clear();
        transactionList.addAll(transaction);

        transactionsTable.setItems(transactionList);
        transactionsTable.refresh();
        statusLabel.setText("Table Refreshed");
    }
}
