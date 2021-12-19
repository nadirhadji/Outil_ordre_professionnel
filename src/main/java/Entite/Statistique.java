package Entite;

import Service.OutilsJson;
import Service.ServiceStatistique;
import Utils.ConstanteStatistique;
import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Statistique extends JSONObject {

    private static Statistique instance;


    public static Statistique obtenirInstance() {
        if (instance == null){
            instance = new Statistique();
        }
        return instance;
    }

    public Statistique() {
        String nom_fichier = "src/main/java/Fichier/statistiques.json";
        if (ServiceStatistique.fichierExiste(nom_fichier)) {
            this.putAll(OutilsJson.obtenirJsonObjectDeFichier(nom_fichier));
        }
        else {
            initialiserStatistique();
            ServiceStatistique.initialiserFichier();
        }
    }

    private void initialiserStatistique() {
        Map<String,Integer> liste = new HashMap();
        liste.put(ConstanteStatistique.CLE_DECLARATION_TRAITE,0);
        liste.put(ConstanteStatistique.CLE_DECLARATION_COMPLETE,0);
        liste.put(ConstanteStatistique.CLE_DECLARATION_INVALIDE,0);
        liste.put(ConstanteStatistique.CLE_HOMME,0);
        liste.put(ConstanteStatistique.CLE_FEMME,0);
        liste.put(ConstanteStatistique.CLE_NON_BINAIRE,0);
        liste.put(ConstanteStatistique.CLE_ACTIVITE,0);
        liste.put(ConstanteStatistique.CLE_ATELIER,0);
        liste.put(ConstanteStatistique.CLE_COLLOQUE,0);
        liste.put(ConstanteStatistique.CLE_CONFERENCE,0);
        liste.put(ConstanteStatistique.CLE_COUR,0);
        liste.put(ConstanteStatistique.CLE_PRESENTATION,0);
        liste.put(ConstanteStatistique.CLE_PROJET,0);
        liste.put(ConstanteStatistique.CLE_DISCUSSION,0);
        liste.put(ConstanteStatistique.CLE_LECTURE,0);
        liste.put(ConstanteStatistique.CLE_SEMINAIRE,0);
        liste.put(ConstanteStatistique.CLE_REDACTION,0);
        liste.put(ConstanteStatistique.CLE_COMPLET_ARCHI,0);
        liste.put(ConstanteStatistique.CLE_COMPLET_GEOLOGUE,0);
        liste.put(ConstanteStatistique.CLE_COMPLET_PSYCHO,0);
        liste.put(ConstanteStatistique.CLE_COMPLET_PODIATRE,0);
        liste.put(ConstanteStatistique.CLE_INCOMPLET_ARCHI,0);
        liste.put(ConstanteStatistique.CLE_INCOMPLET_GEOLOGUE,0);
        liste.put(ConstanteStatistique.CLE_INCOMPLET_PSHYCO,0);
        liste.put(ConstanteStatistique.CLE_INCOMPLET_PODIATRE,0);
        liste.put(ConstanteStatistique.CLE_PERMIS_INVALIDE,0);
        this.putAll(liste);
    }

    public void incrementerCle(String cle) {
        if (this.containsKey(cle)) {
            int nouvelleValeur = (int) this.get(cle) + 1;
            this.replace(cle,nouvelleValeur);
        }
    }

    public void incrementerCle(String cle, int nombre) {
        if (this.containsKey(cle)) {
            int nouvelleValeur = (int) this.get(cle) + nombre;
            this.replace(cle,nouvelleValeur);
        }
    }
}
