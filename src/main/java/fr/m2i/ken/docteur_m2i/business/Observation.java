package fr.m2i.ken.docteur_m2i.business;

import java.time.LocalDateTime;

public class Observation {

    private Long id;
    private LocalDateTime dateCreation;
    private String contenu;

    private Patient patient;
    private Medecin medecin;
}
