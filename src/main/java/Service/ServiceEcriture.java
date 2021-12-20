package Service;

import Entite.MessageErreur;
import Entite.Reponse;
import Entite.Statistique;
import Utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceEcriture {

    public static void ecrireFichier(String nomFichier, String contenue) {
        try {
            FileWriter file = new FileWriter(nomFichier);
            file.write(contenue);
            file.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ecrireFichierReponse(String nomFichier, Reponse reponse) {
        String reponseString = obtenirReponsePrete(reponse);
        ecrireFichier(nomFichier,reponseString);
    }

    public static void ecrireFichierStatistique(String nomFichier, Statistique statistique) {
        String statistiqueString = obtenirJolieJson(statistique.toJSONString());
        ecrireFichier(nomFichier,statistiqueString);
    }

    private static String obtenirReponsePrete(Reponse reponse) {
        JSONObject reponseJSON = new JSONObject();
        ArrayList<MessageErreur> messagesErreur = reponse.obtenirMessagesErreur();
        ArrayList<MessageErreur> messageInformation = reponse.obtenirMessageInformation();

        if ( messagesErreur.isEmpty() ) {
            reponseJSON.put(Constantes.cleReponseComplet,"true");
            JSONArray liste = obtenirListEnJson(messageInformation);
            if(! liste.isEmpty())
                reponseJSON.put(Constantes.cleReponseErreur, liste);
        }
        else {
            reponseJSON.put(Constantes.cleReponseComplet,"false");
            reponseJSON.put(Constantes.cleReponseErreur, fusionnerDeuxListEnJson(messagesErreur,messageInformation));
        }
        return obtenirJolieJson(reponseJSON.toJSONString());
    }

    public static JSONArray obtenirListEnJson(List<MessageErreur> liste) {
        JSONArray listeJson = new JSONArray();
        for (MessageErreur message : liste) {
            listeJson.add(message.getErreur());
        }
        return listeJson;
    }

    public static JSONArray fusionnerDeuxListEnJson(List<MessageErreur> liste1, List<MessageErreur> liste2) {
        JSONArray listeJson = new JSONArray();
        listeJson.addAll(obtenirListEnJson(liste1));
        listeJson.addAll(obtenirListEnJson(liste2));
        return listeJson;
    }

    public static String obtenirJolieJson( String jsonMoche) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        JsonElement elementJson = JsonParser.parseString(jsonMoche);
        return gson.toJson(elementJson);
    }
}
