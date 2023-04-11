package main.server;

/**
 * Classe qui s'occupe de lancer un serveur selon le port donné.
 */
public class ServerLauncher {
    /**
     * Port que l'on désire utiliser pour le serveur.
     */
    public final static int PORT = 8080;

    /**
     * Fonction main qui crée une instance de Server selon le port donné. Elle s'occupe ensuite
     * de lancer le serveur et de nous aviser qu'il est actif.
     *
     * @param args Arguments que l'on peut passer à notre fonction.
     */
    public static void main(String[] args) {
        Server server;

        try {
            server = new Server(PORT);
            System.out.println("Server is running...");
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}