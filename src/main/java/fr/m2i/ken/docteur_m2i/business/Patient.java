package fr.m2i.ken.docteur_m2i.business;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Patient extends Personne{

    private LocalDateTime dateNaissance;

    public Patient() {
    }

    public Patient(String nom, String prenom, LocalDateTime dateNaissance) {
        super(nom, prenom);
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + getId() + " , nom=" + getNom() +
                " , prenom=" + getPrenom() +
                " , dateNaissance=" + dateNaissance.getDayOfMonth() +"/"+dateNaissance.getMonthValue() +"/"+dateNaissance.getYear() +
                '}';
    }

    public LocalDateTime getDateNaissance() {
        return dateNaissance;
    }

    public Timestamp getDateNaissanceTimestamp() {
        Timestamp tmstp = Timestamp.valueOf(dateNaissance);
        return tmstp;
    }

    public void setDateNaissance(LocalDateTime dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
