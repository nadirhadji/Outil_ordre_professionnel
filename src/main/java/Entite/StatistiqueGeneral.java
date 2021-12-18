package Entite;

import Service.OutilsJson;
import Service.ServiceStatistique;
import Utils.ConstanteStatistique;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public class StatistiqueGeneral extends JSONArray {

    public StatistiqueGeneral() {
        String nom_fichier = "src/main/java/Fichier/statistiques.json";
        if (ServiceStatistique.fichierExiste(nom_fichier)) {
            JSONArray jsonArray = OutilsJson.obtenirJsonArrayDeFichier(nom_fichier);
            this.addAll(jsonArray);
        }
        else {
            initialiserStatistique();
            ServiceStatistique.initialiserFichier();
        }
    }

    private void initialiserStatistique() {
        ArrayList<LigneStatistique> liste = new ArrayList<>();
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_DECLARATION_TRAITE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_DECLARATION_COMPLETE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_DECLARATION_INVALIDE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_HOMME,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_FEMME,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_NON_BINAIRE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_ACTIVITE,0));
        this.addAll(liste);
    }

}
