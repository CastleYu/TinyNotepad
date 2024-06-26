package org.casyu.tinynotepad;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import org.casyu.tinynotepad.file.SaveManager;
import org.casyu.tinynotepad.query.SearchManager;
import org.casyu.tinynotepad.settings.AppearanceSettings;
import org.casyu.tinynotepad.settings.SettingsController;

import java.util.Arrays;
import java.util.List;

public class NotepadController {

    @FXML
    public MenuItem findOrReplaceItem;
    @FXML
    public Label positionLabel;
    @FXML
    public Label encodingLabel;
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
    @FXML
    private CheckMenuItem wrapTextItem;
    public SearchManager searchManager;
    public final SettingsController settingsController;
    public SaveManager saveManager;

    public NotepadController() {
        settingsController = new SettingsController();
    }

    @FXML
    public void initialize() {
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
        wrapTextItem.selectedProperty().bindBidirectional(textArea.wrapTextProperty());
        textArea.setOnKeyReleased(this::updatePosition);
        textArea.setOnMouseClicked(this::updatePosition);
        updatePosition(null);

        searchManager = new SearchManager(textArea);
        saveManager = new SaveManager(textArea);
        settingsController.appearanceSettings = new AppearanceSettings(textArea);

        encodingLabel.textProperty().bind(saveManager.encodingProperty().concat(" 编码"));
        findOrReplaceItem.setOnAction(event -> searchManager.findOrReplace());
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

    private void openSettings() {
        settingsController.openSettings();
    }

    private void exitApp() {
        saveManager.askForSave();
        System.exit(0);
    }

    private void undo() {
        textArea.undo();
    }

    private void redo() {
        textArea.redo();
    }

    private void cut() {
        textArea.cut();
    }

    private void copy() {
        textArea.copy();
    }

    private void paste() {
        textArea.paste();
    }

    private void delete() {
        textArea.deleteText(textArea.getSelection());
    }

    private void selectAll() {
        textArea.selectAll();
    }

    private void unselectAll() {
        textArea.deselect();
    }

    private void updatePosition(Event event) {
        int caretPosition = textArea.getCaretPosition();

        String textUpToCaret = textArea.getText(0, caretPosition);
        int rowNum = textUpToCaret.split("\n", -1).length;

        int colNum;
        int lastNewLineIndex = textUpToCaret.lastIndexOf('\n');
        if (lastNewLineIndex == -1) {
            colNum = caretPosition + 1;
        } else {
            colNum = caretPosition - lastNewLineIndex;
        }

        positionLabel.setText("行: " + rowNum + ", 列: " + colNum);
    }
}
