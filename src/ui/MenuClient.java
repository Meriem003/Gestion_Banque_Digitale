package ui;

import java.util.Scanner;

public class MenuClient {
    private Scanner scanner = new Scanner (System.in);
    public void afficherMenuClient(){
        int choix = 0;
        do{
            System.out.println("\n===== Menu Client =====");
            System.out.println("1. Consulter solde");
            System.out.println("2. Dépôt");
            System.out.println("3. Retrait");
            System.out.println("4. Virement");
            System.out.println("5. Consulter relevé");
            System.out.println("6. Déconnexion");
            System.out.println("\n=======================");
            System.out.print("Choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix){
                case 1:
                    // consulter
                case 2:
                    // depos
                case 3 :
                    // retrait
                case 4:
                    // verement
                case 5 :
                    // consulter relevé
                case 6:
                    System.out.println("Merci d'avoir utilisé notre application");
                    break;
                default:
                    System.out.println("choix invalide");
            }
        }while (choix !=6);
    }
}
