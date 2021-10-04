package Service;

import Entite.Activite;
import Entite.General;
import Entite.Reponse;
import Utils.Constantes;

public class ServiceValidationDeclaration {

    public void verifierDeclaration(General general, Reponse reponse ) {
        verifierNumeroDePermis(general,reponse);
        verifierCycle(general,reponse);
        verifierHeureTransfere(general,reponse);
        verifierActivites(general,reponse);
    }

    /*########################## Verification Numero De Permis ############################*/

    //TODO - Appeler le bon service de message.
    private void verifierNumeroDePermis(General general, Reponse reponse) {
        if ( ! estNumeroDePermisValide(general.obtenirNumeroDePermis()) )
            reponse.ajouterMessageInformation("TODO");
    }

    public boolean estNumeroDePermisValide( String numeroDePermis ) {
        boolean resultat;

        resultat = verifierPremierCaractereNumeroDePermis(numeroDePermis);
        resultat = resultat && verifierLongueurDuNumeroDePermis(numeroDePermis);
        resultat = resultat && verifierSiNumeroDePermisContientDesNombres(numeroDePermis);
        return resultat;
    }

    private boolean verifierPremierCaractereNumeroDePermis(String numeroDePermis) {
        boolean resultat = false;

        if ( numeroDePermis.charAt(0) >= 65 && numeroDePermis.charAt(0) <= 90 )
            resultat = true;
        return resultat;
    }

    private boolean verifierLongueurDuNumeroDePermis(String numeroDePermis) {
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
    private boolean verifierSiNumeroDePermisContientDesNombres(String numeroDePermis) {
        boolean resultat = true;

        for (char c : numeroDePermis.substring(1).toCharArray()) {
            if ( !Character.isDigit(c) )
                resultat = false;
        }
        return resultat;
    }

    /*############################### Verification Cycle ##################################*/

    //TODO - Appeler le bon service de message.
    public void verifierCycle( General general, Reponse reponse ) {
        if ( ! estCycleValide(general.obtenirCycle()) ) {
            reponse.ajouterMessageErreur("TODO");
        }
    }

    public boolean estCycleValide( String cycle ) {
        return cycle.equals(Constantes.CYCLE_AUTORISEE);
    }

    /*######################### Verification Heures Transfere ############################*/

    //TODO - Appeler la méthode du service de message.
    private void verifierHeureTransfere(General general, Reponse reponse) {
        int nombreHeuresTransfere = general.obtenirHeurestransfere();

        if ( estSuperieurA7(nombreHeuresTransfere) ) {
            general.modifierNombreHeuresTransfereA7();
            reponse.ajouterMessageInformation("TODO");
        }
        else if ( estInferieurA0(nombreHeuresTransfere) )
            reponse.ajouterMessageErreur("TODO");
    }

    private boolean estSuperieurA7(int nombre) {
        return nombre > Constantes.NOMBRE_HEURES_MAXIMALE_A_TRANSFERE;
    }

    private boolean estInferieurA0(int nombre){
        return nombre < 0;
    }

    /*######################### Verification des activités ############################*/

    private void verifierActivites(General general, Reponse reponse) {
        ServiceValidationActivite serviceValidationActivite = new ServiceValidationActivite();

        for (Activite activite : general.obtenirActivites() ) {
            serviceValidationActivite.verifierActivite(activite, reponse);
        }
    }

    /*#################### Verification du nombre totale d'heures #######################*/

    private boolean verifierNombreHeuresTotaleDansDeclaration( General general,
                                                                      Reponse reponse) {
        //TODO
        return false;
    }

    private int obtenirNombreHeuresManquante( General general) {
        //TODO
        return 0;
    }

    private boolean verifierActiviteDeGroupe(General general, Reponse reponse,
                                                    int nombreHeures) {
        //TODO
        return false;
    }
}
