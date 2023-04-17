package main;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import main.models.Course;

public class Controleur {

    private Modele modele;
    private Scene vue;

    public Controleur(Modele modele, Scene scene){
        this.modele = modele;
        this.vue = scene;
    }

    public void load(String session){
        this.modele.charger();
        System.out.println(session);
        TableView table = (TableView) vue.lookup("#tableView");
        System.out.println("Tableau: "+ table.toString());
        table.getItems().add(new Course("intro prog","IFt1015", "Automne"));

    }

    public void send(){
        this.modele.inscrire();
    }



}
