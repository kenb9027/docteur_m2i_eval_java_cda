package fr.m2i.ken.docteur_m2i.service;

import fr.m2i.ken.docteur_m2i.business.Patient;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface PatientService {

    Patient addPatient(String nom, String prenom, LocalDateTime dateNaissance);
    Patient getPatientById(Long id);
    ArrayList<Patient> getAllPatients();

    Patient updatePatient(Patient patient);

    ArrayList<Patient> getPatientListByAge();

}
