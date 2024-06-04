package org.casyu.tinynotepad.file;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveManager {

    private boolean hasSaved;
    private File boundedFile;
    private final TextArea textArea;

    public SaveManager(TextArea textArea) {
        this.hasSaved = false;
        this.boundedFile = null;
        this.textArea = textArea;
    }

    public void createNewFile() {
        if (!hasSaved && !textArea.getText().isEmpty()) {
            askForSave();
        }
        boundedFile = null;
        textArea.clear();
        hasSaved = true;
    }

    public void openFile() {
        if (!hasSaved && !textArea.getText().isEmpty()) {
            askForSave();
        }
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            boundedFile = file;
            textArea.setText(readFile(file));
            hasSaved = true;
        }
    }

    public void closeFile() {
        if (!hasSaved && !textArea.getText().isEmpty()) {
            askForSave();
        }
        boundedFile = null;
        textArea.clear();
        hasSaved = true;
    }

    public void saveToFile() {
        if (boundedFile != null) {
            writeFile(boundedFile);
        } else {
            saveAs();
        }
    }

    public void saveAs() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            boundedFile = file;
            writeFile(file);
        }
    }

    public void save() {
        if (boundedFile == null) {
            saveAs();
        } else {
            writeFile(boundedFile);
        }
    }

    public void autoSave() throws IOException {
        if (boundedFile == null) {
            File tempFile = File.createTempFile("autosave", ".txt");
            writeFile(tempFile);
        } else {
            save();
        }
    }

    private void askForSave() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "你有未保存的内容，是否退出");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                save();
            }
        });
    }

    private String readFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter("\\Z");
            return scanner.next();
        } catch (IOException e) {
            showError("读取文件异常");
            return "";
        }
    }

    private void writeFile(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(textArea.getText());
            hasSaved = true;
        } catch (IOException e) {
            showError("写入文件异常");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR, message);
        alert.showAndWait();
    }
}