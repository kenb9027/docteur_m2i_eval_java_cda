package fr.m2i.ken.docteur_m2i.service.impl;

import fr.m2i.ken.docteur_m2i.business.Parametre;
import fr.m2i.ken.docteur_m2i.dao.ParametreDao;
import fr.m2i.ken.docteur_m2i.dao.impl.ParametreDaoImpl;
import fr.m2i.ken.docteur_m2i.service.ParametreService;

import java.sql.SQLException;
import java.util.ArrayList;

public class ParametreServiceImpl implements ParametreService {

    private ParametreDao parametreDao = new ParametreDaoImpl();
    @Override
    public Parametre addParametre(String nom) {
        Parametre newParam = new Parametre(nom);
        try {
            return parametreDao.create(newParam);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newParam;
    }

    @Override
    public Parametre getParametreById(Long id) {
        try {
            return parametreDao.findOneById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Parametre> getAllParametres() {
        try {
            return parametreDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
