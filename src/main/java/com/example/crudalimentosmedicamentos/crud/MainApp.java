package com.example.crudalimentosmedicamentos.crud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/example/crudalimentosmedicamentos/crud/ui/views/main.fxml"));
        Scene scene = new Scene(fxml.load(), 900, 600);
        stage.setTitle("Gestión Alimentos y Medicamentos");
        // stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}