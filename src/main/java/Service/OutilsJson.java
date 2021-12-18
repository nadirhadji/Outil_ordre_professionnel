package Service;

import Entite.MessageErreur;
import Utils.Constantes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OutilsJson {

    public static JSONArray obtenirJsonArrayDeFichier(String nom_fichier) {
        JSONArray jsonArray = null ;
        try {
            jsonArray =  chargerFichierEnJsonArray(nom_fichier);
        } catch (Exception e) {
            gererException(e);
        }
        return jsonArray;
    }

    public static JSONObject obtenirJsonObjectDeFichier(String nom_fichier) {
        JSONObject jsonObject = null;
        try {
            jsonObject =  chargerFichierEnJsonObject(nom_fichier);
        } catch (Exception e) {
            gererException(e);
        }
        return jsonObject;
    }

    private static void gererException(Exception e) {
        if (e.getClass() == FileNotFoundException.class) {
            System.out.println("Le fichier " + Constantes.ARG0 + " n'existe pas");
            System.exit(-1);
        } else if (e.getClass() == ParseException.class) {
            System.out.println("Le format du fichier +" + Constantes.ARG0 + " ne respecte pas le format JSON");
            System.exit(-1);
        } else {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public static JSONArray chargerFichierEnJsonArray(String nomFichier) throws IOException, ParseException{
        Object array = new JSONParser().parse(new FileReader(nomFichier));
        return (JSONArray) array;
    }

    public static JSONObject chargerFichierEnJsonObject(String nom_fichier) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(nom_fichier));
        return (JSONObject) obj;
    }

    public static boolean contientCle(JSONObject json, String cle) {
        return json.containsKey(cle);
    }

    public static String obtenirStringDeCle(JSONObject json, String cle) {
        String resultat = null;
        if( json.containsKey(cle))
            resultat = (String) json.get(cle);
        return resultat;
    }

    public static int obtenirIntDeCle(JSONObject json, String cle) {
        int resultat = -1;
        if (json.containsKey(cle))
            resultat = (int) (long) json.get(cle);
        return resultat;
    }

    /*
     * L'appel a cette méthodes  implique que la clé doit etre existante dans le JSON
     * en entrée dans le cas contraire, on doit interrompre l'execution du programme
     * pour cause de fichier JSON incomplet
     */
    public static boolean contientCleObligatoire(JSONObject json, String cle) {
        if ( json.containsKey(cle) )
            return true;
        else {
            return finExecutionChampManquant(cle);
        }
    }

    public static boolean contientCleObligatoire(String cle , JSONObject json) {
        if ( json.containsKey(cle) )
            return true;
        else {
            return finExecutionChampManquant(cle);
        }
    }

    private static boolean finExecutionChampManquant(String cle) {
        MessageErreur message = ServiceMessages.messageErreurChampDansJsonInexistante(cle);
        ServiceFinExecutionFatale.finExecutionChampManquant(message);
        return false;
    }
}
