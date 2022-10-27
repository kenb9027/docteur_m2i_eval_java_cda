package fr.m2i.ken.docteur_m2i.dao.impl;

import fr.m2i.ken.docteur_m2i.business.Parametre;
import fr.m2i.ken.docteur_m2i.dao.ConnectionBDD;
import fr.m2i.ken.docteur_m2i.dao.ParametreDao;
import fr.m2i.ken.docteur_m2i.dao.Queries;

import java.sql.*;
import java.util.ArrayList;

public class ParametreDaoImpl implements ParametreDao {

    private Connection connection;

    public ParametreDaoImpl(){
        try {
            connection = ConnectionBDD.getConnection();
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException();
        }
    }
    @Override
    public Parametre create(Parametre parametre) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.PARAMETRE_CREATE,
                Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1, parametre.getNom());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next())
        {
            parametre.setId(resultSet.getLong(1));
        };

        return parametre;
    }

    @Override
    public Parametre findOneById(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.PARAMETRE_FIND_ONE_BY_ID
        );
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            Parametre parametre = new Parametre();
            parametre.setId(resultSet.getLong("id"));
            parametre.setNom(resultSet.getString("nom"));
            return parametre;

        }

        return new Parametre();
    }

    @Override
    public ArrayList<Parametre> findAll() throws SQLException {
        ArrayList<Parametre> paramList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                Queries.PARAMETRE_FIND_ALL
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Parametre parametre = new Parametre();
            parametre.setId(resultSet.getLong("id"));
            parametre.setNom(resultSet.getString("nom"));
            paramList.add(parametre);
        }
        return paramList;
    }
}
