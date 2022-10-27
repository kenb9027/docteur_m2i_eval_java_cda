package fr.m2i.ken.docteur_m2i;

import fr.m2i.ken.docteur_m2i.service.MedecinService;
import fr.m2i.ken.docteur_m2i.service.ParametreService;
import fr.m2i.ken.docteur_m2i.service.PatientService;
import fr.m2i.ken.docteur_m2i.service.ReleveService;
import fr.m2i.ken.docteur_m2i.service.impl.MedecinServiceImpl;
import fr.m2i.ken.docteur_m2i.service.impl.ParametreServiceImpl;
import fr.m2i.ken.docteur_m2i.service.impl.PatientServiceImpl;
import fr.m2i.ken.docteur_m2i.service.impl.ReleveServiceImpl;

import java.util.Scanner;

public class App {

    private static final PatientService patientService = new PatientServiceImpl();
    private static final MedecinService medecinService = new MedecinServiceImpl();
    public static final ParametreService parametreService = new ParametreServiceImpl();
    public static final ReleveService releveService = new ReleveServiceImpl();
    public static void main(String[] args) {

        System.out.println("Hello there ");
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
                    System.out.println("Ajouter un element");
                    System.out.println();
                    break;
                case 2:
                    System.out.println("modifier patient");
                    System.out.println();
                    break;
                case 3:
                    System.out.println("voir patient selon age");
                    System.out.println();
                    break;
                case 4:
                    System.out.println("voir relevés triés");
                    System.out.println();
                    break;
                case 5:
                    System.out.println("voir medecins");
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
}