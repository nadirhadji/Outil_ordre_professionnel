package Entite;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import Utils.Constantes;
import com.google.gson.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Reponse {

    private ArrayList<String> messagesErreur;
    private ArrayList<String> messageInformation;

    public Reponse() {
        this.messagesErreur = new ArrayList<String>();
        this.messageInformation = new ArrayList<String>();
    }

    public void ajouterMessageErreur(String message) {
        this.messagesErreur.add(message);
    }

    public void ajouterMessageInformation(String message) {
        this.messageInformation.add(message);
    }

    public void ecrireFichierDeSortie(String nomFichierSortie) {
        try {
            FileWriter file = new FileWriter(nomFichierSortie);
            file.write(obtenirReponsePrete());
            file.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String obtenirReponsePrete() {
        JSONObject reponse = new JSONObject();
        if ( messagesErreur.isEmpty() ) {
            reponse.put(Constantes.cleReponseComplet,"true");
            JSONArray liste = obtenirListEnJson(messageInformation);
            if(! obtenirListEnJson(messageInformation).isEmpty())
                reponse.put(Constantes.cleReponseErreur, obtenirListEnJson(messageInformation));
        }
        else {
            reponse.put(Constantes.cleReponseComplet,"false");
            reponse.put(Constantes.cleReponseErreur, fusionnerDeuxListEnJson(messagesErreur,messageInformation));
        }
        return obtenirJolieJson(reponse.toJSONString());
    }

    public JSONArray obtenirListEnJson(List<String> liste) {
        JSONArray listeJson = new JSONArray();
        listeJson.addAll(liste);
        return listeJson;
    }

    public JSONArray fusionnerDeuxListEnJson(List<String> liste1, List<String> liste2) {
        JSONArray listeJson = new JSONArray();
        listeJson.addAll(liste1);
        listeJson.addAll(liste2);
        return listeJson;
    }

    public String obtenirJolieJson( String jsonMoche) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        JsonElement elementJson = JsonParser.parseString(jsonMoche);
        return gson.toJson(elementJson);
    }
}


