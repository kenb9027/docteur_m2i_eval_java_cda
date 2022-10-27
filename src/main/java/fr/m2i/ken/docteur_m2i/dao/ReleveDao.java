package fr.m2i.ken.docteur_m2i.dao;

import fr.m2i.ken.docteur_m2i.business.Releve;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReleveDao {

    Releve create(Releve releve) throws SQLException;
    Releve findOneById(Long id) throws SQLException;

    ArrayList<Releve> sortByDateDesc() throws SQLException;
    boolean delete(Long id) throws SQLException;


}
