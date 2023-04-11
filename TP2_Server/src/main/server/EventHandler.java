package main.server;

/**
 * Interface des evenements/requetes que le serveur peut traiter.
 */
@FunctionalInterface
public interface EventHandler {
    /**
     * Methode a implementer qui traite les evenements des requetes de l'utilisateur.
     *
     * @param cmd Commande entree par l'utilisateur.
     * @param arg Arguments qui suivent la commande entree.
     */
    void handle(String cmd, String arg);
}
