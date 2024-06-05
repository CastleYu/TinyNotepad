package org.casyu.tinynotepad.query;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchController {

    @FXML
    private TextField searchField;
    @FXML
    private TextField replaceField;
    @FXML
    private Button searchButton;
    @FXML
    private Button replaceButton;

    private SearchManager searchManager;
    private String mode;

    public void setSearchManager(SearchManager searchManager) {
        this.searchManager = searchManager;
    }

    public void setMode(String mode) {
        this.mode = mode;
        if (mode.equals("查找")) {
            replaceField.setDisable(true);
            replaceButton.setDisable(true);
        } else if (mode.equals("替换")) {
            replaceField.setDisable(false);
            replaceButton.setDisable(false);
        }
    }

    @FXML
    public void initialize() {
        searchButton.setOnAction(event -> search());
        replaceButton.setOnAction(event -> replace());
        searchField.setOnKeyTyped(event -> search());
    }

    private void search() {
        String searchText = searchField.getText();
        searchManager.highlight(searchText);
    }

    private void replace() {
        String searchText = searchField.getText();
        String replaceText = replaceField.getText();
        searchManager.replace(searchText, replaceText);
    }
}
