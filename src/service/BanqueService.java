package service;

import model.Client;
import model.Compte;

import java.util.ArrayList;

public class BanqueService {

    private ArrayList<Client> clients = new ArrayList<>();

    public Client creerClient(String nom, String prenom, String email, String motDepasse){
        // creer client
        String idClient = "id"+(clients.size()+1);
        Client client = new Client(nom, prenom, email, motDepasse);
        client.setIdClient(idClient);

        // creer compte par defaut
        String numeroCompte = "ACC"+(clients.size())+"322";
        Compte compteCourant = new Compte(numeroCompte, "courant");

        //add le compte au client et le client a la liste
        client.getComptes().put(numeroCompte,compteCourant);
        clients.add(client);

        //msg de succés
        System.out.println("Compte courant créé : " + numeroCompte + " | Solde initial : 0.0DH");

        return client;
    }

    


}
