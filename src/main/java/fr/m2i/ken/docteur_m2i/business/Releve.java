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

        return "Releve :" +
                " id = " + id +
                " , dateCreation = " + addZeroIfMinusTen(dateCreation.getDayOfMonth()) +"/"+addZeroIfMinusTen(dateCreation.getMonthValue()) +"/"+dateCreation.getYear() +
                " à " + addZeroIfMinusTen(dateCreation.getHour()) +":"+ addZeroIfMinusTen(dateCreation.getMinute()) +
                " , patient = " + patient +
                " , parametre = " + parametre +
                " , valeur = " + valeur +
                " , medecin = " + medecin +
                '}';
    }

    /**
     * Add a zero if the number is inferior to 10 (for minutes , hours , dayOfMonth , Month )
     * @param nb number
     * @return string value of number, with or without zero
     */
    public String addZeroIfMinusTen(int nb){
        if (nb < 10){
            return "0"+nb;
        }
        return String.valueOf(nb);

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
