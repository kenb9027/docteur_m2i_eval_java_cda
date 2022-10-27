package fr.m2i.ken.docteur_m2i.dao;

import fr.m2i.ken.docteur_m2i.business.Parametre;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ParametreDao {

    Parametre create(Parametre parametre) throws SQLException;
    Parametre findOneById(Long id) throws SQLException;
    ArrayList<Parametre> findAll() throws SQLException;
}
