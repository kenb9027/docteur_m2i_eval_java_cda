package fr.m2i.ken.docteur_m2i.dao;

import fr.m2i.ken.docteur_m2i.business.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientDao {

    Patient create(Patient patient) throws SQLException;
    Patient findOneById(Long id) throws SQLException;
    ArrayList<Patient> findAll() throws SQLException;

    Patient update(Patient patient) throws SQLException;
    ArrayList<Patient> findAllByAge() throws SQLException;
}
