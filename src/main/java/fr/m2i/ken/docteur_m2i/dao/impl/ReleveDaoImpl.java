package fr.m2i.ken.docteur_m2i.dao.impl;

import fr.m2i.ken.docteur_m2i.business.Parametre;
import fr.m2i.ken.docteur_m2i.business.Releve;
import fr.m2i.ken.docteur_m2i.dao.*;

import java.sql.*;
import java.util.ArrayList;

public class ReleveDaoImpl implements ReleveDao {

    private Connection connection;
    ParametreDao parametreDao = new ParametreDaoImpl();
    MedecinDao medecinDao = new MedecinDaoImpl();
    PatientDao patientDao = new PatientDaoImpl() ;

    public ReleveDaoImpl(){
        try {
            connection = ConnectionBDD.getConnection();

            ParametreDao parametreDao = new ParametreDaoImpl();
            MedecinDao medecinDao = new MedecinDaoImpl();
            PatientDao patientDao = new PatientDaoImpl();


        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }
    @Override
    public Releve create(Releve releve) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.RELEVE_CREATE,
                Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setTimestamp(1, releve.getDateCreationTimestamp());
        preparedStatement.setFloat(2, releve.getValeur());
        preparedStatement.setLong(3 , releve.getPatient().getId() );
        preparedStatement.setLong(4 , releve.getMedecin().getId() );
        preparedStatement.setLong(5 , releve.getParametre().getId() );


        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();

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
