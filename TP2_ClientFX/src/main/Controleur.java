package main;

import javafx.scene.Scene;

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
    }

    public void send(){
        this.modele.inscrire();
    }



}
