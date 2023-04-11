package main.server.models;

import java.io.Serializable;

/**
 * Objet qui représente un cours d'université. Cette classe contient toutes les informations pour décrire un cours.
 * Celle-ci implémente l'interface Serializable afin de sérialiser ses instances pour du transfert d'information entre
 * le client et le serveur.
 */
public class Course implements Serializable {

    private String name;
    private String code;
    private String session;

    /**
     * Constructeur de l'objet course. Assignation des données qui repréntent un cours.
     *
     * @param name Nom du cours.
     * @param code Code (sigle) du cours.
     * @param session Session à laquelle le cours est donné.
     */
    public Course(String name, String code, String session) {
        this.name = name;
        this.code = code;
        this.session = session;
    }

    /**
     * Méthode d'accès au nom du cours.
     *
     * @return Le nom du cours.
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode de modification du nom du cours.
     *
     * @param name Nouveau nom à assigner au cours.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthode d'accès au code du cours.
     *
     * @return Retourne le code du cours.
     */
    public String getCode() {
        return code;
    }

    /**
     * Méthode de modification du code du cours.
     *
     * @param code Nouveau code à assigner au cours.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Méthode d'accès à la session où le cours est donné.
     *
     * @return Retourne la session où le cours est disponible.
     */
    public String getSession() {
        return session;
    }

    /**
     * Méthode de modification de session où le cours est disponible.
     *
     * @param session Nouvelle session où le cours est disponible.
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * Redéfinition de la méthode toString() qui imprime une instance d'un objet Course.
     * La méthode résume les informations de l'instance.
     *
     * @return Retourne un String qui contient le résumé des informations du cours.
     */
    @Override
    public String toString() {
        return "Course{" +
                "name=" + name +
                ", code=" + code +
                ", session=" + session +
                '}';
    }
}
