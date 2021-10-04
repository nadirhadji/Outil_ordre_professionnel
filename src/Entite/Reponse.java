package Entite;

import java.util.ArrayList;
import java.util.List;

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

    public void ecrireFichierDeSortie(){

    }
}