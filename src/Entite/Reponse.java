package Entite;

import java.util.ArrayList;
import java.util.List;

public class Reponse {

    private boolean complet;
    private List<String> messages;

    public Reponse() {
        this.complet = false;
        this.messages = new ArrayList<>();
    }

    public void estComplet() {
        this.complet = true;
    }

    public void ajouterMessage(String message) {
        this.messages.add(message);
    }

    //private JsonObject creerObjectDeSortie() {
        //TODO
    //}

    public void ecrireFichierDeSortie(){

    }
}