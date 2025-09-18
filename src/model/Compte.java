package model;
import java.util.ArrayList;

public class Compte {
    private String numeroComptes;
    private double solde;
    private String typeCompte;
    private ArrayList<Transacion> historiquetransacions = new ArrayList<>();

    public Compte(String numeroComptes, String typeCompte){
        this.numeroComptes = numeroComptes;
        this.typeCompte = typeCompte;
        this.solde = 0.0;
    }

    public ArrayList<Transacion> getHistoriquetransacions(){
        return historiquetransacions;
    }

    public String getNumeroCompte(){
        return getNumeroCompte();
    }
    public double getSolde(){
        return solde;
    }
    public String getTypeCompte(){
        return typeCompte;
    }

}
