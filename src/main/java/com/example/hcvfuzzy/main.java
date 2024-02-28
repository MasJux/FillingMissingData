package com.example.hcvfuzzy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    public static void main(String[] args) throws IOException {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("main-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 982, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}