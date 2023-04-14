package main;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Vue_ClientFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        SplitPane mainPane = new SplitPane();
        SplitPane pairedPane = new SplitPane();

        pairedPane.setOrientation(Orientation.VERTICAL);

        AnchorPane rightPane = new AnchorPane();
        AnchorPane topLeftPane = new AnchorPane();
        AnchorPane botLeftPane = new AnchorPane();

        pairedPane.getItems().addAll(topLeftPane, botLeftPane);
        mainPane.getItems().addAll(pairedPane, rightPane);

        Scene scene = new Scene(mainPane, 400, 400);
        stage.setTitle("Inscription UdeM");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}