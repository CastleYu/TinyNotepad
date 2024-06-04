package org.casyu.tinynotepad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MyHelloApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader;
        VBox root;
        try {
            loader = new FXMLLoader(getClass().getResource("/MyHelloApp.fxml"));
            root = loader.load();
        } catch (Exception e) {
            System.out.println("error when load");
            return;
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MyHelloApp");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
