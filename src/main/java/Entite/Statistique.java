package Entite;

import Service.OutilsJson;
import org.json.simple.JSONObject;

public class Statistique extends JSONObject {

    private static Statistique instance;

    public static Statistique obtenirInstance(String nom_fichier) {
        if (instance == null){
            instance = new Statistique(nom_fichier);
        }
        return instance;
    }

    public static void supprimerInstance() {
        instance = null;
    }

    public Statistique(String nom_fichier) {
        JSONObject jsonObj = OutilsJson.obtenirJsonObjectDeFichier(nom_fichier);
        this.putAll(jsonObj);
    }

}
