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
        int choiceInt = 1;
        do {
            displayMenu();
            String choice = sc1.next();
            // on redemande tant que ce n'est pas un chiffre entre 1 et 8  TODO:make limit dynamic in loop
            while (true){
                try {
                    choiceInt = Integer.parseInt(choice);
                    if (choiceInt > 8 || choiceInt < 1){
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
                    System.out.println("voir patient selon age");
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
                    System.out.println("ajouter relevé");
                    System.out.println();
                    break;
                case 7:
                    System.out.println("supprimer relevé");
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

        // nom et prénom
        System.out.print("Nom:");
        String nom = scanner.next();

        System.out.print("Prénom:");
        String prenom = scanner.next();

        System.out.print("Date de naissance :");
        LocalDateTime date = askForDate() ;

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

        Long idPatient = 0L;
        ArrayList<Long> patientIdList = new ArrayList<>();
        for (Patient patient :
                patientService.getAllPatients() ) {
            patientIdList.add(patient.getId());
        }
        boolean isPatientExist = patientIdList.contains(idPatient);
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

        // nom et prénom
        System.out.println("Nom:" + "(anciennement " + oldPatient.getNom() + ")");
        String nom = scanner.next();
        System.out.println("Prénom:" + "(anciennement " + oldPatient.getNom() + ")");
        String prenom = scanner.next();

//        System.out.print("Date de naissance :");
//        LocalDateTime date = askForDate() ;

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
     * Utils : Ask for a date
     * @return
     */
    public static LocalDateTime askForDate() {
        Scanner dateScanner = new Scanner(System.in);
        LocalDateTime day = LocalDateTime.now();

        boolean dateOk = false;
        while (!dateOk){
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
        LocalDateTime localDateTime1 =  LocalDateTime.of(2020 , 3, 9, 0 , 0) ;
        LocalDateTime localDateTime2 =  LocalDateTime.of(2022 , 10, 9, 0 , 0) ;
        LocalDateTime localDateTime3 =  LocalDateTime.of(1990 , 4, 1, 0 , 0) ;
        LocalDateTime localDateTime4 =  LocalDateTime.of(1990 , 2, 3, 0 , 0) ;
        LocalDateTime localDateTime5 =  LocalDateTime.of(1990 , 11, 30, 0 , 0) ;

        // 2 medecins
        Medecin medecin1 = medecinService.addMedecin("Valjean", "Jean", localDateTime1);
        Medecin medecin2 = medecinService.addMedecin("Granger", "Hermione", localDateTime2);

        // 2 patients
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