package ui;
import java.util.Scanner;
import model.Client;
import model.Gestionnaire;
import service.BanqueService;

public class AuthUI {
    private BanqueService banqueService = new BanqueService();
    private Scanner scanner = new Scanner(System.in);
    public void afficherMenuPrincipal(){
        int choix = 0;
        do{
            System.out.println("===== Banque Digitale =====");
            System.out.println("1. Login");
            System.out.println("2. Register Client");
            System.out.println("3. Quitter");
            System.out.println("===========================");
            System.out.print("Choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix){
                case 1:
                    loginGestionnaire();
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
                        
                        System.out.println("\n=== INFORMATIONS DU COMPTE ===");
                        System.out.println("Nom : " + client.getNom());
                        System.out.println("Prénom : " + client.getPrenom());
                        System.out.println("Email : " + client.getEmail());
                        String numeroCompte = client.getComptes().keySet().iterator().next();
                        System.out.println("Numéro de compte : " + numeroCompte);
                        System.out.println("================================\n");
                        
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

    private void loginGestionnaire() {
        System.out.println("\n=== LOGIN GESTIONNAIRE ===");
        System.out.println("Gestionnaire par défaut :");
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe :");
        String motDePasse = scanner.nextLine();
        System.out.println("========================");
        
        Gestionnaire gestionnaire = banqueService.loginGestionnaire(email, motDePasse);
        if (gestionnaire != null) {
            System.out.println("Connexion réussie! Bienvenue ");
            MenuGestionnaire menuGestionnaire = new MenuGestionnaire();
            menuGestionnaire.afficherMenuGestoinnaire();
        } else {
            System.out.println("Email ou mot de passe incorrect");
        }
    }


}