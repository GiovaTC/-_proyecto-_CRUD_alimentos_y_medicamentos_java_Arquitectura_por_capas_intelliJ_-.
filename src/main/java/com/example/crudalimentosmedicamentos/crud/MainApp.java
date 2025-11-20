package com.example.crudalimentosmedicamentos.crud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Ruta del archivo FXML
        URL fxmlLocation = getClass().getResource("/com/example/crudalimentosmedicamentos/crud/main.fxml");

        // Validación de archivo FXML
        if (fxmlLocation == null) {
            throw new RuntimeException("❌ ERROR: No se encontró el archivo FXML en la ruta: "
                    + "/com/example/crudalimentosmedicamentos/crud/main.fxml");
        }

        System.out.println("✔ Ruta FXML encontrada correctamente: " + fxmlLocation);

        // Cargar FXML
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Parent root = fxmlLoader.load();

        // Crear escena
        Scene scene = new Scene(root, 900, 600);

        // Configurar ventana
        stage.setTitle("Gestión de Alimentos y Medicamentos");
        stage.setScene(scene);
        stage.setResizable(false); // Opcional: evita que se distorsione la UI
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
