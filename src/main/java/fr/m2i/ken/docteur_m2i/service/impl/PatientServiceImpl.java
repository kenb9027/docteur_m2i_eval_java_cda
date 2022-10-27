package fr.m2i.ken.docteur_m2i.service.impl;

import fr.m2i.ken.docteur_m2i.business.Medecin;
import fr.m2i.ken.docteur_m2i.business.Patient;
import fr.m2i.ken.docteur_m2i.dao.PatientDao;
import fr.m2i.ken.docteur_m2i.dao.impl.PatientDaoImpl;
import fr.m2i.ken.docteur_m2i.service.PatientService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;

public class PatientServiceImpl implements PatientService {

    private PatientDao patientDao = new PatientDaoImpl();
    @Override
    public Patient addPatient(String nom, String prenom, LocalDateTime dateNaissance) {
        Patient newPatient = new Patient(nom , prenom, dateNaissance);
        try {
            return patientDao.create(newPatient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newPatient;
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            return patientDao.findOneById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Patient> getAllPatients() {
        try {
            return patientDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Patient updatePatient(Patient patient) {

        try {
            return patientDao.update(patient);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public ArrayList<Patient> getPatientListByAge() {
        return null;
    }
}
