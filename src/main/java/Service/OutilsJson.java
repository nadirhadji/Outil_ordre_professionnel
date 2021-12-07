package Service;

import Entite.MessageErreur;
import org.json.simple.JSONObject;

public class OutilsJson {

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
