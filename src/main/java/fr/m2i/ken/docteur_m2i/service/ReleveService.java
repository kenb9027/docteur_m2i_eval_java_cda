package fr.m2i.ken.docteur_m2i.service;

import fr.m2i.ken.docteur_m2i.business.Medecin;
import fr.m2i.ken.docteur_m2i.business.Parametre;
import fr.m2i.ken.docteur_m2i.business.Patient;
import fr.m2i.ken.docteur_m2i.business.Releve;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public interface ReleveService {

    Releve addReleve(
            LocalDateTime dateCreation,
            float valeur,
            Parametre parametre,
            Patient patient,
            Medecin medecin);
    Releve getReleveById(Long id);
    ArrayList<Releve> getAllReleves();

    ArrayList<Releve> sortByDateDesc();

    void deleteById(Long id);



}
