package Service;

import Entite.Activite;
import Entite.Declaration;
import Entite.Reponse;
import Exception.CleJSONInexistanteException;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
public class ServiceDeclarationJSON {

    private final String fichierEntree;
    private JSONObject jsonObj;

    public ServiceDeclarationJSON(String fichierEntre) throws IOException, ParseException {
        this.fichierEntree = fichierEntre;
        charger();
    }

    //Constructeur a utiliser dans les testes
    public ServiceDeclarationJSON(String fichierEntree, JSONObject jsonObj ) {
        this.fichierEntree = fichierEntree;
        this.jsonObj = jsonObj;
    }

    private void charger() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(fichierEntree));
        jsonObj = (JSONObject) obj;
    }

    public boolean contientCle(String cle) {
        return jsonObj.containsKey(cle);
    }

    public String obtenirStringDeCle(String cle) {
        String resultat = null;
        if( jsonObj.containsKey(cle))
            resultat = (String) jsonObj.get(cle);
        return resultat;
    }

    public int obtenirIntDeCle(String cle) {
        int resultat = -1;
        if (jsonObj.containsKey(cle))
            resultat = (int) (long) jsonObj.get(cle);
        return resultat;
    }

    /*
     * L'appel a cette méthodes  implique que la clé doit etreexistante dans le JSON
     * en entrée dans le cas contraire, on doit interrompre l'execution du programme
     * pour cause de fichier JSON incomplet
     */
    public boolean contientCleObligatoire(String cle) {
        if ( jsonObj.containsKey(cle) )
            return true;
        else {
            return finExecutionChampManquant(cle);
        }
    }

    public boolean contientCleObligatoire(String cle , JSONObject json) {
        if ( json.containsKey(cle) )
            return true;
        else {
            return finExecutionChampManquant(cle);
        }
    }

    /***************************Méthode pour la declaration******************************/

    public Declaration obtenirDeclaration() {
        return new Declaration(
                obtenirNumeroDePermis(),
                obtenirCycle(),
                obtenirOrdre(),
                obtenirHeuresTransfere(obtenirOrdre()),
                obtenirActivites()
        );
    }

    private String obtenirNumeroDePermis() {
        String numeroDePermis;
        numeroDePermis = contientCleObligatoire(Constantes.CLE_NUMERO_DE_PERMIS) ?
            obtenirStringDeCle(Constantes.CLE_NUMERO_DE_PERMIS) : null;
        return numeroDePermis;
    }

    private String obtenirCycle() {
        String cycle;
        cycle = contientCleObligatoire(Constantes.CLE_CYCLE) ?
                obtenirStringDeCle(Constantes.CLE_CYCLE) : null;
        return cycle;
    }

    private String obtenirOrdre() {
        String ordre;
        ordre = contientCleObligatoire(Constantes.CLE_ORDRE) ?
                obtenirStringDeCle(Constantes.CLE_ORDRE) : null;
        return ordre;
    }

    private int obtenirHeuresTransfere(String ordre) {
        int heuresTransfere = -1;
        if (ordre.equals(ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES) ) {
            heuresTransfere = contientCleObligatoire(Constantes.CLE_NOMBRE_HEURE_TRANSFERE) ?
                    obtenirIntDeCle(Constantes.CLE_NOMBRE_HEURE_TRANSFERE) : -1;
        }
        else if ( contientCle(Constantes.CLE_NOMBRE_HEURE_TRANSFERE) ) {
            String message = ServiceMessages.messageErreurHeureTranfereNonSupporte(ordre);
            Reponse.obtenirInstance().ajouterMessageInformation(message);
        }
        return heuresTransfere;
    }

    /***************************Méthode pour les activités******************************/

    public List<Activite> obtenirActivites() {
        if( contientCleObligatoire(Constantes.CLE_LISTE_ACTIVITE) ) {
            return construireListeActivites(new ArrayList<>());
        }
        else return null;
    }

    private JSONArray obtenirListeDesActiviteEnJSONArray() {
        return (JSONArray) jsonObj.get(Constantes.CLE_LISTE_ACTIVITE);
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
        description = contientCleObligatoire(Constantes.CLE_DESCRIPTION, activitesJson) ?
                (String) activitesJson.get(Constantes.CLE_DESCRIPTION) : null;
        return description;
    }

    private String obtenirCategorie(JSONObject activitesJson) {
        String categorie;
        categorie = contientCleObligatoire(Constantes.CLE_CATEGORIE, activitesJson) ?
                (String) activitesJson.get(Constantes.CLE_CATEGORIE) : null;
        return categorie;
    }

    private int obtenirHeures( JSONObject activitesJson ) {
        int heures;
        heures = contientCleObligatoire(Constantes.CLE_NOMBRE_HEURE,activitesJson) ?
                (int) (long) activitesJson.get(Constantes.CLE_NOMBRE_HEURE) : -1;
        return heures;
    }

    private String obtenirDate(JSONObject activitesJson ) {
        String date;
        date = contientCleObligatoire(Constantes.CLE_DATE,activitesJson) ?
                (String) activitesJson.get(Constantes.CLE_DATE) : null;
        return date;
    }

    private boolean finExecutionChampManquant(String cle) {
        String message = ServiceMessages.messageErreurChampDansJsonInexistante(cle);
        ServiceFinExecutionFatale.finExecutionChampManquant(message);
        return false;
    }
    /**********************************************************************************/
}
