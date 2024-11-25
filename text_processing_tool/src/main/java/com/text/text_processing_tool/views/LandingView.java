package com.text.text_processing_tool.views;

import com.text.text_processing_tool.service.FileService;
import com.text.text_processing_tool.service.TextService;
import com.text.text_processing_tool.utils.AlertManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.util.List;

public class LandingView {
    @FXML
    private ComboBox replaceDropdown;

    @FXML
    private TextField regexInput;
    @FXML
    private TextField textSearch;
    @FXML
    private TextField textReplace;
    @FXML
    private TextField textFind;
    @FXML
    private TextArea processedTextArea;
    @FXML
    private TextArea inputText;

    @FXML
    private Label wordCount;

    @FXML
    private Button regexInputButton;
    @FXML
    private Button textSearchButton;
    @FXML
    private Button textReplaceButton;
    @FXML
    private Button analyzeButton;
    @FXML
    private Button importButton;

    private final FileService fileService = new FileService();
    private final TextService textService = new TextService();
    private final AlertManager alert = new AlertManager();

    @FXML
    public void initialize(){
        regexInputButton.setOnAction(e -> handleRegexInput());
        textSearchButton.setOnAction(e -> handleTextSearch());
        textReplaceButton.setOnAction(e -> handleTextReplace());
        analyzeButton.setOnAction(e-> handleAnalyze());
        importButton.setOnAction(e -> {
            try {
                handleFileImport();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        if(inputText.getText().isEmpty()){
            wordCount.setText(""+0);
        }
    }

    private void handleFileImport() throws FileNotFoundException {
        String importedText = fileService.importFile();
        if(importedText == null){
            alert.showWarning("Empty File", "Imported and empty file.",
                    "It appears you imported an empty file.");
        }
        inputText.setText(importedText);
    }

    private void handleAnalyze() {
        int counts = textService.countAll(inputText.getText());


        wordCount.setText("" + counts);
    }

    private void handleTextReplace() {
        if(textFind.getText().isEmpty() || textReplace.getText().isEmpty() ||  replaceDropdown.getValue() == null){
            alert.showError("Empty Fields", "Input values in empty fields", "Please make sure " +
                    "all the fields are filled");
            return;
        }
        if(replaceDropdown.getValue().toString().equals("Replace All")){
           String text =  textService.replaceAllText(inputText.getText(), textFind.getText(), textReplace.getText());
            processedTextArea.setText(text);
        }
        if(replaceDropdown.getValue().toString().equals("Replace First")){
            String text = textService.replaceText(inputText.getText(), textFind.getText(), textReplace.getText());
            processedTextArea.setText(text);
        }
    }

    private void handleTextSearch() {
        if (textSearch.getText().isEmpty()){
            alert.showAlert("Empty Field", "Fill the values", "Please make sure to have entered all fields");
            return;
        }

        List<String> matches = textService.findMatches(inputText.getText(), textSearch.getText());
        if(matches.isEmpty()){
            alert.showAlert("No match found", "Word doesn't have a match",
                    " The text filed seems not to have the word you typed, Please type the word very well");
        }else {
            alert.showAlert("Match Found", "Word found in the text",
                    "The text field had a match with the input word.");
        }

    }

    private void handleRegexInput() {
    }


}
