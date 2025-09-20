package service;

import model.Personne;
import model.Client;
import model.Gestionnaire;
import model.Compte;
import model.Transacion;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class BanqueService {
    private List<Client> clients = new ArrayList<>();

    public Personne login(String email, String motDePasse) {
        if (Gestionnaire.GESTIONNAIRE_DEFAULT.getEmail().equals(email) && 
            Gestionnaire.GESTIONNAIRE_DEFAULT.getMotDePasse().equals(motDePasse)) {
            return Gestionnaire.GESTIONNAIRE_DEFAULT;
        }
        
        for (Client client : clients) {
            if (client.getEmail().equals(email) && 
                client.getMotDePasse().equals(motDePasse)) {
                return client;
            }
        }
        return null;
    }

    public void ajouterClient(Client client) {
        clients.add(client);
    } 
    
    public Client register(String nom, String prenom, String email, String motDePasse) {
        Client nouveauClient = new Client(nom, prenom, email, motDePasse);
        clients.add(nouveauClient);
        String numeroCompte = genererNumeroCompte();
        Compte compteParDefaut = new Compte(numeroCompte, "Compte Courant");
        HashMap<String, Compte> comptes = nouveauClient.getComptes();
        comptes.put(numeroCompte, compteParDefaut);
        System.out.println("Compte bancaire par défaut créé : " + numeroCompte);
        return nouveauClient;
    }
    
    private String genererNumeroCompte() {
        long timestamp = System.currentTimeMillis();
        return "CPT" + timestamp;
    }
    
    public double consulterMonSolde(Client client) {
        double soldeTotal = 0.0;
        HashMap<String, Compte> comptes = client.getComptes();
        for (Compte compte : comptes.values()) {
            soldeTotal += compte.getSolde();
        }
        return soldeTotal;
    }

    public boolean deposer(Client client, String numeroCompte, double montant) {
        if (montant <= 0) {
            System.out.println("Erreur : Le montant doit être positif !");
            return false;
        }
        HashMap<String, Compte> comptes = client.getComptes();
        Compte compte = comptes.get(numeroCompte);
        if (compte == null) {
            System.out.println("Erreur : Compte non trouvé !");
            return false;
        }
        double nouveauSolde = compte.getSolde() + montant;
        compte.setSolde(nouveauSolde);
        String idTransaction = "TXN" + System.currentTimeMillis();
        Transacion transaction = new Transacion(idTransaction, "DEPOT", montant, null, compte);
        compte.ajouterTransaction(transaction);

        System.out.println("Dépôt de " + montant + " DH effectué avec succès !");
        System.out.println("Nouveau solde : " + nouveauSolde + " DH");
        return true;
    }
    
    public boolean retrait(Client client, String numeroCompte, double montant) {
        if (montant <= 0) {
            System.out.println("Erreur : Le montant doit être positif !");
            return false;
        }
        HashMap<String, Compte> comptes = client.getComptes();
        Compte compte = comptes.get(numeroCompte);
        if (compte == null) {
            System.out.println("Erreur : Compte non trouvé !");
            return false;
        }
        if (compte.getSolde() < montant) {
            System.out.println("Erreur : Solde insuffisant !");
            return false;
        }
        double nouveauSolde = compte.getSolde() - montant;
        compte.setSolde(nouveauSolde);
        String idTransaction = "TXN" + System.currentTimeMillis();
        Transacion transaction = new Transacion(idTransaction, "RETRAIT", montant, compte, null);
        compte.ajouterTransaction(transaction);

        System.out.println("Retrait effectué avec succès !");
        System.out.println("Nouveau solde : " + nouveauSolde + " DH");
        
        return true;
    }
    
    public void consulterReleve(Client client, String numeroCompte) {
        HashMap<String, Compte> comptes = client.getComptes();
        Compte compte = comptes.get(numeroCompte);
        if (compte == null) {
            System.out.println("Erreur : Compte non trouvé !");
            return;
        }
        System.out.println("===== Relevé du Compte " + numeroCompte + " =====");
        System.out.println("Type de compte : " + compte.getTypeCompte());
        System.out.println("Solde actuel : " + compte.getSolde() + " DH");
        ArrayList<model.Transacion> historique = compte.getHistoriquetransacions();
        if (historique.isEmpty()) {
            System.out.println("Aucune transaction trouvée.");
        } else {
            System.out.println("Historique des transactions :");
            for (model.Transacion transaction : historique) {
                System.out.println(transaction.getDate().toLocalDate() +  " | " + transaction.getType() + " | " + transaction.getMontant() + " DH" +" | ID: " + transaction.getIdTransaction());
            }
        }
    }

    public boolean cloturerCompte(String emailOuId, String numeroCompte) {
    Client client = null;
    for (Client c : clients) {
        if (c.getEmail().equals(emailOuId) || c.getIdClient().equals(emailOuId)) {
            client = c;
            break;
        }
    }
    if (client == null) {
        System.out.println("Erreur : Client non trouvé !");
        return false;
    }
    HashMap<String, Compte> comptes = client.getComptes();
    if (comptes.containsKey(numeroCompte)) {
        comptes.remove(numeroCompte);
        System.out.println("Compte " + numeroCompte + " clôturé avec succès !");
        return true;
    } else {
        System.out.println("Erreur : Compte non trouvé !");
        return false;
    }
}

    public Client enregistrerClientParGestionnaire(String nom, String prenom, String email, String motDePasse, String typeCompte) {
        for (Client client : clients) {
            if (client.getEmail().equals(email)) {
                System.out.println("Erreur : Un client avec cet email existe déjà !");
                return null;
            }
        }
        Client nouveauClient = new Client(nom, prenom, email, motDePasse);
        clients.add(nouveauClient);        
        String numeroCompte = genererNumeroCompte();
        Compte nouveauCompte = new Compte(numeroCompte, typeCompte);
        HashMap<String, Compte> comptes = nouveauClient.getComptes();
        comptes.put(numeroCompte, nouveauCompte);
        System.out.println("===== Enregistrement Client =====");
        System.out.println("Client créé avec succès !");
        System.out.println("Nom : " + nom + " " + prenom);
        System.out.println("Email : " + email);
        System.out.println("ID Client : " + nouveauClient.getIdClient());
        System.out.println("Compte créé : " + numeroCompte + " (" + typeCompte + ")");
        System.out.println("Solde initial : 0.0 DH");
        System.out.println("================================");
        return nouveauClient;
    }
}