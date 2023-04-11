package main;

/**
 * Interface des événements/requêtes que le serveur peut traiter.
 */
@FunctionalInterface
public interface EventHandler {
    /**
     * Méthode à implémenter qui traite les événements des requêtes de l'utilisateur.
     *
     * @param cmd Commande entrée par l'utilisateur.
     * @param arg Arguments qui suivent la commande entrée.
     */
    void handle(String cmd, String arg);
}
