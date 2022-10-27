package fr.m2i.ken.docteur_m2i.service;

import fr.m2i.ken.docteur_m2i.business.Parametre;

import java.util.ArrayList;

public interface ParametreService {

    Parametre addParametre(String nom);
    Parametre getParametreById(Long id);
    ArrayList<Parametre> getAllParametres();
}
