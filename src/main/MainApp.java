package main;
import  service.BanqueService;
import  ui.MenuClient;
import ui.AuthUI;
import ui.MenuGestionnaire;

public class MainApp {

    public static void main(String[] args) {
        BanqueService banqueService = new BanqueService();

        AuthUI authUI = new AuthUI();
        authUI.afficherMenuPrincipal();
    }
}
