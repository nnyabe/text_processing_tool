module com.example.library_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires java.desktop;

    opens com.example.library_management_system to javafx.fxml;
    exports com.example.library_management_system;
    exports com.example.library_management_system.views;
    opens com.example.library_management_system.views to javafx.fxml;
    opens com.example.library_management_system.modles to javafx.base;
}