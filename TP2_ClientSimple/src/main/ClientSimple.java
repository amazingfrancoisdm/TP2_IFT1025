package main;

import main.models.Course;
import main.models.RegistrationForm;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientSimple {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;
    public static void main(String[] args) {

        ArrayList<Course> cours = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        System.out.println("*** Bienvenue au portail dd'inscription de cours de l'UDEM ***");



        while(true){

            System.out.println("Veuillez choisir la session pour laquelle vous voulez consulter la liste de cours:\n1. Automne\n2. Hiver\n3. Été");
            System.out.print("> Choix: ");
            int choix = scan.nextInt();
            String sessionChoisie = null;

            switch (choix){
                case 1:
                    cours = charger("Automne");
                    sessionChoisie = "automne";
                    break;
                case 2:
                    cours = charger("Hiver");
                    sessionChoisie = "hiver";
                    break;
                case 3:
                    cours = charger("Ete");
                    sessionChoisie = "été";
                default:
                    System.out.println("!!! Ceci n'est pas une option valide !!!");
                    System.out.println();
                    continue;
            }
            System.out.println();
            System.out.println("Les cours offerts pendant la session d'"+sessionChoisie+" sont:");
            printCourses(cours);

            boolean isChoiceCorrect = false;
            boolean isTwoChosen = false;

            while(!isChoiceCorrect){
                System.out.println("1. Consulter les cours offerts pour une autre session\n2. Inscription à un cours");

                System.out.print("> Choix: ");
                int choix2 = scan.nextInt();
                System.out.println();

                switch (choix2) {
                    case 1:
                        System.out.println("-----------------------------------------------------------------------------");
                        System.out.println();
                        isChoiceCorrect=true;
                        break;
                    case 2:
                        isChoiceCorrect=true;
                        isTwoChosen = true;
                        inscrire(cours);
                        break;
                    default:
                        System.out.println("!!! Ceci n'est pas une option valide !!!");
                }
            }

            if (isTwoChosen){
                break;
            }
            isChoiceCorrect = false;

        }


    }

    public static void printCourses(ArrayList<Course> cours){

        for (int i = 0; i < cours.size(); i++) {
            System.out.println((i+1)+". " + cours.get(i).getCode() +"\t"+cours.get(i).getName());
        }
        System.out.println();


    }
    public static ArrayList<Course> charger(String sessionChoisie){
        try {
            Socket client = new Socket(HOST, PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());

            String session = "CHARGER " + sessionChoisie;

            os.writeObject(session);
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

    public static void inscrire(ArrayList<Course> cours){
        try {
            Socket client = new Socket(HOST, PORT);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            String prenom;

            while(true){
                System.out.print("Veuillez saisir votre prénom: ");
                prenom = scanner.nextLine();

                if(prenom.length()!=0){
                    break;
                } else {
                    System.out.println("!!! Prénom invalide !!!");
                }
            }

            String nom;

            while(true){
                System.out.print("Veuillez saisir votre nom: ");
                nom = scanner.nextLine();

                if(nom.length()!=0){
                    break;
                } else {
                    System.out.println("!!! Nom invalide !!!");
                }
            }

            String email;

            while(true){
                System.out.print("Veuillez saisir votre email: ");
                email = scanner.nextLine();

                if(email.length()!=0){
                    break;
                } else {
                    System.out.println("!!! Email invalide !!!");
                }
            }

            String matricule;

            while(true) {

                System.out.print("Veuillez saisir votre matricule: ");
                matricule = scanner.nextLine();

                if (matricule.length()==8){
                    break;
                }else{
                    System.out.println("!! Matricule invalide !!");
                }

            }

            String code;
            Course bonCours = null;

            while(true){
                System.out.print("Veuillez saisir le code du cours: ");
                code = scanner.nextLine();

                for (int i = 0; i < cours.size(); i++) {
                    if (cours.get(i).getCode().equals(code)){
                        bonCours = cours.get(i);
                    }
                }

                if (bonCours!=null){
                    break;
                } else {
                    System.out.println("!!! Code de cours invalide !!!");
                }

            }

            RegistrationForm form = new RegistrationForm(prenom, nom, email, matricule, bonCours);

            os.writeObject("INSCRIRE");
            os.writeObject(form);
            os.flush();

            System.out.println();

            ObjectInputStream is = new ObjectInputStream(client.getInputStream());

            String msg = (String) is.readObject();

            System.out.println(msg);

            client.close();
            os.close();
            is.close();

        } catch (IOException e) {
            System.out.println("Erreur d'écriture au serveur.");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de lecture du serveur");
        }
    }
}
