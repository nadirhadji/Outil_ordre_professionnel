package Entite;

import Exception.CleJSONInexistanteException;
import Utils.Constantes;
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
public class DeclarationJSON {
    private final String fichierEntre;
    private JSONObject jsonObj;

    public DeclarationJSON(String fichierEntre){
        this.fichierEntre = fichierEntre;
    }

    public void charger() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(fichierEntre));
        jsonObj = (JSONObject) obj;
    }

    public boolean contientCle(String cle) {
        return jsonObj.containsKey(cle);
    }

    public String obtenirStringDeCle(String cle) throws CleJSONInexistanteException {
        if( jsonObj.containsKey(cle))
            return (String) jsonObj.get(cle);
        else
            throw new CleJSONInexistanteException("le champ "+cle+" n'existe pas dans le fichier JSON");
    }

    public int obtenirIntDeCle(String cle) throws CleJSONInexistanteException{
        if (jsonObj.containsKey(cle))
            return (int) (long) jsonObj.get(cle);
        else
            throw new CleJSONInexistanteException("le champ "+cle+" n'existe pas dans le fichier JSON");
    }

    public List<Activite> obtenirActivites() throws CleJSONInexistanteException{
        List<Activite> listeActivite = new ArrayList<>();
        if( jsonObj.containsKey(Constantes.CLE_LISTE_ACTIVITE) ) {
            JSONArray jsonArray = (JSONArray) jsonObj.get(Constantes.CLE_LISTE_ACTIVITE);
            for (Object arrayObj : jsonArray) {
                JSONObject activiteEnJson = (JSONObject) arrayObj;
                Activite activite = creerActivite(activiteEnJson);
                listeActivite.add(activite);
            }
        }
        else
            throw new CleJSONInexistanteException("La clé activites n'est pas existante");
        return listeActivite;
    }

    public Activite creerActivite(JSONObject activites) {
        String description = (String) activites.get(Constantes.CLE_DESCRIPTION);
        String categorie = (String) activites.get(Constantes.CLE_CATEGORIE);
        int heures = (int) (long) activites.get(Constantes.CLE_NOMBRE_HEURE);
        String date = (String) activites.get(Constantes.CLE_DATE);
        return new Activite(description, categorie, heures, date);
    }
}
