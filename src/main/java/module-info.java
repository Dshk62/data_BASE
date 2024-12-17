module com.example.basedata {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.basedata to javafx.fxml;
    exports com.example.basedata;
}