package fr.m2i.ken.docteur_m2i.dao.impl;

import fr.m2i.ken.docteur_m2i.business.Medecin;
import fr.m2i.ken.docteur_m2i.dao.ConnectionBDD;
import fr.m2i.ken.docteur_m2i.dao.MedecinDao;
import fr.m2i.ken.docteur_m2i.dao.Queries;

import java.sql.*;
import java.util.ArrayList;

public class MedecinDaoImpl implements MedecinDao {

    private Connection connection;

    public MedecinDaoImpl(){
        try {
            connection = ConnectionBDD.getConnection();
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Medecin create(Medecin medecin) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.MEDECIN_CREATE,
                Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1, medecin.getNom());
        preparedStatement.setString(2, medecin.getPrenom());
        preparedStatement.setDate(3, (Date) medecin.getDateEmbauche());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next())
        {
            medecin.setId(resultSet.getLong(1));
        };

        return medecin;
    }

    @Override
    public Medecin findOneById(Long id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.MEDECIN_FIND_ONE_BY_ID
        );
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            Medecin medecin = new Medecin();
            medecin.setId(resultSet.getLong("id"));
            medecin.setNom(resultSet.getString("nom"));
            medecin.setPrenom(resultSet.getString("prenom"));
            medecin.setDateEmbauche(resultSet.getDate("date"));
            return medecin;

        }

        return new Medecin();
    }

    @Override
    public ArrayList<Medecin> findAll() throws SQLException {
        ArrayList<Medecin> medecinList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.MEDECIN_FIND_ALL
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Medecin medecin = new Medecin();
            medecin.setId(resultSet.getLong("id"));
            medecin.setNom(resultSet.getString("nom"));
            medecin.setPrenom(resultSet.getString("prenom"));
            medecin.setDateEmbauche(resultSet.getDate("date"));
            medecinList.add(medecin);
        }
        return medecinList;
    }
}
