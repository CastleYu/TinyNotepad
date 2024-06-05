package org.casyu.tinynotepad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class NotepadAPP extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        FXMLLoader loader;
        VBox root;
        NotepadController controller;
        try {
            loader = new FXMLLoader(getClass().getResource("NotepadMain.fxml"));
            root = loader.load();
            controller = loader.getController();
        } catch (Exception e) {
            System.out.println("error when load");
            System.err.println(e);
            return;
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e->controller.saveManager.closeFile());
        primaryStage.setTitle("记事本");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
