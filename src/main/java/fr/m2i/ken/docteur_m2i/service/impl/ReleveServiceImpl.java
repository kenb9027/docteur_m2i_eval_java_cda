package fr.m2i.ken.docteur_m2i.service.impl;

import fr.m2i.ken.docteur_m2i.business.Medecin;
import fr.m2i.ken.docteur_m2i.business.Parametre;
import fr.m2i.ken.docteur_m2i.business.Patient;
import fr.m2i.ken.docteur_m2i.business.Releve;
import fr.m2i.ken.docteur_m2i.dao.ReleveDao;
import fr.m2i.ken.docteur_m2i.dao.impl.ReleveDaoImpl;
import fr.m2i.ken.docteur_m2i.service.ReleveService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReleveServiceImpl implements ReleveService {

    ReleveDao releveDao = new ReleveDaoImpl();

    @Override
    public Releve addReleve(LocalDateTime dateCreation, float valeur, Parametre parametre, Patient patient, Medecin medecin) {
        Releve newReleve = new Releve(dateCreation, valeur, parametre, patient, medecin);
        try {
            return releveDao.create(newReleve);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newReleve;
    }

    @Override
    public Releve getReleveById(Long id) {
        try {
            return releveDao.findOneById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Releve> sortByDateDesc() {
        try {
            return releveDao.sortByDateDesc();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            return releveDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
