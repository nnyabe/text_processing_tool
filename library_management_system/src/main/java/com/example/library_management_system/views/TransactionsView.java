package com.example.library_management_system.views;


import com.example.library_management_system.modles.TransactionModel;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;


public class TransactionsView {


    @FXML

    private TableView<TransactionModel> transactionsTable;


    @FXML

    private TableColumn<TransactionModel, String> transactionIdColumn;


    @FXML

    private TableColumn<TransactionModel, String> typeColumn;


    @FXML

    private TableColumn<TransactionModel, String> bookIdColumn;


    @FXML

    private TableColumn<TransactionModel, String> userIdColumn;


    @FXML

    private TableColumn<TransactionModel, String> statusColumn;


    @FXML

    private TableColumn<TransactionModel, Button> actionColumn;


    @FXML

    private Button refreshButton;


    private ObservableList<TransactionModel> transactionList;


    @FXML

    public void initialize() {

        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        actionColumn.setCellValueFactory(new PropertyValueFactory<>("actionButton"));


        refreshButton.setOnAction(event -> loadTransactions());


        loadTransactions();

    }


    private void loadTransactions() {

        transactionList = FXCollections.observableArrayList();


    }
}