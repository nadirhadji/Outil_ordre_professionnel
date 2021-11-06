package Entite;

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
    private String fichierEntre;
    private JSONObject jsonObj;

    public DeclarationJSON(String fichierEntre){
        this.fichierEntre = fichierEntre;
    }

    public void charger() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(fichierEntre));
        jsonObj = (JSONObject) obj;
    }

    public String obtenirStringDeCle(String cle) {
        return (String) jsonObj.get(cle);
    }

    public int obtenirIntDeCle(String cle) {
        try{
            return (int) (long) jsonObj.get(cle);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Activite> obtenirActivites() {
        List<Activite> listeActivite = new ArrayList<Activite>();
        JSONArray jsonArray = (JSONArray) jsonObj.get(Constantes.CLE_LISTE_ACTIVITE);
        for (Object arrayObj : jsonArray) {
            JSONObject activiteEnJson = (JSONObject) arrayObj;
            Activite activite = creerActivite(activiteEnJson);
            listeActivite.add(activite);
        }
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
