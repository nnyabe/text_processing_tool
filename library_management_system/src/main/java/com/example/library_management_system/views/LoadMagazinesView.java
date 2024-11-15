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

    @FXML
    public void initialize() {
        // Set up the columns using the PropertyValueFactory
        availableStateColumn.setCellValueFactory(new PropertyValueFactory<>("availableState"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        totalCopiesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCopies"));
        copiesLeftColumn.setCellValueFactory(new PropertyValueFactory<>("copiesLeft"));
        editorColumn.setCellValueFactory(new PropertyValueFactory<>("editor"));
        issnColumn.setCellValueFactory(new PropertyValueFactory<>("issn"));
        volumeColumn.setCellValueFactory(new PropertyValueFactory<>("volume"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        loadMagazineData();
    }

    private void loadMagazineData() {
        // Sample data (replace with database query or other source)
        try {
            List<MagazineModel> magazines = new MagazineController().getAll();
            ObservableList<MagazineModel> magazineList = FXCollections.observableArrayList();

            magazineList.addAll(magazines);
            magazineTableView.setItems(magazineList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
