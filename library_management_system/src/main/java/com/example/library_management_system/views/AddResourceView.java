package com.example.library_management_system.views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddResourceView {

    @FXML private ComboBox<String> resourceTypeComboBox;  // ComboBox to choose between Book or Magazine
    @FXML private VBox bookForm;  // VBox for Book form fields
    @FXML private VBox magazineForm;  // VBox for Magazine form fields

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

    @FXML private Button saveButton;  // Save button
    @FXML private Button cancelButton;  // Cancel button

    @FXML
    private void initialize() {
        // Set the initial visibility for forms based on ComboBox selection
        resourceTypeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if ("Book".equals(newValue)) {
                bookForm.setVisible(true);
                magazineForm.setVisible(false);
            } else if ("Magazine".equals(newValue)) {
                bookForm.setVisible(false);
                magazineForm.setVisible(true);
            }
        });
        saveButton.setOnAction(e ->handleSaveButton());
        cancelButton.setOnAction(e ->handleCancelButton());
    }

    @FXML
    private void handleSaveButton() {
        // Check if a resource type is selected
        if (resourceTypeComboBox.getValue() == null) {
            showError("Please select a resource type.");
            return;
        }

        // Check for missing input fields based on the selected resource type
        if ("Book".equals(resourceTypeComboBox.getValue())) {
            if (isBookFormValid()) {
                // Process saving the book (e.g., save to database)
                showSuccess("Book saved successfully!");
            } else {
                showError("Please fill in all the fields for the book.");
            }
        } else if ("Magazine".equals(resourceTypeComboBox.getValue())) {
            if (isMagazineFormValid()) {
                // Process saving the magazine (e.g., save to database)
                showSuccess("Magazine saved successfully!");
            } else {
                showError("Please fill in all the fields for the magazine.");
            }
        }
    }

    @FXML
    private void handleCancelButton() {
        // Reset all fields to their default values
        resetFields();
    }

    // Helper method to check if all book fields are filled
    private boolean isBookFormValid() {
        return !titleField.getText().isEmpty() && !authorField.getText().isEmpty() &&
                !publisherField.getText().isEmpty() && !isbnField.getText().isEmpty() &&
                !editionField.getText().isEmpty() && !copiesField.getText().isEmpty() &&
                !copiesLeftField.getText().isEmpty();
    }

    // Helper method to check if all magazine fields are filled
    private boolean isMagazineFormValid() {
        return !magazineTitleField.getText().isEmpty() && !editorField.getText().isEmpty() &&
                !magazinePublisherField.getText().isEmpty() && !issnField.getText().isEmpty() &&
                !volumeField.getText().isEmpty() && !magazineCopiesField.getText().isEmpty() &&
                !magazineCopiesLeftField.getText().isEmpty();
    }

    // Show error message in an alert
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Show success message in an alert
    private void showSuccess(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Reset all fields to their default values
    private void resetFields() {
        // Clear all text fields
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

        // Clear ComboBox selection
        resourceTypeComboBox.getSelectionModel().clearSelection();

        // Hide both forms to reset the visibility state
        bookForm.setVisible(false);
        magazineForm.setVisible(false);
    }
}
