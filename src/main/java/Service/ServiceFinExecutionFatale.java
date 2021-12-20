package Service;

import Entite.Declaration;
import Entite.MessageErreur;
import Entite.Reponse;
import Entite.Statistique;
import Utils.CodeErreur;
import Utils.ConstanteStatistique;
import Utils.Constantes;
import Service.ServiceStatistique;

public class ServiceFinExecutionFatale {
    public static final String PATH_TO_STATS = "src/main/java/Fichier/statistiques.json";
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
    private static void finExecutionFatale(MessageErreur message) {
        Reponse.supprimerInstance();
        Reponse.obtenirInstance().ajouterMessageErreur(message);
        ServiceEcriture.ecrireFichierReponse(Constantes.ARG1,Reponse.obtenirInstance());
        System.out.println(message.getErreur());
    }

    /**
     * Code 5 : heure negative dans une activite
     */
    public static void finExecutionHeuresNegatives(MessageErreur message) {
        finExecutionFatale(message);
        System.exit(CodeErreur.FATAL_HEURES_NEGATIVES);
    }

    /**
     * Code 6 : champ manquant dans le json
     */
    public static void finExecutionChampManquant(MessageErreur message) {
        finExecutionFatale(message);
        System.exit(CodeErreur.FATAL_CHAMP_MANQUANT);
    }

    /**
     * Code 7 : description de moins de 20 caractères
     */
    public static void finExecutionDescription(MessageErreur message) {
        finExecutionFatale(message);
        System.exit(CodeErreur.FATAL_DESCRIPTION);
    }

    /**
     * Code 8 : numéro de permis invalide
     */
    public static void finExecutionPermisInvalide(MessageErreur message) {
        finExecutionFatale(message);
        System.exit(CodeErreur.FATAL_NUMERO_PERMIS);
    }
}
