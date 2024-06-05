package org.casyu.tinynotepad.query;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class SearchManager {

    private final TextArea textArea;
    private int lastIndex;

    public SearchManager(TextArea textArea) {
        this.textArea = textArea;
        this.lastIndex = -1;
    }

    public void findOrReplace() {
        openSearchWindow();
    }

    private void openSearchWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            Parent root = loader.load();

            SearchController controller = loader.getController();
            controller.setSearchManager(this);
            controller.setMode("替换");

            Stage searchStage = new Stage();
            searchStage.setTitle("查找或替换");
            searchStage.setScene(new Scene(root));
            searchStage.showAndWait();
        } catch (IOException e) {
            System.err.println(e);
            showError("无法打开搜索窗口");
        }
    }

    public void highlight(String searchText) {
        String content = textArea.getText();
        lastIndex = content.indexOf(searchText, lastIndex + 1);
        if (lastIndex >= 0) {
            textArea.selectRange(lastIndex, lastIndex + searchText.length());
        } else {
            showError("找不到更多搜索内容");
            lastIndex = -1; // Reset for next search
        }
    }

    public void replace(String searchText, String replaceText) {
        String content = textArea.getText();
        int index = content.indexOf(searchText, lastIndex + 1);
        if (index >= 0) {
            textArea.setText(content.substring(0, index) + replaceText + content.substring(index + searchText.length()));
            textArea.selectRange(index, index + replaceText.length());
            lastIndex = index;
        } else {
            showError("找不到更多搜索内容");
            lastIndex = -1; // Reset for next search
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR, message);
        alert.showAndWait();
    }
}
