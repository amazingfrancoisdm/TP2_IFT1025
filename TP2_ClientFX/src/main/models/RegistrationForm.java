package main.models;

import java.io.Serializable;

/**
 * Objet qui décrit un formulaire d'inscription à un cours. Celui-ci contient toutes les informations nécessaires
 * afin de s'inscrire à un cours.
 * Cette classe implémente Serializable afin de pouvoir sérialiser ses instances pour du transfer d'information entre
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
     * @param prenom Prénom de l'utilisateur.
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
     * Méthode d'accès au prénom de l'utilisateur.
     *
     * @return Retourne le prénom de l'utilisateur.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Méthode de modification du prénom de l'utilisateur.
     *
     * @param prenom Nouveau prénom qu'on assigne à l'utilisateur.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Méthode d'accès au nom de l'utilisateur.
     *
     * @return Retourne le nom de l'utilisateur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode de modification du nom de l'utilisateur.
     *
     * @param nom Nouveau nom qu'on assigne a l'utilisateur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode d'accès au email de l'utilisateur.
     *
     * @return Retourne le email de l'utilisateur.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Méthode de modification du email de l'utilisateur.
     *
     * @param email Nouveau email qu'on assigne à l'utilisateur.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Méthode d'accès au matricule de l'utilisateur.
     *
     * @return Retourne le matricule de l'utilisateur.
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Méthode de modification du matricule de l'utilisateur.
     *
     * @param matricule Nouveau matricule qu'on assigne à l'utilisateur.
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Méthode d'accès au cours auquel l'utilisateur veut s'inscrire.
     *
     * @return Retourne le cours auquel l'utilisateur veut s'inscrire.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Méthode de modification du cours auquel l'utilisateur veut s'inscrire.
     *
     * @param course Nouveau cours auquel l'utilisateur veut s'inscrire.
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Redéfinition de la méthode toString() qui imprime une instance d'un objet RegistrationForm.
     * La méthode résume les informations de l'instance.
     *
     * @return Retourne un String qui contient le résumé des informations du formulaire.
     */
    @Override
    public String toString() {
        return "InscriptionForm{" + "prenom='" + prenom + '\'' + ", nom='" + nom + '\'' + ", email='" + email + '\'' + ", matricule='" + matricule + '\'' + ", course='" + course + '\'' + '}';
    }
}
