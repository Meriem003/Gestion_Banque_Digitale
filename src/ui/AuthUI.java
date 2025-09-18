package ui;
import java.util.Scanner;

public class AuthUI {
    private Scanner scanner = new Scanner(System.in);
    public void afficherMenuPrincipal(){
        int choix = 0;
        do{
            System.out.println("\n===== Banque Digitale =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Quitter");
            System.out.println("\n===========================");
            System.out.print("Choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix){
                case 1:
                    // methode dyal login
                case 2:
                    // methode dyal register
                case 3:
                    System.out.println("Merci utilisé notre banque");
                    break;
                default:
                    System.out.println("choix invalide");
            }
        } while (choix !=3);
    }
}