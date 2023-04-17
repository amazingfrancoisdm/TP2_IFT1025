package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.models.*;

import java.io.IOException;

public class Vue_ClientFX extends Application {

    private static Controleur controleur;

    @Override
    public void start(Stage stage) throws IOException {

        stage.setMinWidth(500);
        stage.setMinHeight(357);

        stage.setMaxWidth(1000);
        stage.setMaxHeight(714);

        SplitPane splitPane = new SplitPane();

        SplitPane leftPane = new SplitPane();
        leftPane.setOrientation(Orientation.VERTICAL);
        VBox rightPane = new VBox();

        splitPane.getItems().addAll(leftPane,rightPane);

        Scene scene = new Scene(splitPane, 700, 500);
        controleur = new Controleur(new Modele(), scene);

        rightPane.minWidthProperty().bind(splitPane.widthProperty().multiply(0.5));
        rightPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.5));

        VBox topLeft = new VBox();
        HBox botLeft = new HBox();

        topLeft.minHeightProperty().bind(splitPane.heightProperty().multiply(0.8));
        topLeft.maxHeightProperty().bind(splitPane.heightProperty().multiply(0.8));

        leftPane.getItems().addAll(topLeft, botLeft);

        TableView coursesList = new TableView<Course>();

        TableColumn codeColumn = new TableColumn<Course, String>("Code");
        codeColumn.setCellFactory(new PropertyValueFactory<Course, String>("code"));

        TableColumn nameColumn = new TableColumn<Course, String>("Cours");
        nameColumn.setCellFactory(new PropertyValueFactory<Course, String>("name"));

        coursesList.getColumns().add(codeColumn);
        coursesList.getColumns().add(nameColumn);

        coursesList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Label leftTitle = new Label("Liste des cours");
        Font font = new Font(20);
        leftTitle.setFont(font);

        leftTitle.prefHeightProperty().bind(topLeft.heightProperty().multiply(0.2));

        coursesList.maxWidthProperty().bind(topLeft.widthProperty().multiply(0.85));
        coursesList.maxHeightProperty().bind(topLeft.heightProperty().multiply(0.90));

        topLeft.getChildren().addAll(leftTitle, coursesList);

        topLeft.setAlignment(Pos.CENTER);

        ObservableList<String> choix = FXCollections.observableArrayList("Automne", "Hiver", "Été");

        ComboBox cBox = new ComboBox<>(choix);

        cBox.getSelectionModel().selectFirst();

        Button loadBtn = new Button("Charger");

        loadBtn.setOnAction((action) -> {
            String session = (String) cBox.getValue();
            controleur.load(session);
        });

        botLeft.getChildren().addAll(cBox, loadBtn);

        botLeft.setSpacing(25);

        botLeft.setAlignment(Pos.CENTER);

        Label rightTitle = new Label("Formulaire d'inscrition");
        rightTitle.setFont(font);

        rightTitle.prefHeightProperty().bind(rightPane.heightProperty().multiply(0.2));

        rightPane.setSpacing(25);

        GridPane grid = new GridPane();

        Label prenomLabel = new Label("Prénom");
        TextField prenomField = new TextField();

        Label nomLabel = new Label("Nom");
        TextField nomField = new TextField();

        Label emailLabel = new Label("Email");
        TextField emailField = new TextField();

        Label matriculeLabel = new Label("Matricule");
        TextField matriculeField = new TextField();

        grid.add(prenomLabel,0,0,1,1);
        grid.add(prenomField, 1,0,1,1);
        grid.add(nomLabel, 0,1,1,1);
        grid.add(nomField, 1,1,1,1);
        grid.add(emailLabel,0, 2,1,1);
        grid.add(emailField,1,2,1,1);
        grid.add(matriculeLabel,0,3,1,1);
        grid.add(matriculeField,1,3,1,1);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Button sendBtn = new Button("Envoyer");
        sendBtn.setOnAction((action) -> {
            System.out.println("ALLo");
        });

        rightPane.getChildren().addAll(rightTitle,grid, sendBtn);
        rightPane.setAlignment(Pos.TOP_CENTER);

        stage.setTitle("Inscription UdeM");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}