package fr.m2i.ken.docteur_m2i.dao;

public class Queries {

    // MEDECIN
    public static final String MEDECIN_CREATE = "INSERT INTO `personne`(`nom`, `prenom`, `isMedecin`, `date`) VALUES ( ?, ?, 1, ?)";
    public static final String MEDECIN_FIND_ALL = "SELECT * FROM `personne` WHERE isMedecin=1";
    public static final String MEDECIN_FIND_ONE_BY_ID = "SELECT * FROM `personne` WHERE isMedecin= 1 AND id= ? ";

    // PATIENT
    public static final String PATIENT_CREATE = "INSERT INTO `personne`(`nom`, `prenom`, `isMedecin`, `date`) VALUES ( ?, ?, 0, ?)";
    public static final String PATIENT_FIND_ALL = "SELECT * FROM `personne` WHERE isMedecin=0";
    public static final String PATIENT_FIND_ONE_BY_ID = "SELECT * FROM `personne` WHERE isMedecin= 0 AND id= ? ";
    public static final String PATIENT_UPDATE = "UPDATE `personne` SET `nom`=?,`prenom`=? WHERE id = ?";

    // RELEVE
    public static final String RELEVE_CREATE = "INSERT INTO `releve`(`dateCreation`, `valeur`, `patientId`, `medecinId`, `parametreId`) VALUES ( ?, ?, ?, ?, ? )";
    public static final String RELEVE_FIND_ONE_BY_ID = "SELECT * FROM `releve` WHERE id= ? ";
    public static final String RELEVE_SORT_BY_DATE_DESC = "SELECT * FROM `releve` ORDER BY dateCreation DESC";
    public static final String RELEVE_DELETE_BY_ID = "DELETE FROM `releve` WHERE id= ?";

    // PARAMETRE
    public static final String PARAMETRE_CREATE = "INSERT INTO `parametre`(`nom`) VALUES ( ? )";
    public static final String PARAMETRE_FIND_ALL = "SELECT * FROM `parametre`";
    public static final String PARAMETRE_FIND_ONE_BY_ID = "SELECT * FROM `parametre` WHERE id= ? ";
}
