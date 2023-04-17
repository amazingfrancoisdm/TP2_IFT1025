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

/**
 * Classe Vue du design MVC. Celle-ci extends Application et est responsable d'affiche les composants graphiques.
 * C'est essentiellement le GUI.
 */
public class Vue_ClientFX extends Application {

    private static Controleur controleur;
    private static TableView coursesList;
    private static SplitPane splitPane;
    private static SplitPane leftPane;
    private static VBox rightPane;
    private static VBox topLeft;
    private static HBox botLeft;
    private static GridPane grid;
    private static TextField prenomField;
    private static TextField nomField;
    private static TextField emailField;
    private static TextField matriculeField;

    /**
     * Méthode qui lanche la fenêtre avec tous les composants placés
     *
     * @param stage Fenêtre de l'application
     * @throws IOException Exception en cas d'erreur d'input/output comme il s'agit de l'ouverture d'une fenêtre.
     */
    @Override
    public void start(Stage stage) throws IOException {

        stage.setMinWidth(500);
        stage.setMinHeight(357);

        stage.setMaxWidth(1000);
        stage.setMaxHeight(714);

        createPanes();

        Scene scene = new Scene(splitPane, 700, 500);
        controleur = new Controleur(new Modele(), scene);

        //===================================== LEFT SIDE =======================================================
        createTable();

        Label leftTitle = new Label("Liste des cours");
        leftTitle.setFont(new Font(20));

        leftTitle.prefHeightProperty().bind(topLeft.heightProperty().multiply(0.2));

        topLeft.getChildren().addAll(leftTitle, coursesList);

        topLeft.setAlignment(Pos.CENTER);

        ObservableList<String> choix = FXCollections.observableArrayList("Automne", "Hiver", "Été");

        ComboBox cBox = new ComboBox<>(choix);

        cBox.getSelectionModel().selectFirst();

        Button loadBtn = new Button("Charger");

        loadBtn.setOnAction((action) -> {
            String session = (String) cBox.getValue();

            if(session.equals("Été"))
                session = "Ete";

            controleur.load(session);
        });

        botLeft.getChildren().addAll(cBox, loadBtn);

        botLeft.setSpacing(25);

        botLeft.setAlignment(Pos.CENTER);

        //===================================== RIGHT SIDE ======================================================
        Label rightTitle = new Label("Formulaire d'inscrition");
        rightTitle.setFont(new Font(20));

        rightTitle.prefHeightProperty().bind(rightPane.heightProperty().multiply(0.2));

        rightPane.setSpacing(25);

        createGrid();

        Button sendBtn = new Button("Envoyer");
        sendBtn.setOnAction((action) -> {

            controleur.send();
            System.out.println("envoyé");

        });

        rightPane.getChildren().addAll(rightTitle,grid, sendBtn);
        rightPane.setAlignment(Pos.TOP_CENTER);

        stage.setTitle("Inscription UdeM");
        stage.setScene(scene);
        stage.show();
    }

    //Méthode qui crée les différentes zones dans la scène.
    private void createPanes(){
        splitPane = new SplitPane();

        leftPane = new SplitPane();
        leftPane.setOrientation(Orientation.VERTICAL);
        rightPane = new VBox();

        splitPane.getItems().addAll(leftPane,rightPane);

        rightPane.minWidthProperty().bind(splitPane.widthProperty().multiply(0.5));
        rightPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.5));

        topLeft = new VBox();
        botLeft = new HBox();

        topLeft.minHeightProperty().bind(splitPane.heightProperty().multiply(0.8));
        topLeft.maxHeightProperty().bind(splitPane.heightProperty().multiply(0.8));

        leftPane.getItems().addAll(topLeft, botLeft);
    }

    //Méthode privée qui crée le TableView dans le côté gauche de l'application.
    private void createTable(){
        coursesList = new TableView<Course>();
        coursesList.setId("tableView");

        TableColumn codeColumn = new TableColumn<Course, String>("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("code"));

        TableColumn nameColumn = new TableColumn<Course, String>("Cours");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));

        coursesList.getColumns().add(codeColumn);
        coursesList.getColumns().add(nameColumn);

        coursesList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        coursesList.maxWidthProperty().bind(topLeft.widthProperty().multiply(0.85));
        coursesList.maxHeightProperty().bind(topLeft.heightProperty().multiply(0.90));
    }

    //Méthode privée qui crée le GridPane dans le côté droit de l'application.
    private void createGrid(){
        grid = new GridPane();

        Label prenomLabel = new Label("Prénom");
        prenomField = new TextField();
        prenomField.setId("prenom");

        Label nomLabel = new Label("Nom");
        nomField = new TextField();
        nomField.setId("nom");

        Label emailLabel = new Label("Email");
        emailField = new TextField();
        emailField.setId("email");

        Label matriculeLabel = new Label("Matricule");
        matriculeField = new TextField();
        matriculeField.setId("matricule");

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
    }

    /**
     * Méthode main qui appelle la méthode qui lance la fenêtre.
     *
     * @param args Arguments passés à la méthode.
     */
    public static void main(String[] args) {
        launch();
    }
}