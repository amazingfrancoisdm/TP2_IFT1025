package main.server.models;

import java.io.Serializable;

/**
 * Objet qui decrit un formulaire d'inscription a un cours. Celui-ci contient tous les informations necessaires
 * afin de s'inscrire a un cours.
 * Cette classe implemente Serializable afin de pouvoir serialiser ses instances pour du transfer d'informations entre
 * le client et le serveur.
 */
public class RegistrationForm implements Serializable {
    private String prenom;
    private String nom;
    private String email;
    private String matricule;
    private Course course;

    /**
     * Constructeur d'un formulaire d'inscription. Assignation des informations de l'utilisateur.
     *
     * @param prenom Prenom de l'utilisateur.
     * @param nom Nom de l'utilisateur.
     * @param email Email de l'utilisateur.
     * @param matricule Matricle de l'utilisateur.
     * @param course Cours auquel l'utilisateur veut s'inscrire.
     */
    public RegistrationForm(String prenom, String nom, String email, String matricule, Course course) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.matricule = matricule;
        this.course = course;
    }

    /**
     * Methode d'acces au prenom de l'utilisateur.
     *
     * @return Retourne le prenom de l'utilisateur.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Methode de modification du prenom de l'utilisateur.
     *
     * @param prenom Nouveau prenom qu'on assigne a l'utilisateur.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Methode d'acces au nom de l'utilisateur.
     *
     * @return Retourne le nom de l'utilisateur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Methode de modification du prenom de l'utilisateur.
     *
     * @param nom Nouveau nom qu'on assigne a l'utilisateur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Methode d'acces au email de l'utilisateur.
     *
     * @return Retourne le email de l'utilisateur.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Methode de modification du email de l'utilisateur.
     *
     * @param email Nouveau email qu'on assigne a l'utilisateur.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Methode d'acces au matricule de l'utilisateur.
     *
     * @return Retourne le matricule de l'utilisateur.
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Methode de modification du matricule de l'utilisateur.
     *
     * @param matricule Nouveau matricule qu'on assigne a l'utilisateur.
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Methode d'acces au cours auquel l'utilisateur veut s'inscrire.
     *
     * @return Retourne le cours auquel l'utilisateur veut s'inscrire.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Methode de modification du cours auquel l'utilisateur veut s'inscrire.
     *
     * @param course Nouveau cours auquel l'utilisateur veut s'inscrire.
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Redefinition de la methode toString() qui imprime une instance d'un objet RegistrationForm.
     * La methode resume les informations de l'instance.
     *
     * @return Retourne un String qui contient le resume des informations du formulaire.
     */
    @Override
    public String toString() {
        return "InscriptionForm{" + "prenom='" + prenom + '\'' + ", nom='" + nom + '\'' + ", email='" + email + '\'' + ", matricule='" + matricule + '\'' + ", course='" + course + '\'' + '}';
    }
}
