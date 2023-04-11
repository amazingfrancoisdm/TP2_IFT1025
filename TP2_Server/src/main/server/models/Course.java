package main.server.models;

import java.io.Serializable;

/**
 * Objet qui represente un cours d'universite. Cette classe contient toutes les informations pour decrire un cours.
 * Celle-ci implemente l'interface Serializable afin de serialiser ses instances pour du transfert d'informations entre
 * le client et le serveur.
 */
public class Course implements Serializable {

    private String name;
    private String code;
    private String session;

    /**
     * Constructeur de l'objet course. Assignation des donnees qui reprentent un cours.
     *
     * @param name Nom du cours.
     * @param code Code (sigle) du cours.
     * @param session Session a laquelle le cours est donne.
     */
    public Course(String name, String code, String session) {
        this.name = name;
        this.code = code;
        this.session = session;
    }

    /**
     * Methode d'acces au nom du cours.
     *
     * @return Le nom du cours.
     */
    public String getName() {
        return name;
    }

    /**
     * Methode de modification du nom du cours.
     *
     * @param name Nouveau nom a assigner au cours.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Methode d'acces au code du cours.
     *
     * @return Retourne le code du cours.
     */
    public String getCode() {
        return code;
    }

    /**
     * Methode de modification du code du cours.
     *
     * @param code Nouveau code a assigner au cours.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Methode d'acces a la session ou le cours est donne.
     *
     * @return Retourne la session ou le cours est disponible.
     */
    public String getSession() {
        return session;
    }

    /**
     * Methode de modification de session ou le cours est disponible.
     *
     * @param session Nouvelle session ou le cours est disponible.
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * Redefinition de la methode toString() qui imprime une instance d'un objet Course.
     * La methode resume les informations de l'instance.
     *
     * @return Retourne un String qui contient le resume des informations du cours.
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
