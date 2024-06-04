package org.casyu.tinynotepad.file;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SaveManager {

    private boolean hasSaved;
    private File boundedFile;
    private final TextArea textArea;


    private Charset fileEncoding;
    private final StringProperty encodingProperty;



    public SaveManager(TextArea textArea) {
        this.hasSaved = false;
        this.boundedFile = null;
        this.textArea = textArea;
        this.fileEncoding = StandardCharsets.UTF_8; // 默认编码
        this.encodingProperty = new SimpleStringProperty(this.fileEncoding.name());
    }

    public void createNewFile() {
        if (!hasSaved && !textArea.getText().isEmpty()) {
            askForSave();
        }
        setFileEncoding(StandardCharsets.UTF_8);
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
            String content = readFileWithEncodings(file);
            textArea.setText(content);
            hasSaved = true;
        }
    }

    public void closeFile() {
        if (!hasSaved && !textArea.getText().isEmpty()) {
            askForSave();
        }
        setFileEncoding(StandardCharsets.UTF_8);
        boundedFile = null;
        textArea.clear();
        hasSaved = true;
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

    public void askForSave() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "你有未保存的内容，是否保存？");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                save();
            }
        });
    }

    private String readFileWithEncodings(File file) {
        String content = readFile(file, StandardCharsets.UTF_8);
        if (content == null) {
            content = readFile(file, Charset.forName("GBK"));
            if (content == null) {
                content = readFile(file, Charset.forName("windows-1252")); // ANSI
                if (content != null) {
                    setFileEncoding(Charset.forName("windows-1252"));
                }
            } else {
                setFileEncoding(Charset.forName("GBK"));
            }
        } else {
            setFileEncoding(StandardCharsets.UTF_8);
        }
        return content != null ? content : "";
    }

    private String readFile(File file, Charset charset) {
        try (Scanner scanner = new Scanner(file, charset.name())) {
            scanner.useDelimiter("\\Z");
            return scanner.hasNext() ? scanner.next() : "";
        } catch (IOException e) {
            return null;
        }
    }

    private void writeFile(File file) {
        try (FileWriter writer = new FileWriter(file, fileEncoding)) {
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

    public void setFileEncoding(Charset fileEncoding) {
        this.fileEncoding = fileEncoding;
        this.encodingProperty.set(fileEncoding.name());
    }
    public Charset getFileEncoding() {
        return fileEncoding;
    }

    public StringProperty encodingProperty() {
        return this.encodingProperty;
    }
}
