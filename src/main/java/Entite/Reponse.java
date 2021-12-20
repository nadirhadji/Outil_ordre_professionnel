package Entite;

import java.util.ArrayList;

public class Reponse {

    private static Reponse instance;

    private final ArrayList<MessageErreur> messagesErreur;
    private final ArrayList<MessageErreur> messageInformation;

    public Reponse() {
        this.messagesErreur = new ArrayList<MessageErreur>();
        this.messageInformation = new ArrayList<MessageErreur>();
    }

    public static Reponse obtenirInstance() {
        if (instance == null){
            instance = new Reponse();
        }
        return instance;
    }

    public static void supprimerInstance() {
        instance = null;
    }

    public void ajouterMessageErreur(MessageErreur message) {
        this.messagesErreur.add(message);
    }

    public void ajouterMessageInformation(MessageErreur message) {
        this.messageInformation.add(message);
    }

    public ArrayList<MessageErreur> obtenirMessagesErreur() {
        return messagesErreur;
    }

    public ArrayList<MessageErreur> obtenirMessageInformation() {
        return messageInformation;
    }
}