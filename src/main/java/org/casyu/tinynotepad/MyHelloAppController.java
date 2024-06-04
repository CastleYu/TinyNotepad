package org.casyu.tinynotepad;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

import java.io.File;

public class MyHelloAppController {

    @FXML
    private TextArea textArea;

    @FXML
    private MenuItem newItem;

    @FXML
    private MenuItem openItem;

    @FXML
    private MenuItem closeItem;

    @FXML
    private MenuItem saveItem;

    @FXML
    private MenuItem saveAsItem;

    @FXML
    private MenuItem settingsItem;

    @FXML
    private MenuItem exitItem;

    @FXML
    private MenuItem undoItem;

    @FXML
    private MenuItem redoItem;

    @FXML
    private MenuItem cutItem;

    @FXML
    private MenuItem copyItem;

    @FXML
    private MenuItem pasteItem;

    @FXML
    private MenuItem deleteItem;

    @FXML
    private MenuItem selectAllItem;

    @FXML
    private MenuItem unselectAllItem;

    @FXML
    private MenuItem wrapTextItem;

    @FXML
    private MenuItem aboutItem;

    @FXML
    private MenuItem encodingUTF8;

    @FXML
    private MenuItem encodingGBK;

    @FXML
    private MenuItem encodingANSI;

    private File currentFile;
    // Add event handlers for menu items
    @FXML
    private void initialize() {
        newItem.setOnAction(e -> handleNew());
        openItem.setOnAction(e -> handleOpen());
        closeItem.setOnAction(e -> handleClose());
        saveItem.setOnAction(e -> handleSave());
        saveAsItem.setOnAction(e -> handleSaveAs());
        settingsItem.setOnAction(e -> handleSettings());
        exitItem.setOnAction(e -> handleExit());
        undoItem.setOnAction(e -> handleUndo());
        redoItem.setOnAction(e -> handleRedo());
        cutItem.setOnAction(e -> handleCut());
        copyItem.setOnAction(e -> handleCopy());
        pasteItem.setOnAction(e -> handlePaste());
        deleteItem.setOnAction(e -> handleDelete());
        selectAllItem.setOnAction(e -> handleSelectAll());
        unselectAllItem.setOnAction(e -> handleUnselectAll());
        wrapTextItem.setOnAction(e -> handleWrapText());
        aboutItem.setOnAction(e -> handleAbout());
        encodingUTF8.setOnAction(e -> handleEncoding("UTF-8"));
        encodingGBK.setOnAction(e -> handleEncoding("GBK"));
        encodingANSI.setOnAction(e -> handleEncoding("ANSI"));
    }

    private void handleNew() {
        textArea.clear();
    }

    private void handleOpen() {
        // Add logic to open a file
    }

    private void handleClose() {
        textArea.clear();
    }

    private void handleSave() {
        // Add logic to save the file
    }

    private void handleSaveAs() {
        // Add logic to save the file as
    }

    private void handleSettings() {
        // Add logic to open settings
    }

    private void handleExit() {
        System.exit(0);
    }

    private void handleUndo() {
        textArea.undo();
    }

    private void handleRedo() {
        textArea.redo();
    }

    private void handleCut() {
        textArea.cut();
    }

    private void handleCopy() {
        textArea.copy();
    }

    private void handlePaste() {
        textArea.paste();
    }

    private void handleDelete() {
        textArea.deleteText(textArea.getSelection());
    }

    private void handleSelectAll() {
        textArea.selectAll();
    }

    private void handleUnselectAll() {
        textArea.deselect();
    }

    private void handleWrapText() {
        textArea.setWrapText(!textArea.isWrapText());
    }

    private void handleAbout() {
        // Add logic to show about information
    }

    private void handleEncoding(String encoding) {
        // Add logic to handle text encoding
    }
}
