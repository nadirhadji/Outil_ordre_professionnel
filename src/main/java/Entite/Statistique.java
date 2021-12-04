package Entite;

public class Statistique {

    private static Statistique instance;

    public static Statistique obtenirInstance() {
        if (instance == null){
            instance = new Statistique();
        }
        return instance;
    }

    public static void supprimerInstance() {
        instance = null;
    }
}
