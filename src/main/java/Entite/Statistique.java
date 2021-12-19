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
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_ATELIER,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_COLLOQUE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_CONFERENCE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_COUR,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_PRESENTATION,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_PROJET,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_DISCUSSION,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_LECTURE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_SEMINAIRE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_REDACTION,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_COMPLET_ARCHI,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_COMPLET_GEOLOGUE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_COMPLET_PSYCHO,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_COMPLET_PODIATRE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_INCOMPLET_ARCHI,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_INCOMPLET_GEOLOGUE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_INCOMPLET_PSHYCO,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_INCOMPLET_PODIATRE,0));
        liste.add(new LigneStatistique(ConstanteStatistique.CLE_PERMIS_INVALIDE,0));
        
        this.addAll(liste);
    }

}
