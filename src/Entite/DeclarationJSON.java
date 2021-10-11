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
    private String fichierSortie;
    private JSONObject jsonObj;

    public DeclarationJSON(String fichierEntre, String fichierSortie){
        this.fichierEntre = fichierEntre;
        this.fichierSortie = fichierSortie;
        this.jsonObj = null;
    }

    public void charger() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(fichierEntre));
        jsonObj = (JSONObject) obj;
    }

    public String obtenirStringDeCle(String cle) {
        return (String) jsonObj.get(cle);
    }

    public int obtenirIntDeCle(String cle) {
        return (int) (long) jsonObj.get(cle);
    }

    public List<Activite> obtenirActivites() {
        List<Activite> listeActivite = new ArrayList<Activite>();
        JSONArray jsonArray = (JSONArray) jsonObj.get(Constantes.cleListeActivite);
        for (Object arrayObj : jsonArray) {
            JSONObject activiteEnJson = (JSONObject) arrayObj;
            Activite activite = creerActivite(activiteEnJson);
            listeActivite.add(activite);
        }
        return listeActivite;
    }

    public Activite creerActivite(JSONObject activites) {
        String description = (String) activites.get(Constantes.cleDescription);
        String categorie = (String) activites.get(Constantes.cleCategorie);
        int heures = (int) (long) activites.get(Constantes.cleNombreHeure);
        String date = (String) activites.get(Constantes.cleDate);
        return new Activite(description, categorie, heures, date);
    }
}
