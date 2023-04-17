package main;

import main.models.Course;
import main.models.RegistrationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Modele {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;

    public Modele(){

    }

    public ArrayList<Course> charger(String session){

        try {
            Socket client = new Socket(HOST,PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());

            String commande = "CHARGER " + session;

            os.writeObject(commande);
            os.flush();

            ObjectInputStream is = new ObjectInputStream(client.getInputStream());

            ArrayList<Course> cours = new ArrayList<>();

            cours = (ArrayList<Course>) is.readObject();

            os.close();
            is.close();
            client.close();

            return cours;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public String isApplicationValid(String prenom, String nom, String email, String matricule, Course cours){

        ArrayList<String> errors = new ArrayList<>();

        if (prenom.length()==0)
            errors.add("Le champ 'Prénom' est invalide!");

        if (nom.length()==0)
            errors.add("Le champ 'Nom' est invalide!");

        if (email.length()==0)
            errors.add("Le champ 'Email' est invalide!");

        if (matricule.length()!=8)
            errors.add("Le champ 'Matricule' est invalide!");

        if (cours==null)
            errors.add("Vous devez sélectionner un cours!");

        if (errors.size()==0){
            return inscrire(prenom, nom, email, matricule, cours);
        }

        String errorMsg = "";

        for (int i = 0; i < errors.size(); i++) {
                if (i== errors.size()-1){
                    errorMsg += errors.get(i);
                    break;
                }
                errorMsg += errors.get(i)+"\n";
        }

        return errorMsg;
    }

    public String inscrire(String prenom, String nom, String email, String matricule, Course cours){

        String msg;

        try {
            Socket client = new Socket(HOST, PORT);

            RegistrationForm form = new RegistrationForm(prenom, nom, email, matricule, cours);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());

            os.writeObject("INSCRIRE");
            os.writeObject(form);
            os.flush();

            ObjectInputStream is = new ObjectInputStream(client.getInputStream());

            msg = (String) is.readObject();

            client.close();
            os.close();
            is.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return msg;
    }


    private boolean isEmailValid(String email){
        return true;
    }

}
