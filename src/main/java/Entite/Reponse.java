package Entite;

import java.util.ArrayList;

public class Reponse {

    private static Reponse instance;

    private final ArrayList<String> messagesErreur;
    private final ArrayList<String> messageInformation;

    public Reponse() {
        this.messagesErreur = new ArrayList<String>();
        this.messageInformation = new ArrayList<String>();
    }

    public static Reponse obtenirInstance() {
        if (instance == null){
            instance = new Reponse();
        }
        return instance;
    }

    public void ajouterMessageErreur(String message) {
        this.messagesErreur.add(message);
    }

    public void ajouterMessageInformation(String message) {
        this.messageInformation.add(message);
    }

    public ArrayList<String> obtenirMessagesErreur() {
        return messagesErreur;
    }

    public ArrayList<String> obtenirMessageInformation() {
        return messageInformation;
    }
}