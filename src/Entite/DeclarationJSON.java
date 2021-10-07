package Entite;

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
    private String inputFile;
    private String outputFile;
    private JSONObject jsonObj;

    public DeclarationJSON(String inputFile, String outputFile){

        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.jsonObj = null;
    }

    public void load() throws IOException, ParseException {

        Object obj = new JSONParser().parse(new FileReader(inputFile));
        jsonObj = (JSONObject) obj;
    }

    public void save() throws FileNotFoundException {

        PrintWriter pw = new PrintWriter(outputFile);
        pw.write(jsonObj.toJSONString());

        pw.flush();
        pw.close();
    }
    /*
    public void to_string() {
        String numeroDePermis = (String) jsonObj.get("numero_de_permis");
        System.out.println("numero de permis : "+ numeroDePermis); //a enelever apres testing

        String cycle = (String) jsonObj.get("cycle");
        System.out.println("cycle : "+ cycle);//a enelever apres testing

        String heuresTransfere = (String) jsonObj.get("heures_transferees_du_cycle_precedent");
        System.out.println("heures transferees du cycle precedent : "+ heuresTransfere);//a enelever apres testing

        JSONArray jsonArray = (JSONArray) jsonObj.get("activites");
        for (Object arrayObj : jsonArray) {
            JSONObject activites = (JSONObject) arrayObj;
            System.out.println("===========================");//a enelever apres testing
            System.out.println("description : " + activites.get("description"));//a enelever apres testing
            System.out.println("categorie : " + activites.get("categorie"));//a enelever apres testing
            System.out.println("heures : " + activites.get("heures"));//a enelever apres testing
            System.out.println("date : " + activites.get("date"));//a enelever apres testing

        }
    }
    */
    public String getInfoGen(String info){
        String returnInfo = (String) jsonObj.get(info);
        return returnInfo;
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
        JSONArray jsonArray = (JSONArray) jsonObj.get("activites");
        for (Object arrayObj : jsonArray) {
            JSONObject activites = (JSONObject) arrayObj;
            String description = (String) activites.get("description");//a enelever apres testing
            String cat = (String) activites.get("categorie");//a enelever apres testing
            int heures = (int) activites.get("heures");//a enelever apres testing
            LocalDate date = LocalDate.parse((String) activites.get("date"));//a enelever apres testing
            listeActivite.add(new Activite(description, cat, heures, date));
        }
        return listeActivite;
    }
}
