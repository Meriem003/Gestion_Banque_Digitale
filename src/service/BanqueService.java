package service;

import model.Client;
import model.Compte;
import java.util.ArrayList;

public class BanqueService {

    private ArrayList<Client> clients = new ArrayList<>();

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
            System.out.println("le montant negative ");
            return;
        }
        Compte compte = client.getComptes().get(numeroCompte);
        if(compte != null){
            compte.setSolde(compte.getSolde()+montant);
            System.out.println("depot reussi");
        }else{
            System.out.println("erreur");
        }
    }
    






}
