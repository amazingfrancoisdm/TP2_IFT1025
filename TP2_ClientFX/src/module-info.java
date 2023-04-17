/**
 * Configuration des prérequis JavaFX et des permissions
 */

module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens main to javafx.fxml;
    opens main.models to javafx.fxml;

    exports main;
    exports main.models;
}