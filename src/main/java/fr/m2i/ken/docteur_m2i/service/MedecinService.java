package fr.m2i.ken.docteur_m2i.service;

import fr.m2i.ken.docteur_m2i.business.Medecin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;

public interface MedecinService {

    Medecin addMedecin(String nom, String prenom, LocalDateTime dateEmbauche);
    Medecin getMedecinById(Long id);
    ArrayList<Medecin> getAllMedecins();
}
