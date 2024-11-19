package com.example.library_management_system.views;

import com.example.library_management_system.controllers.MagazineController;
import com.example.library_management_system.modles.MagazineModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * The {@code LoadMagazinesView} class represents the view that displays a list of magazines in the system.
 * It binds magazine data to a {@code TableView} for visualization and handles the initialization of the table columns.
 */
public class LoadMagazinesView {

    @FXML
    private TableView<MagazineModel> magazineTableView;

    @FXML
    private TableColumn<MagazineModel, Boolean> availableStateColumn;

    @FXML
    private TableColumn<MagazineModel, String> titleColumn;

    @FXML
    private TableColumn<MagazineModel, String> publisherColumn;

    @FXML
    private TableColumn<MagazineModel, Integer> totalCopiesColumn;

    @FXML
    private TableColumn<MagazineModel, Integer> copiesLeftColumn;

    @FXML
    private TableColumn<MagazineModel, String> editorColumn;

    @FXML
    private TableColumn<MagazineModel, String> issnColumn;

    @FXML
    private TableColumn<MagazineModel, Integer> volumeColumn;

    /**
     * Initializes the view by setting up the table columns and loading magazine data.
     * This method is automatically called when the view is loaded by the FXMLLoader.
     */
    @FXML
    public void initialize() {
        setUpTableColumns();
        loadMagazineData();
    }

    /**
     * Sets up the table columns to bind data from {@code MagazineModel} to the respective columns in the table.
     * Each column is linked to a specific property of the {@code MagazineModel} using {@code PropertyValueFactory}.
     */
    private void setUpTableColumns() {
        availableStateColumn.setCellValueFactory(new PropertyValueFactory<>("availableState"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
        copiesLeftColumn.setCellValueFactory(new PropertyValueFactory<>("copiesLeft"));
        editorColumn.setCellValueFactory(new PropertyValueFactory<>("editor"));
        issnColumn.setCellValueFactory(new PropertyValueFactory<>("issn"));
        volumeColumn.setCellValueFactory(new PropertyValueFactory<>("volume"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    }

    /**
     * Loads the magazine data from the database using the {@code MagazineController} and populates the {@code TableView}.
     * This method retrieves a list of magazines, converts it to an {@code ObservableList}, and binds it to the table.
     */
    private void loadMagazineData() {
        try {
            List<MagazineModel> magazines = new MagazineController().getAll();
            ObservableList<MagazineModel> magazineList = FXCollections.observableArrayList();
            magazineList.addAll(magazines);
            magazineTableView.setItems(magazineList);
        } catch (SQLException e) {
            throw new RuntimeException("Error loading magazine data", e);
        }
    }
}
