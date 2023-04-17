package main;

import main.models.Course;
import main.models.RegistrationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Classe qui gère le fonctionnement de l'application selon le design MVC.
 * Elle s'occupe des sockets client-serveur.
 */
public class Modele {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;

    /**
     * Constructeur de Modele afin de l'instancier.
     */
    public Modele(){}

    /**
     * Méthode qui récupère la liste de cours au serveur selon la session demandée.
     *
     * @param session Session choisie par l'utilisateur.
     * @return Retourne un ArrayList des cours disponibles selon la session.
     */
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

    /**
     * Méthode qui vérifie si les champs sont tous valides, si oui, on appelle la fonction inscrire().
     *
     * @param prenom Prénom entré par l'utilisateur.
     * @param nom Nom entré par l'utilisateur.
     * @param email Email entré par l'utilisateur.
     * @param matricule Matricule entré par l'utilisateur.
     * @param cours Cours sélectionné par l'utilisateur.
     * @return Retourne un message qui contient soit les erreurs d'entrées ou la confirmation d'inscription.
     */
    public String isApplicationValid(String prenom, String nom, String email, String matricule, Course cours){

        ArrayList<String> errors = new ArrayList<>();

        if (prenom.length()==0)
            errors.add("Le champ 'Prénom' est invalide!");

        if (nom.length()==0)
            errors.add("Le champ 'Nom' est invalide!");

        if (!isEmailValid(email))
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

    /**
     * Méthode qui envoie une requête d'inscription au serveur et qui retourne sa réponse.
     *
     * @param prenom Prénom de l'utilisateur.
     * @param nom Nom de l'utilisateur.
     * @param email Email de l'utilisateur.
     * @param matricule Matricule de l'utilisateur.
     * @param cours Cours de l'utilisateur.
     * @return Retourne la confirmation d'inscription du serveur.
     */
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

    /**
     * Méthode qui vérifie le format du email selon un regex défini.
     *
     * @param email Email entrée par l'utilisateur.
     * @return Retourne une valeur true ou false.
     */
    private boolean isEmailValid(String email){
        String regex = "^[a-zA-Z0-9._&-]+@[a-zA-Z0-9_]+\\.[a-zA-Z]{2,}$";

        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(email).matches();
    }


}
