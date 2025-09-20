package ui;

import java.util.Scanner;
import service.BanqueService;

public class MenuGestionnaire {
    private Scanner scanner = new Scanner(System.in);
    private BanqueService banqueService;

    public MenuGestionnaire(BanqueService banqueService) {
        this.banqueService = banqueService;
    }

    public void afficherMenuGestoinnaire() {
        int choix = 0;
        do {
            System.out.println("===== Menu Gestionnaire =====");
            System.out.println("1. Créer compte client");
            System.out.println("2. Modifier infos client");
            System.out.println("3. Clôturer compte client");
            System.out.println("4. Consulter relevé client");
            System.out.println("5. Quitter");
            System.out.println("==============================");
            System.out.print("Choix : ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("\n===== Enregistrement Nouveau Client =====");
                    scanner.nextLine();
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();

                    System.out.print("Prénom : ");
                    String prenom = scanner.nextLine();

                    System.out.print("Email : ");
                    String email = scanner.nextLine();

                    System.out.print("Mot de passe : ");
                    String motDePasse = scanner.nextLine();

                    System.out.println("\nTypes de comptes disponibles :");
                    System.out.print("Choisissez le type de compte (1 : Courant - 2 : Épargne) : ");
                    int typeChoix = scanner.nextInt();
                    scanner.nextLine();
                    String typeCompte;
                    switch (typeChoix) {
                        case 1:
                            typeCompte = "Compte Courant";
                            break;
                        case 2:
                            typeCompte = "Compte Épargne";
                            break;
                        default:
                            System.out.println("Type de compte invalide !");
                            return;
                    }
                    System.out.print("Confirmer l'enregistrement du client " + nom + " " + prenom + " ? (oui/non) : ");
                    String confirmationCreation = scanner.nextLine();

                    if (confirmationCreation.equalsIgnoreCase("oui")) {
                        model.Client nouveauClient = banqueService.enregistrerClientParGestionnaire(nom, prenom, email,
                                motDePasse, typeCompte);
                        if (nouveauClient != null) {
                            System.out.println("Client enregistré avec succès !");
                        }
                    } else {
                        System.out.println("Enregistrement annulé.");
                    }

                case 2:
                    // modifier
                    break;
                case 3:
                    System.out.println("\n===== Clôture Compte Client =====");
                    scanner.nextLine();
                    System.out.print("Entrez l'email ou l'ID du client : ");
                    String emailOuId = scanner.nextLine();
                    System.out.print("Entrez le numéro du compte à clôturer : ");
                    String numeroCompte = scanner.nextLine();
                    System.out.print("Êtes-vous sûr de vouloir clôturer ce compte ? (oui/non) : ");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equalsIgnoreCase("oui")) {
                        boolean succes = banqueService.cloturerCompte(emailOuId, numeroCompte);
                        if (!succes) {
                            System.out.println("Clôture annulée.");
                        }
                    }
                    break;

                case 4:
                    // consulterReleveClient
                    break;

                case 5:
                    System.out.println("Déconnexion réussie");
                    break;
                default:
                    System.out.println("choix invalide");
            }
        } while (choix != 5);
    }
}
