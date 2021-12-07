package Service;

import Entite.Activite;
import Entite.Declaration;
import Entite.MessageErreur;
import Entite.Reponse;
import Exception.CleJSONInexistanteException;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
   * Classe qui gère l'entrée d'un fichier JSON
   * avec des méthodes qui transforme celui-ci
   * en objets maléables de language java.
   * On peut ensuite aller chercher les
   * informations spécifiques désirées
   * pour éventuellement les analyser.
*/
public class ServiceDeclarationJSON extends JSONObject {

    private final String fichierEntree;

    public ServiceDeclarationJSON(String fichierEntre)
            throws Exception {
        this.fichierEntree = fichierEntre;
        charger();
    }

    private void charger() throws Exception {
        Object obj = new JSONParser().parse(new FileReader(fichierEntree));
        JSONObject jsonObj = (JSONObject) obj;
        this.putAll(jsonObj);
    }

    /***************************Méthode pour la declaration******************************/

    public Declaration obtenirDeclaration() {
        return new Declaration( obtenirNom(), obtenirPrenom(), obtenirSexe(),
                obtenirNumeroDePermis(),
                obtenirCycle(),
                obtenirOrdre(),
                obtenirHeuresTransfere(obtenirOrdre()),
                obtenirActivites()
        );
    }

    private String obtenirNom(){
        String nom;
        nom = OutilsJson.contientCleObligatoire(this,Constantes.CLE_NOM) ?
                OutilsJson.obtenirStringDeCle(this,Constantes.CLE_NOM) : null;
        return  nom;
    }

    private String obtenirPrenom(){
        String prenom;
        prenom = OutilsJson.contientCleObligatoire(this,Constantes.CLE_PRENOM) ?
                OutilsJson.obtenirStringDeCle(this,Constantes.CLE_PRENOM) : null;
        return  prenom;
    }

    private int obtenirSexe(){
        int sexe = -1;
        sexe = OutilsJson.contientCleObligatoire(this,Constantes.CLE_SEXE) ?
                OutilsJson.obtenirIntDeCle(this,Constantes.CLE_SEXE) : -1;
        return  sexe;
    }

    private String obtenirNumeroDePermis() {
        String numeroDePermis;
        numeroDePermis = OutilsJson.contientCleObligatoire(this,Constantes.CLE_NUMERO_DE_PERMIS) ?
                OutilsJson.obtenirStringDeCle(this,Constantes.CLE_NUMERO_DE_PERMIS) : null;
        return numeroDePermis;
    }

    private String obtenirCycle() {
        String cycle;
        cycle = OutilsJson.contientCleObligatoire(this,Constantes.CLE_CYCLE) ?
                OutilsJson.obtenirStringDeCle(this,Constantes.CLE_CYCLE) : null;
        return cycle;
    }

    private String obtenirOrdre() {
        String ordre;
        ordre = OutilsJson.contientCleObligatoire(this,Constantes.CLE_ORDRE) ?
                OutilsJson.obtenirStringDeCle(this,Constantes.CLE_ORDRE) : null;
        return ordre;
    }

    private int obtenirHeuresTransfere(String ordre) {
        int heuresTransfere = -1;
        if (ordre.equals(ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES) ) {
            heuresTransfere = OutilsJson.contientCleObligatoire(this,Constantes.CLE_NOMBRE_HEURE_TRANSFERE) ?
                    OutilsJson.obtenirIntDeCle(this,Constantes.CLE_NOMBRE_HEURE_TRANSFERE) : -1;
        }
        else if ( OutilsJson.contientCle(this,Constantes.CLE_NOMBRE_HEURE_TRANSFERE) ) {
            MessageErreur message = ServiceMessages.messageErreurHeureTranfereNonSupporte(ordre);
            Reponse.obtenirInstance().ajouterMessageInformation(message);
        }
        return heuresTransfere;
    }

    /***************************Méthode pour les activités******************************/

    public List<Activite> obtenirActivites() {
        if( OutilsJson.contientCleObligatoire(this,Constantes.CLE_LISTE_ACTIVITE) ) {
            return construireListeActivites(new ArrayList<>());
        }
        else return null;
    }

    private JSONArray obtenirListeDesActiviteEnJSONArray() {
        return (JSONArray) this.get(Constantes.CLE_LISTE_ACTIVITE);
    }

    private List<Activite> construireListeActivites(List<Activite> listeActivites) {
        for (Object arrayObj : obtenirListeDesActiviteEnJSONArray() ) {
            JSONObject activiteJson = (JSONObject) arrayObj;
            listeActivites.add(creerActivite(activiteJson));
        }
        return listeActivites;
    }

    public Activite creerActivite(JSONObject activitesJson) {
        return new Activite(
                obtenirDescription(activitesJson),
                obtenirCategorie(activitesJson),
                obtenirHeures(activitesJson),
                obtenirDate(activitesJson)
        );
    }

    private String obtenirDescription(JSONObject activitesJson) {
        String description;
        description = OutilsJson.contientCleObligatoire(Constantes.CLE_DESCRIPTION, activitesJson) ?
                (String) activitesJson.get(Constantes.CLE_DESCRIPTION) : null;
        return description;
    }

    private String obtenirCategorie(JSONObject activitesJson) {
        String categorie;
        categorie = OutilsJson.contientCleObligatoire(Constantes.CLE_CATEGORIE, activitesJson) ?
                (String) activitesJson.get(Constantes.CLE_CATEGORIE) : null;
        return categorie;
    }

    private int obtenirHeures( JSONObject activitesJson ) {
        int heures;
        heures = OutilsJson.contientCleObligatoire(Constantes.CLE_NOMBRE_HEURE,activitesJson) ?
                (int) (long) activitesJson.get(Constantes.CLE_NOMBRE_HEURE) : -1;
        return heures;
    }

    private String obtenirDate(JSONObject activitesJson ) {
        String date;
        date = OutilsJson.contientCleObligatoire(Constantes.CLE_DATE,activitesJson) ?
                (String) activitesJson.get(Constantes.CLE_DATE) : null;
        return date;
    }
    /**********************************************************************************/
}
