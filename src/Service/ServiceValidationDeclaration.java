package Service;

import Entite.Declaration;
import Entite.Reponse;

public class ServiceValidationDeclaration {

    public static boolean verifierDeclaration( Declaration declaration, Reponse reponse ) {
        return false;
    }

    private static boolean verifierNumeroDePermis( Declaration declaration, Reponse reponse ) {
        return false;
    }

    private static boolean verifierCycle( Declaration declaration, Reponse reponse ) {
        return false;
    }

    private static boolean verifierHeureTransfere( Declaration declaration, Reponse reponse) {
        return false;
    }

    private static boolean verifierActivites( Declaration declaration, Reponse reponse) {
        return false;
    }

    private static boolean verifierNombreHeuresTotaleDansDeclaration( Declaration declaration ,
                                                                      Reponse reponse) {
        return false;
    }

    private static int obtenirNombreHeuresManquante( Declaration declaration) {
        return 0;
    }

    private static boolean verifierActiviteDeGroupe( Declaration declaration, Reponse reponse,
                                                     int nombreHeures) {
        return false;
    }
}
