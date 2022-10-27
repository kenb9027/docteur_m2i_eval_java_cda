package fr.m2i.ken.docteur_m2i.service;

import fr.m2i.ken.docteur_m2i.business.Medecin;

import java.util.ArrayList;
import java.util.Date;

public interface MedecinService {

    Medecin addMedecin(String nom, String prenom, Date dateEmbauche);
    Medecin getMedecinById(Long id);
    ArrayList<Medecin> getAllMedecins();
}
