module com.example.crudalimentosmedicamentos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    exports com.example.crudalimentosmedicamentos.crud;

    opens com.example.crudalimentosmedicamentos.crud to javafx.fxml;
    opens com.example.crudalimentosmedicamentos.crud.ui.controllers to javafx.fxml;
}

