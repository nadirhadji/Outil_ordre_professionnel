package Entite;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Reponse {

    private boolean complet;
    private List<String> messagesErreur;
    private List<String> messageInformation;

    public Reponse() {
        this.complet = false;
        this.messagesErreur = new ArrayList<>();
        this.messageInformation = new ArrayList<>();
    }

    public void estComplet() {
        this.complet = true;
    }

    public void ajouterMessageErreur(String message) {
        this.messagesErreur.add(message);
    }

    public void ajouterMessageInformation(String message) {
        this.messageInformation.add(message);
    }

    //private JsonObject creerObjectDeSortie() {
        //TODO
    //}

    public void ecrireFichierDeSortie(String nomFichierSortie){
        JSONObject sortieInfo = new JSONObject();
        sortieInfo.put("complet", "true or false"); //le true or false faut aller le chercher
        String erreurComplet = null;
        for(int i=0 ; i < this.messagesErreur.size() ; i++){
            erreurComplet = messagesErreur.get(i) + ",\n"+erreurComplet;
        }
        sortieInfo.put("erreurs", erreurComplet);
        try (FileWriter file = new FileWriter(nomFichierSortie)) {
            file.write(sortieInfo.toJSONString());
            file.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}