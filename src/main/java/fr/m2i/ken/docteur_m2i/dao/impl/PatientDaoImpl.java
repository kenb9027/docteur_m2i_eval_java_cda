package fr.m2i.ken.docteur_m2i.dao.impl;

import fr.m2i.ken.docteur_m2i.business.Medecin;
import fr.m2i.ken.docteur_m2i.business.Patient;
import fr.m2i.ken.docteur_m2i.dao.ConnectionBDD;
import fr.m2i.ken.docteur_m2i.dao.PatientDao;
import fr.m2i.ken.docteur_m2i.dao.Queries;

import java.sql.*;
import java.util.ArrayList;

public class PatientDaoImpl implements PatientDao {

    private Connection connection;

    public PatientDaoImpl(){
        try {
            connection = ConnectionBDD.getConnection();
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e);
            throw new RuntimeException();
        }
    }


    @Override
    public Patient create(Patient patient) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.PATIENT_CREATE,
                Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1, patient.getNom());
        preparedStatement.setString(2, patient.getPrenom());
        preparedStatement.setDate(3, (Date) patient.getDateNaissance());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next())
        {
            patient.setId(resultSet.getLong(1));
        };

        return patient;
    }

    @Override
    public Patient findOneById(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.PATIENT_FIND_ONE_BY_ID
        );
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            Patient patient = new Patient();
            patient.setId(resultSet.getLong("id"));
            patient.setNom(resultSet.getString("nom"));
            patient.setPrenom(resultSet.getString("prenom"));
            patient.setDateNaissance(resultSet.getDate("date"));
            return patient;

        }

        return new Patient();
    }

    @Override
    public ArrayList<Patient> findAll() throws SQLException {
        ArrayList<Patient> patientList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.PATIENT_FIND_ALL
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Patient patient = new Patient();
            patient.setId(resultSet.getLong("id"));
            patient.setNom(resultSet.getString("nom"));
            patient.setPrenom(resultSet.getString("prenom"));
            patient.setDateNaissance(resultSet.getDate("date"));
            patientList.add(patient);
        }
        return patientList;
    }

    @Override
    public Patient update(Patient patient) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Patient> findAllByAge() throws SQLException {
        return null;
    }
}
