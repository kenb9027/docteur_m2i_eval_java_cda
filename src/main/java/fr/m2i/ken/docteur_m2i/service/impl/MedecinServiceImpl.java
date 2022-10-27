package fr.m2i.ken.docteur_m2i.service.impl;

import fr.m2i.ken.docteur_m2i.business.Medecin;
import fr.m2i.ken.docteur_m2i.dao.MedecinDao;
import fr.m2i.ken.docteur_m2i.dao.impl.MedecinDaoImpl;
import fr.m2i.ken.docteur_m2i.service.MedecinService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;

public class MedecinServiceImpl implements MedecinService {

    private MedecinDao medecinDao = new MedecinDaoImpl();

    @Override
    public Medecin addMedecin(String nom, String prenom, LocalDateTime dateEmbauche) {
        Medecin newMedecin = new Medecin(nom , prenom, dateEmbauche);
        try {
            return medecinDao.create(newMedecin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newMedecin;
    }

    @Override
    public Medecin getMedecinById(Long id) {
        try {
            return medecinDao.findOneById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Medecin> getAllMedecins() {
        try {
            return medecinDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
