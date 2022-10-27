package fr.m2i.ken.docteur_m2i.business;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Medecin extends Personne{

    private LocalDateTime dateEmbauche;

    public Medecin() {
    }

    public Medecin(String nom, String prenom, LocalDateTime dateEmbauche) {
        super(nom, prenom);
        this.dateEmbauche = dateEmbauche;
    }


    public String toString() {
        return super.toString + "Medecin {" +
                " id= " + getId() + " , nom= " + getNom() +
                " , prenom= " + getPrenom() +
                " , dateEmbauche= " + dateEmbauche.getDayOfMonth() +"/"+dateEmbauche.getMonthValue() +"/"+dateEmbauche.getYear() +
                '}';
    }

    public LocalDateTime getDateEmbauche() {
        return dateEmbauche;
    }
    public Timestamp getDateEmbaucheTimestamp() {
        Timestamp tmstp = Timestamp.valueOf(dateEmbauche);
        return tmstp;
    }
    public void setDateEmbauche(LocalDateTime dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
}
