package main;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.models.Course;

import java.util.ArrayList;

/**
 * Classe qui fait l'intermédiaire entre la classe Modele et l'interface graphique.
 * Elle gère les appels aux méthodes de la classe Modele et s'occupe aussi des changements de l'interface graphique.
 */
public class Controleur {

    private Modele modele;
    private Scene vue;
    private String isRegistered = "Félicitations";

    /**
     * Constructeur de la classe. Assigne les paramètres passés aux champs privés.
     *
     * @param modele Classe Modele que l'on passe pour avoir accès aux méthodes.
     * @param scene La scèce de notre application afin d'avoir accès aux éléments graphiques.
     */
    public Controleur(Modele modele, Scene scene){
        this.modele = modele;
        this.vue = scene;
    }

    /**
     * Méthode qui affiche les cours dans le TableView selon la session rentrée dans le ComboBox.
     *
     * @param session Session sélectionnée dans le ComboBox dans l'interface.
     */
    public void load(String session){
        ArrayList<Course> cours = this.modele.charger(session);

        TableView table = (TableView) vue.lookup("#tableView");

        table.getItems().clear();

        for (int i = 0; i < cours.size(); i++) {

            table.getItems().add(cours.get(i));

        }

    }

    /**
     * Méthode qui fait appel à une méthode de Modele et qui affiche les boites de Dialog selon la réponse/l'état de l'inscription.
     */
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

        if (returnMsg.contains(isRegistered)){

            alert.setAlertType(Alert.AlertType.CONFIRMATION);

        } else{

            alert.setAlertType(Alert.AlertType.ERROR);

        }

        alert.setContentText(returnMsg);
        alert.showAndWait();
    }

}
