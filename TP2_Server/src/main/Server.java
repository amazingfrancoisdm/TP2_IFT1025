package main;

import javafx.util.Pair;
import main.models.Course;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Server est une classe qui établie des connexions entre des utilisateurs et le serveur.
 * C'est une classe qui traite les requêtes d'informations. Server écoute les requêtes faites par l'utilisateur et gère ensuite celles-ci.
 * La classe renvoie à l'utilisateur les cours offerts selon la session voulue et peut aussi inscrire l'utilisateur à des cours.
 *
 * @author Oussama Sghaier
 *
 */
public class Server {
    /**
     * Le String (commande) que l'utilisateur doit écrire afin de s'inscrire.
     */
    public final static String REGISTER_COMMAND = "INSCRIRE";
    /**
     * La String (commande) que l'utilisateur doit écrire afin de charger les cours qu'il veut voir selon la session.
     */
    public final static String LOAD_COMMAND = "CHARGER";
    private final ServerSocket server;
    private Socket client;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final ArrayList<EventHandler> handlers;

    /**
     * Constructeur pour créer un serveur qui établie la connexion avec un maximum de 1 client au port spécifié.
     * Le constructeur crée un tableau de EventHandlers.
     *
     * @param port Port spécifié.
     * @throws IOException Exception d'entrée ou de sortie lors de la création du ServerSocket.
     */
    public Server(int port) throws IOException {
        this.server = new ServerSocket(port, 1);
        this.handlers = new ArrayList<EventHandler>();
        this.addEventHandler(this::handleEvents);
        System.out.println("Serveur créé");
    }

    /**
     * Méthode qui permet d'ajouter un EventHandler au tableau handlers.
     *
     * @param h EventHandler que l'on veut ajouter au tableau handlers.
     */
    public void addEventHandler(EventHandler h) {
        this.handlers.add(h);
    }

    /**
     * Méthode qui passe à travers tous les événements en leur passant la commande et les arguments afin que les
     * événements appropriés répondent.
     *
     * @param cmd Commande passée en paramètre dans le "Event".
     * @param arg Arguments passés en paramètre dans le "Event".
     */
    private void alertHandlers(String cmd, String arg) {
        for (EventHandler h : this.handlers) {
            h.handle(cmd, arg);
        }
    }

    /**
     * Méthode qui accepte le client, lance le serveur et qui appelle une méthode pour écouter les requêtes du client
     * et les gérer. La méthode run() déconnecte tout une fois que les requêtes ont été répondues. Le cycle recommence.
     *
     */
    public void run() {
        while (true) {
            try {
                client = server.accept();
                System.out.println("Connecté au client: " + client);
                objectInputStream = new ObjectInputStream(client.getInputStream());
                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                listen();
                disconnect();
                System.out.println("Client déconnecté!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Méthode qui lit les requêtes reçues par le socket. Elle appelle ensuite alertHandlers() afin de répondre
     * aux requêtes.
     *
     * @throws IOException Exception d'entrée ou de sortie lorsqu'on utilise le stream d'input.
     * @throws ClassNotFoundException Exception de classe lorsqu'on utilise le stream d'input.
     */
    public void listen() throws IOException, ClassNotFoundException {
        String line;
        if ((line = this.objectInputStream.readObject().toString()) != null) {
            Pair<String, String> parts = processCommandLine(line);
            String cmd = parts.getKey();
            String arg = parts.getValue();
            this.alertHandlers(cmd, arg);
        }
    }

    /**
     * Méthode qui prend une ligne en paramètre et qui la sépare et une commande et ses arguments.
     * Celle-ci crée un Objet Pair qui contient la commande et ses arguments.
     *
     * @param line Ligne rentrée dans la ligne de commande par l'utilisateur.
     * @return Pair qui contient la commande et ses arguments.
     */
    public Pair<String, String> processCommandLine(String line) {
        String[] parts = line.split(" ");
        String cmd = parts[0];
        String args = String.join(" ", Arrays.asList(parts).subList(1, parts.length));
        return new Pair<>(cmd, args);
    }

    /**
     * Méthode qui ferme les streams et qui déconnecte le client du serveur.
     *
     * @throws IOException Exception d'entrée ou de sortie lorsqu'on utilise le stream d'input/output.
     */
    public void disconnect() throws IOException {
        objectOutputStream.close();
        objectInputStream.close();
        client.close();
    }

    /**
     * Méthode qui appelle la bonne fonction selon la commande rentrée par l'utilisateur. handleReistration() est appelée
     * si l'utilisateur rentre la valeur de la variable REGISTER_COMMANDE, idem pour LOAD_COMMAND qui appelle handleLoadCourses(arg).
     *
     * @param cmd Commande rentrée par l'utilisateur
     * @param arg Arguments qui suivent la commande rentrée par l'utilisateur.
     */
    public void handleEvents(String cmd, String arg) {
        if (cmd.equals(REGISTER_COMMAND)) {
            handleRegistration();
        } else if (cmd.equals(LOAD_COMMAND)) {
            handleLoadCourses(arg);
        }
    }

    /**
     Lire un fichier texte contenant des informations sur les cours et les transofmer en liste d'objets 'Course'.
     La méthode filtre les cours par la session spécifiée en argument.
     Ensuite, elle renvoie la liste des cours pour une session au client en utilisant l'objet 'objectOutputStream'.
     La méthode gère les exceptions si une erreur se produit lors de la lecture du fichier ou de l'écriture de l'objet dans le flux.

     @param arg la session pour laquelle on veut récupérer la liste des cours
     */
    public void handleLoadCourses(String arg) {

        //System.out.println("Requete reçue.");

        ArrayList<Course> cours = new ArrayList<>();
        ArrayList<Course> coursToSend = new ArrayList<>();

        try{
            FileReader file = new FileReader("src/main/data/cours.txt");

            BufferedReader reader = new BufferedReader(file);

            String line;

            while((line = reader.readLine()) != null){

                String[] lineSplit = line.split("\t");
                cours.add(new Course(lineSplit[1],lineSplit[0],lineSplit[2]));
            }

            reader.close();

            for (int i=0; i< cours.size(); i++){

                if(cours.get(i).getSession().equals(arg)){

                    coursToSend.add(cours.get(i));

                }
            }

            //System.out.println("Tableau de cours à envoyer créé.");

        } catch (IOException e){
            System.out.println("Erreur de lecture du fichier.");
        }

        System.out.println(coursToSend.get(0).toString());

        try{

            objectOutputStream.writeObject(coursToSend);
            //System.out.println("Tableau de cours envoyé");

        } catch(IOException e){
            System.out.println("Erreur d'éciture du fichier.");
        }


    }

    /**
     Récupérer l'objet 'RegistrationForm' envoyé par le client en utilisant 'objectInputStream', l'enregistrer dans un fichier texte
     et renvoyer un message de confirmation au client.
     La méthode gére les exceptions si une erreur se produit lors de la lecture de l'objet, l'écriture dans un fichier ou dans le flux de sortie.
     */
    public void handleRegistration() {
        // TODO: implémenter cette méthode
    }
}

