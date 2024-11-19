package com.example.library_management_system.views;

import com.example.library_management_system.controllers.BookController;
import com.example.library_management_system.controllers.MagazineController;
import com.example.library_management_system.modles.BookModel;
import com.example.library_management_system.modles.MagazineModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class AddResourceView {

    @FXML private CheckBox availableStateCheckBox;
    @FXML private ComboBox<String> resourceTypeComboBox;
    @FXML private VBox bookForm;
    @FXML private VBox magazineForm;

    // Book form fields
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField publisherField;
    @FXML private TextField isbnField;
    @FXML private TextField editionField;
    @FXML private TextField copiesField;
    @FXML private TextField copiesLeftField;

    // Magazine form fields
    @FXML private TextField magazineTitleField;
    @FXML private TextField editorField;
    @FXML private TextField magazinePublisherField;
    @FXML private TextField issnField;
    @FXML private TextField volumeField;
    @FXML private TextField magazineCopiesField;
    @FXML private TextField magazineCopiesLeftField;

    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    /**
     * Initializes the view by setting visibility for resource type forms.
     */
    @FXML
    private void initialize() {
        // Toggle between book and magazine forms based on selection
        resourceTypeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if ("Book".equals(newValue)) {
                bookForm.setVisible(true);
                magazineForm.setVisible(false);
            } else if ("Magazine".equals(newValue)) {
                bookForm.setVisible(false);
                magazineForm.setVisible(true);
            }
        });
        saveButton.setOnAction(e -> {
            try {
                handleSaveButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        cancelButton.setOnAction(e -> handleCancelButton());
    }

    /**
     * Handles saving a new resource (book or magazine).
     */
    @FXML
    private void handleSaveButton() throws SQLException {
        // Validate resource type and form fields, then save resource
        if (resourceTypeComboBox.getValue() == null) {
            showError("Please select a resource type.");
            return;
        }

        if ("Book".equals(resourceTypeComboBox.getValue())) {
            if (isBookFormValid()) {
                BookModel book = new BookModel(availableStateCheckBox.isSelected(),
                        titleField.getText(), publisherField.getText(),
                        Integer.parseInt(copiesField.getText()),
                        Integer.parseInt(copiesLeftField.getText()),
                        authorField.getText(), isbnField.getText(),
                        Integer.parseInt(editionField.getText()));
                new BookController().createOne(book);
                showSuccess("Book saved successfully!");
            } else {
                showError("Please fill in all the fields for the book.");
            }
        } else if ("Magazine".equals(resourceTypeComboBox.getValue())) {
            if (isMagazineFormValid()) {
                MagazineModel magazine = new MagazineModel(availableStateCheckBox.isSelected(),
                        magazineTitleField.getText(), magazinePublisherField.getText(),
                        Integer.parseInt(magazineCopiesField.getText()),
                        Integer.parseInt(magazineCopiesLeftField.getText()),
                        editorField.getText(), issnField.getText(),
                        Integer.parseInt(volumeField.getText()));
                new MagazineController().createOne(magazine);
                showSuccess("Magazine saved successfully!");
            } else {
                showError("Please fill in all the fields for the magazine.");
            }
        }
    }

    /**
     * Handles resetting form fields to their default values.
     */
    @FXML
    private void handleCancelButton() {
        resetFields();
    }

    /**
     * Validates book form fields.
     */
    private boolean isBookFormValid() {
        return !titleField.getText().isEmpty() && !authorField.getText().isEmpty() &&
                !publisherField.getText().isEmpty() && !isbnField.getText().isEmpty() &&
                !editionField.getText().isEmpty() && !copiesField.getText().isEmpty() &&
                !copiesLeftField.getText().isEmpty();
    }

    /**
     * Validates magazine form fields.
     */
    private boolean isMagazineFormValid() {
        return !magazineTitleField.getText().isEmpty() && !editorField.getText().isEmpty() &&
                !magazinePublisherField.getText().isEmpty() && !issnField.getText().isEmpty() &&
                !volumeField.getText().isEmpty() && !magazineCopiesField.getText().isEmpty() &&
                !magazineCopiesLeftField.getText().isEmpty();
    }

    /**
     * Displays an error alert with a message.
     */
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays a success alert with a message.
     */
    private void showSuccess(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Resets all input fields and ComboBox to their initial states.
     */
    private void resetFields() {
        titleField.clear();
        authorField.clear();
        publisherField.clear();
        isbnField.clear();
        editionField.clear();
        copiesField.clear();
        copiesLeftField.clear();

        magazineTitleField.clear();
        editorField.clear();
        magazinePublisherField.clear();
        issnField.clear();
        volumeField.clear();
        magazineCopiesField.clear();
        magazineCopiesLeftField.clear();

        resourceTypeComboBox.getSelectionModel().clearSelection();
        bookForm.setVisible(false);
        magazineForm.setVisible(false);
    }
}
