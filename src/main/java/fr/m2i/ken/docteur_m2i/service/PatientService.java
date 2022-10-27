package fr.m2i.ken.docteur_m2i.service;

import fr.m2i.ken.docteur_m2i.business.Patient;

import java.util.ArrayList;
import java.util.Date;

public interface PatientService {

    Patient addPatient(String nom, String prenom, Date dateNaissance);
    Patient getPatientById(Long id);
    ArrayList<Patient> getAllPatients();

    Patient updatePatient(Patient patient);

    ArrayList<Patient> getPatientListByAge();

}
