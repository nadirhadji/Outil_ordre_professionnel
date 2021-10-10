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
        JSONArray sortieListErreur = new JSONArray();
        messageDeSortie(sortieListErreur);
        sortieInfo.put("complet", this.complet);
        sortieInfo.put("erreurs", sortieListErreur);
        try (FileWriter file = new FileWriter(nomFichierSortie)) {
            file.write(sortieInfo.toJSONString());
            file.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
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


