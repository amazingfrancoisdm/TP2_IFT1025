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
            }
            System.out.println();
            System.out.println("Les cours offerts pendant la session d'"+sessionChoisie+" sont:");
            printCourses(cours);

            System.out.println("Que désirez-vous faire?");
            System.out.println("1. Consulter les cours offerts pour une autre session\n2. Inscription à un cours");

            System.out.print("> Choix: ");
            int choix2 = scan.nextInt();
            System.out.println();

            switch (choix2) {
                case 1:
                    System.out.println("-----------------------------------------------------------------------------");
                    continue;
                case 2:
                    inscrire(cours);
                    break;
            }
            break;
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

            System.out.print("Veuillez saisir votre prénom: ");
            String prenom = scanner.nextLine();

            System.out.print("Veuillez saisir votre nom: ");
            String nom = scanner.nextLine();

            System.out.print("Veuillez saisir votre email: ");
            String email = scanner.nextLine();

            System.out.print("Veuillez saisir votre matricule: ");
            String matricule = scanner.nextLine();

            System.out.print("Veuillez saisir le code du cours: ");
            String code = scanner.nextLine();

            Course bonCours = null;

            for (int i = 0; i < cours.size(); i++) {
                if (cours.get(i).getCode().equals(code)){
                    bonCours = cours.get(i);
                }
            }

            RegistrationForm form = new RegistrationForm(prenom, nom, email, matricule, bonCours);

            os.writeObject("INSCRIRE");
            os.writeObject(form);
            os.flush();

            os.close();
            client.close();

            System.out.println();
            System.out.println("Félicitations! Inscription réussie de "+ prenom+" au cours "+ code+".");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
