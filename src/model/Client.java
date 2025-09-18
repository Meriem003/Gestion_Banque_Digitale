package model;
import java.util.HashMap;

public class Client extends Personne {
    private String idClient;
    private HashMap<String , Compte> comptes = new HashMap<>();

    public Client(String nom, String prenom, String email, String motDePasse){
        super(nom, prenom, email, motDePasse);
    }

    public String getIdClient(){
        return idClient;
    }

    public void setIdClient(String idClient){
        this.idClient = idClient;
    }

    public HashMap<String , Compte> getComptes(){
        return comptes;
    }
}
