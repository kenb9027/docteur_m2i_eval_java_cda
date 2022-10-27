package fr.m2i.ken.docteur_m2i.business;

import java.util.Date;

public class Patient extends Personne{

    private Date dateNaissance;

    public Patient() {
    }

    public Patient(String nom, String prenom, Date dateNaissance) {
        super(nom, prenom);
        this.dateNaissance = dateNaissance;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
