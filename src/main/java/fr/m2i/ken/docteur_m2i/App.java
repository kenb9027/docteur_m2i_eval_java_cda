package fr.m2i.ken.docteur_m2i;

import fr.m2i.ken.docteur_m2i.business.Medecin;
import fr.m2i.ken.docteur_m2i.business.Parametre;
import fr.m2i.ken.docteur_m2i.business.Patient;
import fr.m2i.ken.docteur_m2i.business.Releve;
import fr.m2i.ken.docteur_m2i.service.MedecinService;
import fr.m2i.ken.docteur_m2i.service.ParametreService;
import fr.m2i.ken.docteur_m2i.service.PatientService;
import fr.m2i.ken.docteur_m2i.service.ReleveService;
import fr.m2i.ken.docteur_m2i.service.impl.MedecinServiceImpl;
import fr.m2i.ken.docteur_m2i.service.impl.ParametreServiceImpl;
import fr.m2i.ken.docteur_m2i.service.impl.PatientServiceImpl;
import fr.m2i.ken.docteur_m2i.service.impl.ReleveServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class App {

    private static final PatientService patientService = new PatientServiceImpl();
    private static final MedecinService medecinService = new MedecinServiceImpl();
    public static final ParametreService parametreService = new ParametreServiceImpl();
    public static final ReleveService releveService = new ReleveServiceImpl();
    public static void main(String[] args) {

//        Chargement des fixtures
//        makeFixtures();

        System.out.println("Hello there ");
        System.out.println("🌡 ❤ 🌬");
        System.out.println();
        System.out.println("Bienvenue sur Docteur M2i !");
        System.out.println();
        Scanner sc1 = new Scanner(System.in);
        //initialisation du choix pour éviter les erreurs
        int choiceInt = 1;
        do {
            //affichage du menu
            displayMenu();
            String choice = sc1.next();
            // on redemande tant que ce n'est pas un chiffre ET qu'il soit compris entre 1 et 8
            while (true){
                try {
                    //on tente de caster le chiffre reçu en Integer : si cela échou , ce n'est pas un nombre entier
                    choiceInt = Integer.parseInt(choice);
                    if (choiceInt > 8 || choiceInt < 1){
                        //on vérifie que le chiffre est entre 1 et 8 : si non , on reprends la boucle
                        System.err.println("Entrez un nombre entre 1 et 8 svp! ");
                        choice = sc1.next(); // clear scanner wrong input
                        continue; // continues to loop if exception is found
                    }
                    break;

                } catch (NumberFormatException e) {
                    //throw new RuntimeException(e);
                    System.err.println("Entrez un nombre svp! " + e.getMessage());
                    choice = sc1.next(); // clear scanner wrong input
                    continue; // continues to loop if exception is found
                }
            }
            switch (choiceInt){
                case 1:
                    addPatient();
                    System.out.println();
                    break;
                case 2:
                    updatePatient();
                    System.out.println();
                    break;
                case 3:
                    getPatientListByAge();
                    System.out.println();
                    break;
                case 4:
                    displayRelevesSortByDate();
                    System.out.println();
                    break;
                case 5:
                    displayAllMedecins();
                    System.out.println();
                    break;
                case 6:
                    addReleve();
                    System.out.println();
                    break;
                case 7:
                    deleteReleve();
                    System.out.println();
                    break;
                case 8:
                    System.out.println();
                    System.out.println("Au revoir ! ");
                    System.out.println();
                    break;
                default:
                    break;

            }

        }
        while (choiceInt != 8);
        System.out.println("FIN DU PROGRAMME.");
        System.out.println("🌡 ❤ 🌬");
    }


    /**
     * Display Menu
     */
    public static void displayMenu() {
        System.out.println("MENU");
        System.out.println("1. Ajouter un Patient");
        System.out.println("2. Modifier un patient");
        System.out.println("3. Voir tous les patients ayant un âge donné");
        System.out.println("4. Voir tous les relevés triés du plus récent au plus ancien");
        System.out.println("5. Voir tout les Medecins");
        System.out.println("6. Ajouter un relevé");
        System.out.println("7. Supprimer un relevé");
        System.out.println("8. Quitter");
        System.out.print("Entrez votre choix : ");
    }

    /**
     * 1. Ajouter un patient
     */
    public static void addPatient()
    {
        System.out.println("Ajouter un patient:");
        Scanner scanner = new Scanner(System.in);

        //demande des nom et prénom
        System.out.print("Nom:");
        String nom = scanner.next();

        System.out.print("Prénom:");
        String prenom = scanner.next();

        // demande de la date de naissance
        System.out.println("Date de naissance :");
        LocalDateTime date = askForDate() ;

        //enregistrement
        Patient patient =  patientService.addPatient(nom, prenom, date);
        System.out.println("Patient ajouté :");
        System.out.println(patient);

    }

    /**
     * 2. Modifier un patient
     */
    public  static  void updatePatient(){
        System.out.println("Modifier un patient:");
        Scanner scanner = new Scanner(System.in);

        //on vérifie tout d'abord que le patient existe
        Long idPatient = 0L;
        //on crée une liste contenant les ID des patients existants
        ArrayList<Long> patientIdList = new ArrayList<>();
        for (Patient patient :
                patientService.getAllPatients() ) {
            patientIdList.add(patient.getId());
        }
        boolean isPatientExist = patientIdList.contains(idPatient);
        //tant que le chiffre donnée ne correspond pas à l'ID d'un patient existant , on reprends la boulcle
        while (!isPatientExist)
        {
            System.out.println("Quels patient souhaitez vous modifier ?");
            for (Patient patient :
                    patientService.getAllPatients() ) {
                System.out.println(patient);
            }
            System.out.println("Id :");
            idPatient = scanner.nextLong();
            isPatientExist = patientIdList.contains(idPatient);
            if (!isPatientExist)
            {
                System.out.println("Ce patient n'éxiste pas.");
            }
        }
        Patient oldPatient = patientService.getPatientById(idPatient);

        //changement nom et prénom
        System.out.println("Nom:" + "(anciennement " + oldPatient.getNom() + ")");
        String nom = scanner.next();
        System.out.println("Prénom:" + "(anciennement " + oldPatient.getPrenom() + ")");
        String prenom = scanner.next();


        //enregistrement en BDD en assignant au 'nouveau' patient l'id du patient séléctionné
        Patient patient = new Patient(nom, prenom, oldPatient.getDateNaissance());
        patient.setId(idPatient);
        Patient updatedPatient =  patientService.updatePatient(patient);
        System.out.println("Patient modifié :");
        System.out.println(updatedPatient);
    }

    /**
     * 3. Voir les Relevés triés par date desc
     */
    public static void displayRelevesSortByDate()
    {
        System.out.println("Liste des Relevés triés par date desc. :");
        for (Releve releve :
                releveService.sortByDateDesc() ) {
            System.out.println(releve);
        }
    }

    /**
     * 4. Voir les patient selon un age donné
     */
    public static void getPatientListByAge()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel âge recherchez vous ?");
        int age = 0;
        String str = scanner.next();

        //on vérifie que l'age donné est bien un chiffre
        while (true){
            try {

                age = Integer.parseInt(str);
                if (age < 1){
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                //throw new RuntimeException(e);
                System.err.println("Entrez un nombre valide svp! " + e.getMessage());
                str = scanner.next(); // clear scanner wrong input
                continue; // continues to loop if exception is found
            }
        }

        //on compare l'age de chaque patient avec l'âge donné
        LocalDateTime today = LocalDateTime.now();
        ArrayList<Patient> patientsListByAge = new ArrayList<>();
        for (Patient patient :
                patientService.getAllPatients()) {
            int diff = today.compareTo(patient.getDateNaissance());
            if (diff == age ){
                patientsListByAge.add(patient);
            }
        }

        System.out.println("Liste des patients agés de " + age + " ans :");
        for (Patient patient :
                patientsListByAge ) {
            System.out.println(patient);
        }

    }

    /**
     * 5. Voir tous les medecins
     */
    public static void displayAllMedecins (){
        System.out.println("Liste des Médecins :");
        for (Medecin medecin :
                medecinService.getAllMedecins() ) {
            System.out.println(medecin);
        }
    }

    /**
     * 6. Ajouter un relevé
     */
    public static void addReleve()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ajouter un Relevé :");

        //demande la date et l'heure du relevé
        LocalDateTime date = askForDateTime();

        //demande le parametre
        Long idParam = 0L;
        ArrayList<Long> paramIdList = new ArrayList<>();
        // on vérifie que le parametre xhoisi existe en le comparant a une list d'id créée
        for (Parametre parametre :
                parametreService.getAllParametres() ) {
            paramIdList.add(parametre.getId());
        }
        boolean isParamExist = paramIdList.contains(idParam);
        while (!isParamExist)
        {
            System.out.println("Quels paramètre souhaitez vous choisir ?");
            for (Parametre parametre :
                    parametreService.getAllParametres() ) {
                System.out.println(parametre);
            }
            System.out.println("Id :");
            idParam = scanner.nextLong();
            isParamExist = paramIdList.contains(idParam);
            if (!isParamExist)
            {
                System.out.println("Ce paramètre n'éxiste pas.");
            }
        }
        Parametre parametre = parametreService.getParametreById(idParam);

        //valeur
        System.out.println("Entrez la valeur de ce paramètre : ");
        float valeur = 0F;
        String value = scanner.next();
        //on vérifie que la valeur est bien un nombre FLOAT
        while (true){
            try {
                valeur = Float.parseFloat(value);
                break;
            } catch (NumberFormatException e) {
                //throw new RuntimeException(e);
                System.err.println("Entrez un nombre svp! " + e.getMessage());
                value = scanner.next(); // clear scanner wrong input
                continue; // continues to loop if exception is found
            }
        }

        //patient
        Long idPatient = 0L;
        ArrayList<Long> patientIdList = new ArrayList<>();
        // on vérifie que le patient choisi existe en le comparant a une list d'id créée
        for (Patient patient :
                patientService.getAllPatients() ) {
            patientIdList.add(patient.getId());
        }
        boolean isPatientExist = patientIdList.contains(idPatient);
        while (!isPatientExist)
        {
            System.out.println("Quels patient souhaitez vous choisir ?");
            for (Patient patient :
                    patientService.getAllPatients() ) {
                System.out.println(patient);
            }
            System.out.println("Id :");
            idPatient = scanner.nextLong();
            isPatientExist = patientIdList.contains(idPatient);
            if (!isPatientExist)
            {
                System.out.println("Ce patient n'éxiste pas.");
            }
        }
        Patient patient = patientService.getPatientById(idPatient);

        //medecin
        Long idMedecin = 0L;
        ArrayList<Long> medecinIdList = new ArrayList<>();
        // on vérifie que le medecin choisi existe en le comparant a une list d'id créée
        for (Medecin medecin :
                medecinService.getAllMedecins() ) {
            medecinIdList.add(medecin.getId());
        }
        boolean isMedecinexist = medecinIdList.contains(idMedecin);
        while (!isMedecinexist)
        {
            System.out.println("Quels medecin souhaitez vous choisir ?");
            for (Medecin medecin :
                    medecinService.getAllMedecins() ) {
                System.out.println(medecin);
            }
            System.out.println("Id :");
            idMedecin = scanner.nextLong();
            isMedecinexist = medecinIdList.contains(idMedecin);
            if (!isMedecinexist)
            {
                System.out.println("Ce medecin n'éxiste pas.");
            }
        }
        Medecin medecin = medecinService.getMedecinById(idMedecin);


        // create Relevé
        Releve newReleve = releveService.addReleve(
                date,
                valeur,
                parametre,
                patient,
                medecin

        );
        System.out.println("Relevé ajouté :");
        System.out.println(newReleve);
    }

    /**
     * 7. Supprimer un relevé
     */
    public static void deleteReleve()
    {
        System.out.println("Supprimer un relevé :");
        Scanner scanner = new Scanner(System.in);

        Long idReleve = 0L;
        ArrayList<Long> releveIdList = new ArrayList<>();
        for (Releve releve :
                releveService.sortByDateDesc() ) {
            releveIdList.add(releve.getId());
        }
        boolean isReleveExist = releveIdList.contains(idReleve);
        while (!isReleveExist)
        {
            System.out.println("Quels relevé souhaitez vous supprimer ?");
            for (Releve releve :
                    releveService.sortByDateDesc() ) {
                System.out.println(releve);
            }
            System.out.println("Id :");
            idReleve = scanner.nextLong();
            isReleveExist = releveIdList.contains(idReleve);
            if (!isReleveExist)
            {
                System.out.println("Ce relevé n'éxiste pas.");
            }


        }
        boolean bool = releveService.deleteById(idReleve);
        if (!bool){
            System.out.print("Relevé #"+ idReleve +" supprimé !");
        }
        else {
            System.out.print("Erreur : Relevé #"+ idReleve +" NON supprimé !");

        }
    }

    /**
     * Utils : Ask for a dateTime
     */
    public static LocalDateTime askForDateTime() {
        Scanner dateScanner = new Scanner(System.in);
        LocalDateTime day = LocalDateTime.now();
        String[] dateStringList = new String[0] ;
        String[] heureStringList = new String[0] ;
        //date
        // tant que la date rentrée ne correspond pas au pattern "dd/MM/yyyy" , on redemande "
        boolean dateOk = false;
        while (!dateOk){
            System.out.println("Entrez une date :");
            System.out.println("( au format dd-MM-yyyy )");
            String dateString = dateScanner.next();

            if (dateString.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
                try {
                    //on sépare la chaine recu en 3 (jour , mois , année)
                    dateStringList = dateString.split("/");
                    dateOk = true;
                    break;

                } catch (Exception e) {
                    // throw new RuntimeException(e);
                    System.out.println(dateString);
                    System.out.println("Date invalide.");
                }
            }
            System.out.println("Date invalide.");
        }
        //heure
        // tant que la date rentrée ne correspond pas au pattern "HH:mm" , on redemande "
        boolean heureOk = false;
        while (!heureOk){
            System.out.println("Entrez un horaire :");
            System.out.println("( au format HH:mm )");
            String heureString = dateScanner.next();

            if (heureString.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
                try {
                    //on sépare la chaine reçu en 2 (heure , minute)
                    heureStringList = heureString.split(":");
                    heureOk = true;
                    break;

                } catch (Exception e) {
                    // throw new RuntimeException(e);
                    System.out.println(heureString);
                    System.out.println("Heure invalide.");
                }
            }
            System.out.println("Heure invalide.");
        }

        //on crée une nouvelle LocalDateTime avec les données récupérées
        day = LocalDateTime.of(
                Integer.parseInt(dateStringList[2]),
                Integer.parseInt(dateStringList[1]),
                Integer.parseInt(dateStringList[0]),
                Integer.parseInt(heureStringList[0]) ,
                Integer.parseInt(heureStringList[1]) );

        return day;
    }

    /**
     * Utils : Ask for a date
     * @return
     */
    public static LocalDateTime askForDate() {
        Scanner dateScanner = new Scanner(System.in);
        LocalDateTime day = LocalDateTime.now();

        boolean dateOk = false;
        while (!dateOk){
            System.out.println("Entrez une date :");
            System.out.println("( au format dd-MM-yyyy )");
            String dateString = dateScanner.next();

            if (dateString.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
                try {
                    String[] dateStringList = dateString.split("/");
                    day = LocalDateTime.of(Integer.parseInt(dateStringList[2]), Integer.parseInt(dateStringList[1]), Integer.parseInt(dateStringList[0]), 0 , 0 );
                    dateOk = true;
                    break;

                } catch (Exception e) {
                    // throw new RuntimeException(e);
                    System.out.println(dateString);
                    System.out.println("Date invalide.");
                }
            }
            System.out.println("Date invalide.");
        }
        return day;
    }


    /**
     * Fixtures : creer des medecins, patients et parametres
     */
    public static void makeFixtures()
    {
        // 4 localdatetime pour Naissance et Embauche
        LocalDateTime localDateTime1 =  LocalDateTime.of(1999 , 3, 9, 0 , 0) ;
        LocalDateTime localDateTime2 =  LocalDateTime.of(2022 , 10, 9, 0 , 0) ;
        LocalDateTime localDateTime3 =  LocalDateTime.of(1990 , 4, 1, 0 , 0) ;
        LocalDateTime localDateTime4 =  LocalDateTime.of(1990 , 2, 3, 0 , 0) ;
        LocalDateTime localDateTime5 =  LocalDateTime.of(1990 , 11, 30, 0 , 0) ;

        // 2 medecins
        Medecin medecin1 = medecinService.addMedecin("Valjean", "Jean", localDateTime1);
        Medecin medecin2 = medecinService.addMedecin("Granger", "Hermione", localDateTime2);

        // 4 patients
        Patient patient1 =  patientService.addPatient("Sacquet" , "Frodon" , localDateTime3 );
        Patient patient2 =  patientService.addPatient("Gamegie" , "Sam" , localDateTime4 );
        Patient patient3 =  patientService.addPatient("Touque" , "Pippin" , localDateTime5 );
        Patient patient4 =  patientService.addPatient("Brandebouc" , "Meri" , localDateTime5 );

        // 3 parametres
        Parametre param1 = parametreService.addParametre("Température ");
        Parametre param2 = parametreService.addParametre("Saturation O² ");
        Parametre param3 = parametreService.addParametre("Fréquence cardiaque ");

        // 5 relevés
        releveService.addReleve(
                LocalDateTime.now().minusDays(2).minusHours(15),
                37.5F,
                param1 ,
                patient1,
                medecin1
        );
        releveService.addReleve(
                LocalDateTime.now().minusDays(8).minusMinutes(45),
                40.5F,
                param1 ,
                patient2,
                medecin1
        );
        releveService.addReleve(
                LocalDateTime.now().minusDays(6).minusMinutes(2),
                92F,
                param2 ,
                patient3,
                medecin1
        );
        releveService.addReleve(
                LocalDateTime.now().minusDays(2).minusMinutes(15),
                98F,
                param2 ,
                patient4,
                medecin2
        );
        releveService.addReleve(
                LocalDateTime.now().minusDays(2),
                89,
                param3 ,
                patient4,
                medecin2
        );

    }

}