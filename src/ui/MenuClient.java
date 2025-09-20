package ui;

import service.BanqueService;
import model.Client;
import java.util.Scanner;

public class MenuClient {
    private BanqueService banqueService;
    private Client client;
    private Scanner scanner = new Scanner(System.in);

    public MenuClient(Client client, BanqueService banqueService) {
        this.client = client;
        this.banqueService = banqueService;
    }

    public void afficherMenuClient() {
        int choix = 0;
        do {
            System.out.println("===== Menu Client =====");
            System.out.println("1. Consulter solde");
            System.out.println("2. Dépôt");
            System.out.println("3. Retrait");
            System.out.println("4. Virement");
            System.out.println("5. Consulter relevé");
            System.out.println("6. Quitter");
            System.out.println("=======================");
            System.out.print("Choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("===== Consultation de Solde =====");
                    System.out.println("Client : " + client.getPrenom() + " " + client.getNom());
                    System.out.println("Email : " + client.getEmail());
                    if (!client.getComptes().isEmpty()) {
                        System.out.print("Numéros de comptes : ");
                        for (String numeroCompte : client.getComptes().keySet()) {
                            System.out.print(numeroCompte + " ");
                        }
                    }
                    double monSolde = banqueService.consulterMonSolde(client);
                    System.out.println("Votre solde total : " + monSolde + " DH");
                    System.out.println("==================================");
                    break;

                case 2:
                    System.out.println("\n===== Dépôt d'Argent =====");
                    System.out.print("Numéro de compte : ");
                    String numeroCompte = scanner.nextLine();
                    System.out.print("Montant à déposer (DH) : ");
                    double montant = scanner.nextDouble();
                    scanner.nextLine();
                    boolean succes = banqueService.deposer(client, numeroCompte, montant);
                    if (!succes) {
                        System.out.println("Échec du dépôt.");
                    }
                    System.out.println("===========================\n");
                    break;

                case 3:
                    retrait();
                    break;
                case 4:
                    // virement();
                    break;
                case 5:
                    consulterReleve();
                    break;
                case 6:
                    System.out.println("Merci d'avoir utilisé notre application");
                    break;
                default:
                    System.out.println("choix invalide");
            }
        } while (choix != 6);
    }
    
    private void retrait() {
        System.out.println("\n===== Retrait d'Argent =====");
        System.out.print("Numéro de compte : ");
        String numeroCompte = scanner.nextLine();
        System.out.print("Montant à retirer (DH) : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();
        boolean succes = banqueService.retrait(client, numeroCompte, montant);
        if (!succes) {
            System.out.println("Échec du retrait.");
        }
        System.out.println("============================\n");
    }
    
    private void consulterReleve() {
        System.out.println("\n===== Consultation de Relevé =====");
        System.out.print("Numéro de compte à consulter : ");
        String numeroCompte = scanner.nextLine();
        
        banqueService.consulterReleve(client, numeroCompte);
        System.out.println("===================================\n");
    }

}
