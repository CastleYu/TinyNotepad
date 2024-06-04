package org.casyu.tinynotepad;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import org.casyu.tinynotepad.file.SaveManager;
import org.casyu.tinynotepad.query.SearchManager;
import org.casyu.tinynotepad.settings.SettingsController;

public class NotepadController {

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
    private TextArea textArea;

    private final SettingsController settingsController;
    private final SearchManager searchManager;
    private SaveManager saveManager;

    public NotepadController() {
        // Initialize controllers
        settingsController = new SettingsController();
        searchManager = new SearchManager();
    }

    @FXML
    public void initialize() {
        // Bind menu items to their actions
        newItem.setOnAction(event -> createNewFile());
        openItem.setOnAction(event -> openFile());
        closeItem.setOnAction(event -> closeFile());
        saveItem.setOnAction(event -> saveFile());
        saveAsItem.setOnAction(event -> saveFileAs());
        settingsItem.setOnAction(event -> openSettings());
        exitItem.setOnAction(event -> exitApp());

        undoItem.setOnAction(event -> undo());
        redoItem.setOnAction(event -> redo());
        cutItem.setOnAction(event -> cut());
        copyItem.setOnAction(event -> copy());
        pasteItem.setOnAction(event -> paste());
        deleteItem.setOnAction(event -> delete());
        selectAllItem.setOnAction(event -> selectAll());
        unselectAllItem.setOnAction(event -> unselectAll());
        saveManager = new SaveManager(textArea);
    }

    private void createNewFile() {
        saveManager.createNewFile();
    }

    private void openFile() {
        saveManager.openFile();
    }

    private void closeFile() {
        saveManager.closeFile();
    }

    private void saveFile() {
        saveManager.save();
    }

    private void saveFileAs() {
        saveManager.saveAs();
    }

    // Open the settings window
    private void openSettings() {
        settingsController.openSettings();
    }

    // Exit the application
    private void exitApp() {
        // Implement exit logic
    }

    // Undo the last action
    private void undo() {
        textArea.undo();
    }

    // Redo the last undone action
    private void redo() {
        textArea.redo();
    }

    // Cut the selected text
    private void cut() {
        textArea.cut();
    }

    // Copy the selected text
    private void copy() {
        textArea.copy();
    }

    // Paste text from clipboard
    private void paste() {
        textArea.paste();
    }

    // Delete the selected text
    private void delete() {
        // Implement delete logic
    }

    // Select all text
    private void selectAll() {
        textArea.selectAll();
    }

    // Unselect all text
    private void unselectAll() {
        textArea.deselect();
    }
}
