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

    private TextArea textArea;

    public SearchManager(TextArea textArea) {
        this.textArea = textArea;
    }

    public void findOrReplace() {
        openSearchWindow();
    }

    private void openSearchWindow() {
        try {
            System.out.println("Start");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            System.out.println("Load");
            Parent root = loader.load();

            System.out.println("LoadCtrl");
            SearchController controller = loader.getController();
            controller.setSearchManager(this);
            controller.setMode("查找或替换");

            Stage searchStage = new Stage();
            searchStage.setTitle("查找或替换");
            searchStage.setScene(new Scene(root));
            searchStage.initModality(Modality.APPLICATION_MODAL);
            searchStage.showAndWait();
        } catch (IOException e) {
            System.err.println(e);
            showError("无法打开搜索窗口");
        }
    }

    public void highlight(String searchText) {
        // 高亮实现
        String content = textArea.getText();
        int index = content.indexOf(searchText);
        if (index >= 0) {
            textArea.selectRange(index, index + searchText.length());
        } else {
            showError("找不到搜索内容");
        }
    }

    public void replace(String searchText, String replaceText) {
        // 替换实现
        String content = textArea.getText();
        content = content.replace(searchText, replaceText);
        textArea.setText(content);
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR, message);
        alert.showAndWait();
    }
}
