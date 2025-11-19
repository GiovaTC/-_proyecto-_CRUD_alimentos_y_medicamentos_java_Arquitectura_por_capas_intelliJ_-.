module com.example.crudalimentosmedicamentos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.crudalimentosmedicamentos to javafx.fxml;
    exports com.example.crudalimentosmedicamentos;
    exports com.example.crudalimentosmedicamentos.crud;
    opens com.example.crudalimentosmedicamentos.crud to javafx.fxml;
    exports com.example.crudalimentosmedicamentos.crud.util;
    opens com.example.crudalimentosmedicamentos.crud.util to javafx.fxml;
}