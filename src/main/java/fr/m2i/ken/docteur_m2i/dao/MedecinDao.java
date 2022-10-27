package fr.m2i.ken.docteur_m2i.dao;

import fr.m2i.ken.docteur_m2i.business.Medecin;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MedecinDao {

    Medecin create(Medecin medecin) throws SQLException;
    Medecin findOneById(Long id) throws SQLException;
    ArrayList<Medecin> findAll() throws SQLException;

}
