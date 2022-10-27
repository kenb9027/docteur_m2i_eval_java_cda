package fr.m2i.ken.docteur_m2i.business;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Releve {

    private Long id;
    private LocalDateTime dateCreation;
    private float valeur;
    private Parametre parametre;
    private Patient patient;
    private Medecin medecin;

    public Releve() {
    }

    public Releve(LocalDateTime dateCreation, float valeur, Parametre parametre, Patient patient, Medecin medecin) {
        this.dateCreation = dateCreation;
        this.valeur = valeur;
        this.parametre = parametre;
        this.patient = patient;
        this.medecin = medecin;
    }

    @Override
    public String toString() {
        return "Releve{" +
                "id=" + id +
                ", dateCreation=" + dateCreation +
                ", valeur=" + valeur +
                ", parametre=" + parametre +
                ", patient=" + patient +
                ", medecin=" + medecin +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public Timestamp getDateCreationTimestamp() {
        Timestamp tmstp = Timestamp.valueOf(dateCreation);
        return tmstp;
    }



    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Parametre getParametre() {
        return parametre;
    }

    public void setParametre(Parametre parametre) {
        this.parametre = parametre;
    }
}
