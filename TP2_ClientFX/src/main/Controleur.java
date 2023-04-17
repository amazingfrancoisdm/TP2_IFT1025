package main;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.models.Course;

import java.util.ArrayList;

public class Controleur {

    private Modele modele;
    private Scene vue;

    public Controleur(Modele modele, Scene scene){
        this.modele = modele;
        this.vue = scene;
    }

    public void load(String session){
        ArrayList<Course> cours = this.modele.charger(session);

        TableView table = (TableView) vue.lookup("#tableView");

        table.getItems().clear();

        for (int i = 0; i < cours.size(); i++) {
            table.getItems().add(cours.get(i));
        }

    }

    public void send(){

        TextField prenomField = (TextField) vue.lookup("#prenom");
        String prenom = prenomField.getText();

        TextField nomField = (TextField) vue.lookup("#nom");
        String nom = nomField.getText();

        TextField emailField = (TextField) vue.lookup("#email");
        String email = emailField.getText();

        TextField matriculeField = (TextField) vue.lookup("#matricule");
        String matricule = matriculeField.getText();

        TableView table = (TableView) vue.lookup("#tableView");

        Course chosenCourse = (Course) table.getSelectionModel().getSelectedItem();

        String returnMsg = this.modele.isApplicationValid(prenom, nom, email, matricule, chosenCourse);

        Alert alert = new Alert(Alert.AlertType.NONE);

        if (returnMsg.contains("Félicitations")){
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
        } else{
            alert.setAlertType(Alert.AlertType.ERROR);
        }

        alert.setContentText(returnMsg);
        alert.showAndWait();


    }

}
