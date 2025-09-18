package ui;
import java.util.Scanner;
import model.Client;
import service.BanqueService;

public class AuthUI {
    private BanqueService banqueService = new BanqueService();
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
                    break;
                case 2:
                    System.out.println("\n=== REGISTER CLIENT ===");
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String motDePasse = scanner.nextLine();

                    Client client = banqueService.creerClient(nom, prenom, email, motDePasse);

                    if(client != null){
                        System.out.println("Inscription réussie !");
                        MenuClient menuClient = new MenuClient(client, banqueService);
                        menuClient.afficherMenuClient();
                    }
                    break;
                case 3:
                    System.out.println("Merci utilisé notre banque");
                    break;
                default:
                    System.out.println("choix invalide");
            }
        } while (choix !=3);
    }
}