package Service;

import Entite.Reponse;
import Utils.Constantes;

public class ServiceFinExecutionFatale {

    /**
     * Méthode a executer dans le cas d'une fin d'execution due a une erreur fatale
     *
     *  Code 5 - heure negative dans une activite.
     *  Code 6 - champ manquant dans le json.
     *  Code 7 - description de moins de 20 caractères.
     *  Code 8 - numéro de permis invalide.
     *
     * @param message message a afficher a la console et dans le fichier reponse.json
     */
    private static void finExecutionFatale(String message) {
        Reponse.supprimerInstance();
        Reponse.obtenirInstance().ajouterMessageErreur(message);
        ServiceReponse.ecrireFichierDeSortie(Constantes.ARG1,Reponse.obtenirInstance());
        System.out.println(message);
    }

    /**
     * Code 5 : heure negative dans une activite
     */
    public static void finExecutionHeuresNegatives(String message) {
        finExecutionFatale(message);
        System.exit(5);
    }

    /**
     * Code 6 : champ manquant dans le json
     */
    public static void finExecutionChampManquant(String message) {
        finExecutionFatale(message);
        System.exit(6);
    }

    /**
     * Code 7 : description de moins de 20 caractères
     */
    public static void finExecutionDescription(String message) {
        finExecutionFatale(message);
        System.exit(7);
    }

    /**
     * Code 8 : numéro de permis invalide
     */
    public static void finExecutionPermisInvalide(String message) {
        finExecutionFatale(message);
        System.exit(8);
    }
}
