module main {
    requires javafx.controls;
    requires javafx.fxml;

    opens main to javafx.fxml;
    opens main.models to javafx.fxml;

    exports main;
    exports main.models;
}