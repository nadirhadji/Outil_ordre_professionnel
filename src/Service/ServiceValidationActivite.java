package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.Reponse;
import Utils.Constantes;
import java.time.LocalDate;

public class ServiceValidationActivite {

    public void verifierActivite(Activite activite, Reponse reponse) {
        verifierDateActivite(activite,reponse);
        verifierNombreHeurePourActivite(activite,reponse);
        verifierCategorie(activite,reponse);
    }

    /*#################### Verification de la categorie #######################*/

    private void verifierCategorie(Activite activite, Reponse reponse ) {
        if ( ! estUneCategorieReconnue(activite.obtenirCategorie()) ) {
            reponse.ajouterMessageInformation(ServiceMessages.erreurMessageCategorieNonReconnue(activite));
            activite.ignorerActivite();
        }
    }

    public boolean estUneCategorieReconnue(String chaine) {
        for (Categorie c : Categorie.values()) {
            if ( c.toString().equals(chaine) ) {
                return true;
            }
        }
        return false;
    }

    /*#################### Verification du nombre d'heure #######################*/

    private void verifierNombreHeurePourActivite(Activite activite, Reponse reponse) {
        if( ! aNombreHeurePourActiviteValide(activite.obtenirHeures()) ) {
            reponse.ajouterMessageInformation(ServiceMessages.messageErreurNombreHeuresPourActiviteInvalide(activite));
            activite.ignorerActivite();
        }
    }

    public boolean aNombreHeurePourActiviteValide( int nombre ) {
        return nombre >= Constantes.NOMBRE_HEURE_MINIMALE_POUR_ACTIVITE;
    }

    /*#################### Verification la date d'une activité #######################*/

    private void verifierDateActivite(Activite activite, Reponse reponse) {
        if ( ! estDateValide(activite.obtenirDate()) ){
            activite.ignorerActivite();
            reponse.ajouterMessageInformation(ServiceMessages.messageErreurActiviteHorsDateReconnue(activite));
        }
    }

    private boolean estDateValide(LocalDate date) {
        boolean resultat;
        if ( date.isBefore(Constantes.DATE_DEBUT_ACTIVITE_AUTORISEE) )
            resultat = false;
        else
            resultat = !date.isAfter(Constantes.DATE_FIN_ACTIVITE_AUTORISEE);
        return resultat;
    }
}
