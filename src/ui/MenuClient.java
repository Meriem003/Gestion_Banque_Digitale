package ui;

import service.BanqueService;
import model.Client;
import java.util.Scanner;

public class MenuClient {
    private BanqueService banqueService;
    private Client client;
    private Scanner scanner = new Scanner (System.in);

    public MenuClient(Client client, BanqueService banqueService) {
        this.client = client;
        this.banqueService = banqueService;
    }

    public void afficherMenuClient(){
        int choix = 0;
        do{
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

            switch (choix){
                case 1:
                    consulterSolde();
                    break;
                case 2:
                    deposer();
                    break;
                case 3 :
                    retrait();
                    break;
                case 4:
                    virement();
                    break;
                case 5 :
                    consulterReleve();
                    break;
                case 6:
                    System.out.println("Merci d'avoir utilisé notre application");
                    break;
                default:
                    System.out.println("choix invalide");
            }
        }while (choix !=6);
    }

    private void consulterSolde(){
        System.out.println("=== VOS COMPTES DISPONIBLES ===");
        for(String numeroCompte : client.getComptes().keySet()){
            System.out.println("• " + numeroCompte);
        }
        System.out.println("==============================");
        
        System.out.print("entrez le numéro de compte : ");
        String numeroCompte = scanner.nextLine();
        double solde = banqueService.consulterSolde(client, numeroCompte);
        System.out.println("solde du compte : "+solde+" DH");
    }

    private void deposer(){
        System.out.println("=== VOS COMPTES DISPONIBLES ===");
        for(String numeroCompte : client.getComptes().keySet()){
            System.out.println("• " + numeroCompte);
        }
        System.out.println("==============================");
        
        System.out.print("entrez le numéro de compte : ");
        String numeroCompte = scanner.nextLine();
        System.out.print("entrez de montant a deposer : ");
        double montant = scanner.nextDouble();
        banqueService.deposer(client, numeroCompte, montant);
    }

    private void retrait(){
        System.out.println("=== VOS COMPTES DISPONIBLES ===");
        for(String numeroCompte : client.getComptes().keySet()){
            System.out.println("• " + numeroCompte);
        }
        System.out.println("==============================");
        
        System.out.print("entrez le numéro de compte : ");
        String numeroCompte = scanner.nextLine();
        System.out.print("entrez le montant à retirer : ");
        double montant = scanner.nextDouble();
        banqueService.retrait(client, numeroCompte, montant);
    }

    private void virement(){
        System.out.print("entrez le numéro de compte source : ");
        String numeroCompteSource = scanner.nextLine();
        System.out.print("entrez le numéro de compte destination : ");
        String numeroCompteDestination = scanner.nextLine();
        System.out.print("entrez le montant à virer : ");
        double montant = scanner.nextDouble();
        banqueService.virement(client, numeroCompteSource, numeroCompteDestination, montant);
    }

    private void consulterReleve(){
        System.out.println("=== VOS COMPTES DISPONIBLES ===");
        for(String numeroCompte : client.getComptes().keySet()){
            System.out.println("• " + numeroCompte);
        }
        System.out.println("==============================");
        
        System.out.print("entrez le numéro de compte : ");
        String numeroCompte = scanner.nextLine();
        banqueService.consulterReleve(client, numeroCompte);
    }

}
