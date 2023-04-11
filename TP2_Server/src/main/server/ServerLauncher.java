package main.server;

/**
 * Classe qui contient s'occupe de lancer un serveur selon le port donne.
 */
public class ServerLauncher {
    /**
     * Port que l'on desire utiliser pour le serveur.
     */
    public final static int PORT = 8080;

    /**
     * Fonction main qui cree une instance de Server selon le port donne. Elle s'occupe ensuite
     * de lancer le serveur et de nous aviser qu'il est actif.
     *
     * @param args Arguments que l'on peut passer a notre fonction.
     */
    public static void main(String[] args) {
        Server server;

        System.out.println("Hello Test de compilation");

        try {
            server = new Server(PORT);
            System.out.println("Server is running...");
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}