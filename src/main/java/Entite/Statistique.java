package Entite;

import Service.OutilsJson;
import Service.ServiceStatistique;
import org.json.simple.JSONObject;

public class Statistique extends JSONObject {

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

    public Statistique() {
        String nom_fichier = "src/main/java/Fichier/statistiques.json";
        JSONObject jsonObj = OutilsJson.obtenirJsonObjectDeFichier(nom_fichier);
        this.putAll(jsonObj);
    }

    private void initialiserStatistique(String nom_fichier) {
        if ( ! ServiceStatistique.fichierExiste(nom_fichier) ) {
        }
    }
    
}
