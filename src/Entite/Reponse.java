package Entite;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import Utils.Constantes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Reponse {

    private List<String> messagesErreur;
    private List<String> messageInformation;

    public Reponse() {
        this.messagesErreur = new ArrayList<>();
        this.messageInformation = new ArrayList<>();
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

    public JSONArray messageDeSortie( JSONArray listErreur){
        for(int i=0 ; i < this.messageInformation.size() ; i++){
            listErreur.add(messageInformation.get(i).trim());
            System.out.println(messageInformation.get(i));
        }
        for(int i=0 ; i < this.messagesErreur.size() ; i++){
            listErreur.add(messagesErreur.get(i).trim());
            System.out.println(messagesErreur.get(i));
        }
        return listErreur;
    }
}


