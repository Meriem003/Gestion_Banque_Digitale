package service;

import model.Client;
import model.Compte;
import model.Gestionnaire;
import model.Transacion;
import java.util.ArrayList;

public class BanqueService {

    private ArrayList<Client> clients = new ArrayList<>();
    private Gestionnaire gestionnaireDefaut;



    public BanqueService() {
        this.gestionnaireDefaut = new Gestionnaire("GEST001", "Admin", "Banque", "admin@banque.com", "admin123");
    }
    public Gestionnaire getGestionnaireDefaut() {
        return gestionnaireDefaut;
    }
    public Gestionnaire loginGestionnaire(String email, String motDePasse) {
        if (gestionnaireDefaut.getEmail().equals(email) && gestionnaireDefaut.getMotDePasse().equals(motDePasse)) {
            return gestionnaireDefaut;
        }
        return null;
    }




    public Client creerClient(String nom, String prenom, String email, String motDepasse){

        String idClient = "id"+(clients.size()+1);
        Client client = new Client(nom, prenom, email, motDepasse);
        client.setIdClient(idClient);

        String numeroCompte = "ACC"+(clients.size())+"322";
        Compte compteCourant = new Compte(numeroCompte, "courant");

        client.getComptes().put(numeroCompte,compteCourant);
        clients.add(client);

        System.out.println("Compte créé : " + numeroCompte);

        return client;
    }






    public double consulterSolde(Client client, String numeroCompte){
        Compte compte = client.getComptes().get(numeroCompte);
        if(compte != null){
            return compte.getSolde();
        }else {
            System.out.println(" compte inexistant ");
            return 0;
        }
    }



    public void deposer(Client client, String numeroCompte, double montant){
        if(montant <= 0){
            System.out.println("Le montant doit être positif");
            return;
        }
        Compte compte = client.getComptes().get(numeroCompte);
        if(compte != null){
            compte.setSolde(compte.getSolde()+montant);
            String idTransaction = "TXN" + System.currentTimeMillis() + "DEP";
            Transacion transaction = new Transacion(idTransaction, "DEPOT", montant, null, compte);
            compte.getHistoriquetransacions().add(transaction);
            System.out.println("Dépôt réussi. Nouveau solde: " + compte.getSolde() + " DH");
        }else{
            System.out.println("Compte inexistant");
        }
    }



    public void retrait(Client client, String numeroCompte, double montant){
        if(montant <= 0){
            System.out.println("Le montant doit être positif");
            return;
        }
        Compte compte = client.getComptes().get(numeroCompte);
        if(compte != null){
            if(compte.getSolde() >= montant){
                compte.setSolde(compte.getSolde() - montant);
                String idTransaction = "TXN" + System.currentTimeMillis() + "RET";
                Transacion transaction = new Transacion(idTransaction, "RETRAIT", montant, compte, null);
                compte.getHistoriquetransacions().add(transaction);
                System.out.println("Retrait réussi. Nouveau solde: " + compte.getSolde() + " DH");
            } else {
                System.out.println("Solde insuffisant. Solde actuel: " + compte.getSolde());
            }
        } else {
            System.out.println("Compte inexistant");
        }
    }



    public void virement(Client clientSource, String numeroCompteSource, String numeroCompteDestination, double montant){
        if(montant <= 0){
            System.out.println("Le montant doit être positif");
            return;
        }
        Compte compteSource = clientSource.getComptes().get(numeroCompteSource);
        if(compteSource == null){
            System.out.println("Compte source inexistant");
            return;
        }
        if(compteSource.getSolde() < montant){
            System.out.println("Solde insuffisant. Solde actuel: " + compteSource.getSolde());
            return;
        }
        Compte compteDestination = null;
        
        for(Client client : clients){
            if(client.getComptes().containsKey(numeroCompteDestination)){
                compteDestination = client.getComptes().get(numeroCompteDestination);
                break;
            }
        }
        if(compteDestination == null){
            System.out.println("Compte destination inexistant");
            return;
        }
        compteSource.setSolde(compteSource.getSolde() - montant);
        compteDestination.setSolde(compteDestination.getSolde() + montant);
        
        String idTransactionDebit = "TXN" + System.currentTimeMillis() + "D";
        String idTransactionCredit = "TXN" + System.currentTimeMillis() + "C";
        Transacion transactionDebit = new Transacion(idTransactionDebit, "VIREMENT_DEBIT", montant, compteSource, compteDestination);
        compteSource.getHistoriquetransacions().add(transactionDebit);
        Transacion transactionCredit = new Transacion(idTransactionCredit, "VIREMENT_CREDIT", montant, compteSource, compteDestination);
        compteDestination.getHistoriquetransacions().add(transactionCredit);
        System.out.println("Virement réussi!");
        System.out.println("Montant transféré: " + montant + " DH");
    }

    

    public void consulterReleve(Client client, String numeroCompte) {
        Compte compte = client.getComptes().get(numeroCompte);
        if(compte != null) {
            System.out.println("\n=== RELEVÉ DE COMPTE " + numeroCompte + " ===");
            System.out.println("Solde actuel: " + compte.getSolde() + " DH");
            System.out.println("\n=== HISTORIQUE DES TRANSACTIONS ===");
            
            if(compte.getHistoriquetransacions().isEmpty()) {
                System.out.println("Aucune transaction effectuée");
            } else {
                for(Transacion transaction : compte.getHistoriquetransacions()) {
                    System.out.println("─────────────────────────────────");
                    System.out.println("ID: " + transaction.getIdTransaction());
                    System.out.println("Type: " + transaction.getType());
                    System.out.println("Montant: " + transaction.getMontant() + " DH");
                    
                    if(transaction.getCompteSource() != null && !transaction.getCompteSource().equals(compte)) {
                        System.out.println("De: " + transaction.getCompteSource().getNumeroCompte());
                    }
                    if(transaction.getComptedestination() != null && !transaction.getComptedestination().equals(compte)) {
                        System.out.println("Vers: " + transaction.getComptedestination().getNumeroCompte());
                    }
                }
            }
            System.out.println("═══════════════════════════════════");
        } else {
            System.out.println("Compte inexistant");
        }
    }

}
