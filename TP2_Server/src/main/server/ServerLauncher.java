package main.server;

public class ServerLauncher {
    public final static int PORT = 8080;

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