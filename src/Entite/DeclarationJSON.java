package Entite;

import Utils.Constantes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
    Classe qui gere l'entree d'un fichier JSON
    avec des methodes qui transforme ce fichier
    en objet maleable en language java et qui peut
    aller chercher des informations specifiques
    a cet objet.
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

    public void enregister() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(fichierSortie);
        pw.write(jsonObj.toJSONString());
        pw.flush();
        pw.close();
    }

    public String obtenirStringDeCle(String cle) {
        return (String) jsonObj.get(cle);
    }
/*
    public int obtenirIntDeCle(String cle) {
        return Integer.parseInt(cle);
    }
*/
public int obtenirIntDeCle(String cle) {
    return (int) (long) jsonObj.get(cle);
}
    /*
    public int getNombreActivites(){
        int nbActivites=0;
        JSONArray jsonArray = (JSONArray) jsonObj.get("activites");
        for (Object arrayObj : jsonArray){
            nbActivites++;
        }
        return nbActivites;
    }

    public String getInfoActivites(String info, int indexActivite){
        String returnInfo = null;
        String stringComparaison = null;
        JSONArray jsonArray = (JSONArray) jsonObj.get("activites");
        int i = 0;
        for (Object arrayObj : jsonArray) {
            JSONObject activites = (JSONObject) arrayObj;

            if(i + 1 == indexActivite){
                returnInfo = (String) activites.get(info);
            }
            i++;
        }
        return returnInfo;
    }
     */

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
        LocalDate date = LocalDate.parse((String) activites.get(Constantes.cleDate));
        return new Activite(description, categorie, heures, date);
    }
}
