package ui;
import java.util.Scanner;
import model.Client;
import model.Gestionnaire;
import model.Personne;
import service.BanqueService;

public class AuthUI {
    private BanqueService banqueService = new BanqueService();
    private Scanner scanner = new Scanner(System.in);
    
    public void afficherMenuPrincipal(){
        int choix = 0;
        do{
            System.out.println("===== Banque Digitale =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Quitter");
            System.out.println("===========================");
            System.out.print("Choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix){
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Merci utilisé notre banque");
                    break;
                default:
                    System.out.println("choix invalide");
            }
        } while (choix !=3);
    }
    
    private void login() {
        System.out.println("\n===== LOGIN =====");
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();
        
        Personne personne = banqueService.login(email, motDePasse);
        
        if (personne != null) {
            System.out.println("Connexion réussie !");
            if (personne instanceof Gestionnaire) {
                MenuGestionnaire menuGestionnaire = new MenuGestionnaire(banqueService);
                menuGestionnaire.afficherMenuGestoinnaire();
            } else if (personne instanceof Client) {
                Client client = (Client) personne;
                MenuClient menuClient = new MenuClient(client, banqueService);
                menuClient.afficherMenuClient();
            }
        } else {
            System.out.println("Erreur : Email ou mot de passe incorrect !");
        }
    }
    
    private void register() {
        System.out.println("\n===== REGISTER =====");
        System.out.print("Nom : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();
        Client nouveauClient = banqueService.register(nom, prenom, email, motDePasse);
        
        if (nouveauClient != null) {
            System.out.println("Enregistrement réussi !");
            System.out.println("Votre ID Client : " + nouveauClient.getIdClient());
            MenuClient menuClient = new MenuClient(nouveauClient, banqueService);
            menuClient.afficherMenuClient();
        } else {
            System.out.println("Erreur : Cet email est déjà utilisé !");
        }
    }
}