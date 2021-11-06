package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.Reponse;
import Utils.Constantes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ServiceValidationActivite{

    private String ordre;
    private String cycle;

    public ServiceValidationActivite(String ordre, String cycle) {
        this.ordre = ordre;
        this.cycle = cycle;
    }

    //TODO - Verifier la date en fonction de l'ordre et du cycle.
    public void verifierActivite(Activite activite, Reponse reponse) {
        verifierDateActivite(activite,reponse);
        verifierNombreHeurePourActivite(activite,reponse);
        verifierCategorie(activite,reponse);
    }

    /*#################### Service.Verification de la categorie #####################*/

    private void verifierCategorie(Activite activite, Reponse reponse ) {
        if ( ! estUneCategorieReconnue(activite.obtenirCategorie()) ) {
            reponse.ajouterMessageInformation(
                  ServiceMessages.erreurMessageCategorieNonReconnue(activite));
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

    /*#################### Service.Verification du nombre d'heure ####################*/

    private void verifierNombreHeurePourActivite(Activite activite,
                                                 Reponse reponse) {
        verifierNombreHeuresNegatif(activite,reponse);
        verifierNombreHeuresMaximum(activite,reponse);
    }

    public void verifierNombreHeuresNegatif( Activite activite, Reponse reponse) {
        int nombreHeures = activite.obtenirHeures();
        if (aNombreHeuresNegatif(nombreHeures)) {
            reponse.ajouterMessageInformation(
                    ServiceMessages.messageErreurNombreHeuresPourActiviteNegatif(
                            activite));
            activite.ignorerActivite();
        }
    }

    public boolean aNombreHeuresNegatif(int nombreHeures) {
        return nombreHeures < Constantes.NOMBRE_HEURE_MINIMALE_POUR_UNE_ACTIVITE;
    }

    public void verifierNombreHeuresMaximum(Activite activite, Reponse reponse) {
        int nombresHeures = activite.obtenirHeures();
        if(aNombreHeuresSuperieurAuMaximum(nombresHeures)) {
            reponse.ajouterMessageInformation(
                    ServiceMessages.messageErreurNombreHeuresPourActiviteSuperieurAuMaximum(
                            activite));
            activite.decrementerNombresHeuresA10();
        }
    }

    public boolean aNombreHeuresSuperieurAuMaximum(int nombreHeures) {
        return nombreHeures > Constantes.NOMBRE_HEURE_MAXIMALE_POUR_UNE_ACTIVITE;
    }

    /*############## Service.Verification la date d'une activité ##################*/

    private void verifierDateActivite(Activite activite, Reponse reponse) {
        if (! formatDateValide(activite.obtenirDate()) ||
                !estDateValide(activite.obtenirDate())){
            activite.ignorerActivite();
            reponse.ajouterMessageInformation(
               ServiceMessages.messageErreurActiviteDateNonReconnue(activite));
        }
    }

    private boolean estDateValide(String date) {
        boolean resultat;
        if ( LocalDate.parse(date).isBefore(
                Constantes.DATE_DEBUT_ACTIVITE_AUTORISEE) )
            resultat = false;
        else
            resultat = !LocalDate.parse(date).isAfter(
                    Constantes.DATE_FIN_ACTIVITE_AUTORISEE);
        return resultat;
    }

    private boolean formatDateValide(String date){
        try {
            LocalDate.parse(date);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }
}
