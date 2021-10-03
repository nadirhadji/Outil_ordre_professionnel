package Service;

import Entite.Declaration;
import Entite.Reponse;
import Utils.Constantes;

import java.util.Iterator;

public class ServiceValidationDeclaration {

    public static boolean verifierDeclaration( Declaration declaration, Reponse reponse ) {
        verifierNumeroDePermis(declaration,reponse);
        verifierCycle(declaration.obtenirCycle());
        return false;
    }

    //TODO - Appeler le bon service de message.
    private static void verifierNumeroDePermis(Declaration declaration, Reponse reponse) {
        if ( ! estNumeroDePermisValide(declaration.obtenirNumeroDePermis()) )
            reponse.ajouterMessage("TODO");
    }

    public static boolean estNumeroDePermisValide( String numeroDePermis ) {
        boolean resultat;

        resultat = verifierPremierCaractereNumeroDePermis(numeroDePermis);
        resultat = resultat && verifierLongueurDuNumeroDePermis(numeroDePermis);
        resultat = resultat && verifierSiNumeroDePermisContientDesNombres(numeroDePermis);
        return resultat;
    }

    private static boolean verifierPremierCaractereNumeroDePermis(String numeroDePermis) {
        boolean resultat = false;

        if ( numeroDePermis.charAt(0) >= 65 && numeroDePermis.charAt(0) <= 90 )
            resultat = true;
        return resultat;
    }

    private static boolean verifierLongueurDuNumeroDePermis(String numeroDePermis) {
        boolean resultat = false;

        if ( numeroDePermis.length() == 5)
            resultat = true;
        return resultat;
    }

    /**
     * Dans l'example d'execution du TP, le numero de permis a cette forme "A0001".
     * l'implémentation de la methode qui suit verifie si les 4 dernier caracteres
     * sont des chiffres.
     *
     * @param numeroDePermis
     * @return True si la condition est respecté
     */
    private static boolean verifierSiNumeroDePermisContientDesNombres(String numeroDePermis) {
        boolean resultat = true;

        for (char c : numeroDePermis.substring(1).toCharArray()) {
            if ( !Character.isDigit(c) )
                resultat = false;
        }
        return resultat;
    }

    public static boolean verifierCycle( String cycle ) {
        return cycle.equals(Constantes.CYCLE_AUTORISEE);
    }

    private static boolean verifierHeureTransfere( Declaration declaration, Reponse reponse) {
        //TODO
        return false;
    }

    private static boolean verifierActivites( Declaration declaration, Reponse reponse) {
        //TODO
        return false;
    }

    private static boolean verifierNombreHeuresTotaleDansDeclaration( Declaration declaration ,
                                                                      Reponse reponse) {
        //TODO
        return false;
    }

    private static int obtenirNombreHeuresManquante( Declaration declaration) {
        //TODO
        return 0;
    }

    private static boolean verifierActiviteDeGroupe( Declaration declaration, Reponse reponse,
                                                     int nombreHeures) {
        //TODO
        return false;
    }
}
