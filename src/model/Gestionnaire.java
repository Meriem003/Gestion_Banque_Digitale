package model;

public class Gestionnaire extends Personne{
    private String idGestionnaire;
    public static final Gestionnaire GESTIONNAIRE_DEFAULT = new Gestionnaire(
        "ADMIN_001", 
        "Admin", 
        "Système", 
        "admin@finbank.com", 
        "admin123"
    );
    public Gestionnaire(String idGestionnaire, String nom, String prenom, String email, String motDePasse){
        super(nom, prenom, email, motDePasse);
        this.idGestionnaire = idGestionnaire;
    }
    public String getIdGestionnaire(){
        return idGestionnaire;
    }
    public void setIdGestionnaire(String idGestionnaire){
        this.idGestionnaire = idGestionnaire;
    }
}
