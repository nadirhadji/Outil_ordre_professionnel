package Service;

import Entite.MessageErreur;
import Entite.Reponse;
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

public class ServiceReponse {

    public static void ecrireFichierDeSortie(String nomFichierSortie, Reponse reponse) {
        try {
            FileWriter file = new FileWriter(nomFichierSortie);
            file.write(obtenirReponsePrete(reponse));
            file.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
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
