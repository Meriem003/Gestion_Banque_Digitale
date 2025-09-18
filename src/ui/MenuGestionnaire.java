package ui;

import java.util.Scanner;

public class MenuGestionnaire {
    private Scanner scanner = new Scanner(System.in);

    public void afficherMenuGestoinnaire(){
    int choix = 0;
    do{
        System.out.println("\n===== Menu Gestionnaire =====");
        System.out.println("1. Créer compte client");
        System.out.println("2. Modifier infos client");
        System.out.println("3. Clôturer compte client");
        System.out.println("4. Consulter relevé client");
        System.out.println("5. Déconnexion");
        System.out.println("\n==============================");
        System.out.print("Choix : ");
        choix = scanner.nextInt();

        switch (choix){
            case 1:
                // creer compte
                break;
            case 2:
                // modifier
                break;
            case 3:
                // cloture compte
                break;
            case 4:
                // consulter
                break;
            case 5:
                System.out.println("Déconnexion réussie");
                break;
            default:
                System.out.println("choix invalide");
        }
    }while (choix != 5);
  }
}
