package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transacion {
    private String idTransaction;
    private String type;
    private double montant;
    private LocalDateTime date;
    private Compte compteSource;
    private Compte comptedestination;

    public Transacion(String idTransaction, String type, double montant, Compte compteSource, Compte comptedestination){
        this.idTransaction = idTransaction;
        this.type = type;
        this.montant = montant;
        this.date = LocalDateTime.now();
        this.compteSource = compteSource;
        this.comptedestination = comptedestination;
    }

    public String getIdTransaction(){
        return idTransaction;
    }
    public String getType(){
        return type;
    }
    public double getMontant(){
        return montant;
    }
    
    public LocalDateTime getDate(){
        return date;
    }

    public Compte getComptedestination() {
        return comptedestination;
    }

    public Compte getCompteSource() {
        return compteSource;
    }
}

