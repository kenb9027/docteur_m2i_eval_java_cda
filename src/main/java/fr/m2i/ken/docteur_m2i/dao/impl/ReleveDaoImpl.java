package fr.m2i.ken.docteur_m2i.dao.impl;

import fr.m2i.ken.docteur_m2i.business.Parametre;
import fr.m2i.ken.docteur_m2i.business.Releve;
import fr.m2i.ken.docteur_m2i.dao.*;

import java.sql.*;
import java.util.ArrayList;

public class ReleveDaoImpl implements ReleveDao {

    private Connection connection;
    private ParametreDao parametreDao = new ParametreDaoImpl();
    private MedecinDao medecinDao = new MedecinDaoImpl();
    private PatientDao patientDao = new PatientDaoImpl() ;

    public ReleveDaoImpl(){

        //on teste la connection à la base de donnée lors de la création de l'objet
        try {
            connection = ConnectionBDD.getConnection();

            //on en profite pour tester la création des dao
            ParametreDao parametreDao = new ParametreDaoImpl();
            MedecinDao medecinDao = new MedecinDaoImpl();
            PatientDao patientDao = new PatientDaoImpl();


        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }
    @Override
    public Releve create(Releve releve) throws SQLException {
        // préparation de la requete pour la base de données
        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.RELEVE_CREATE,
                Statement.RETURN_GENERATED_KEYS
        );
        //on charge les données dans la requete, en remplacant les "?" par les valeurs de l'objet
        preparedStatement.setTimestamp(1, releve.getDateCreationTimestamp());
        preparedStatement.setFloat(2, releve.getValeur());
        preparedStatement.setLong(3 , releve.getPatient().getId() );
        preparedStatement.setLong(4 , releve.getMedecin().getId() );
        preparedStatement.setLong(5 , releve.getParametre().getId() );


        //execution de la requete , puis on récupère la clé générée (si elle existe)
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        //si la clé existe , on l'assigne à l'objet renvoyé
        if (resultSet.next())
        {
            releve.setId(resultSet.getLong(1));
        };

        return releve;

    }

    @Override
    public Releve findOneById(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.RELEVE_FIND_ONE_BY_ID
        );
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            Releve releve = new Releve();
            releve.setId(resultSet.getLong("id"));
            return releve;

        }

        return new Releve();
    }



    @Override
    public ArrayList<Releve> sortByDateDesc() throws SQLException {
        ArrayList<Releve> releveList = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(Queries.RELEVE_SORT_BY_DATE_DESC);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            Releve releve = new Releve();
            releve.setId(resultSet.getLong("id"));
            releve.setDateCreation(resultSet.getTimestamp("dateCreation").toLocalDateTime());
            releve.setValeur(resultSet.getFloat("valeur"));
            releve.setParametre( this.parametreDao.findOneById( resultSet.getLong("parametreId") ) );
            releve.setMedecin( this.medecinDao.findOneById( resultSet.getLong("medecinId") ) );
            releve.setPatient( this.patientDao.findOneById( resultSet.getLong("patientId") ) );
            releveList.add(releve);
        }
        return releveList;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.RELEVE_DELETE_BY_ID);
        preparedStatement.setLong(1, id);
        return preparedStatement.execute();
    }
}
