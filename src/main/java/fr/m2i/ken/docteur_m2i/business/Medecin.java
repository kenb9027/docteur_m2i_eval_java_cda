package fr.m2i.ken.docteur_m2i.business;

import java.util.Date;

public class Medecin extends Personne{

    private Date dateEmbauche;

    public Medecin() {
    }

    public Medecin(String nom, String prenom, Date dateEmbauche) {
        super(nom, prenom);
        this.dateEmbauche = dateEmbauche;
    }


    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
}
