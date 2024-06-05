package org.casyu.tinynotepad.settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {

    @FXML
    private TreeView<String> settingsTree;
    @FXML
    private StackPane settingsPane;
    private final Settings settings;
    public AppearanceSettings appearanceSettings;

    public SettingsController() {
        settings = Settings.getInstance();
    }

    @FXML
    public void initialize() {
        settingsTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue.getValue()) {
                    case "常规":
                        displayGeneralSettings();
                        break;
                    case "外观":
                        displayAppearanceSettings();
                        break;
                }
            }
        });
    }

    private void displayGeneralSettings() {
        CheckBox wrapTextCheckbox = new CheckBox("启用自动换行");
        boolean isWrap = appearanceSettings.checkWrap();
        wrapTextCheckbox.setSelected(isWrap);
        wrapTextCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            settings.setWrapText(newValue);
        });
        settingsPane.getChildren().setAll(wrapTextCheckbox);
    }

    private void displayAppearanceSettings() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER_LEFT);

        ComboBox<String> fontTypeCombo = new ComboBox<>();
        fontTypeCombo.getItems().addAll("Arial", "Consolas", "Verdana");
        fontTypeCombo.setValue(settings.getFontType());
        fontTypeCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
            settings.setFontType(newValue);
        });

        TextField fontSizeField = new TextField(String.valueOf(settings.getFontSize()));
        fontSizeField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                settings.setFontSize(Integer.parseInt(newValue));
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        });

        ColorPicker fontColorPicker = new ColorPicker(settings.getFontColor());
        fontColorPicker.setOnAction(event -> {
            settings.setFontColor(fontColorPicker.getValue());
        });

        layout.getChildren().addAll(new Label("字体:"), fontTypeCombo, new Label("字体大小:"), fontSizeField, new Label("字体颜色:"), fontColorPicker);
        settingsPane.getChildren().setAll(layout);
    }

    public void openSettings() {
        try {
            // 加载 settings.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings.fxml"));
            Parent root = loader.load();
            SettingsController settingsController = loader.getController();
            settingsController.appearanceSettings = this.appearanceSettings;
            // 创建新的 Stage 用于设置窗口
            Stage settingsStage = new Stage();
            settingsStage.setTitle("Settings");
            settingsStage.setScene(new Scene(root));
            settingsStage.setOnCloseRequest(event -> {
                closeSettings();
            });
            settingsStage.initModality(Modality.APPLICATION_MODAL); // 设置为模态窗口，阻塞其他窗口操作
            settingsStage.showAndWait(); // 显示窗口并等待其关闭
        } catch (IOException e) {
            e.printStackTrace(); // 处理加载 FXML 时的异常
        }
    }

    public void closeSettings() {
        applySettings();
        if (settingsPane != null) {
            Stage stage = (Stage) settingsPane.getScene().getWindow();
            stage.close();
        }
    }

    public void applySettings() {
        settings.saveSettings();
        appearanceSettings.applyAllSettings();
    }

}
