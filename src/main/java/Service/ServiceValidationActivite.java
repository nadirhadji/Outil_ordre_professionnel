package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.Reponse;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import Utils.ConstantesGeologue;
import Utils.ConstantesPsychologues;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ServiceValidationActivite {

    private String ordre;
    private String cycle;

    public ServiceValidationActivite(String ordre, String cycle) {
        this.ordre = ordre;
        this.cycle = cycle;
    }

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
        for (Categorie categorie : Categorie.values()) {
            if ( categorie.toString().equals(chaine) ) {
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

    //TODO - Trouver un moyen d'afficher les bonnes dates dans le message d'erreur sans trop de couplage
    private void verifierDateActivite(Activite activite, Reponse reponse) {
        String date = activite.obtenirDate();
        if ( ! estformatDateValide(date) ) {
            activite.ignorerActivite();
            reponse.ajouterMessageInformation(
                    ServiceMessages.messageErreurActiviteDateNonReconnue(activite));
        }
        else if ( !estDateValide(date)) {
            activite.ignorerActivite();
            reponse.ajouterMessageInformation(
                    ServiceMessages.messageErreurActiviteHorsDateReconnue(activite));
        }
    }

    private boolean estformatDateValide(String date){
        try {
            LocalDate.parse(date);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }

    private boolean estDateValide(String date) {
        if( ordre.equals(Constantes.VALEUR_ORDRE_ARCHITECTES) ) {
            return estDateArchitecteValide(date);
        }
        else if ( ordre.equals(Constantes.VALEUR_ORDRE_GEOLOGUES) ) {
            return estDateGeologueValide(date);
        }
        else if ( ordre.equals(Constantes.VALEUR_ORDRE_PSHYCOLOGUES) ) {
            return estDatePshycologueValide(date);
        }
        return false;
    }

    private boolean estDateArchitecteValide(String date) {
        return switch (this.cycle) {
            case ConstantesArchitecte.CYCLE_2018_2020 -> verifier2018a2020PourArchitecte(date);
            case ConstantesArchitecte.CYCLE_2016_2018 -> verifier2016a2018PourArchitecte(date);
            case ConstantesArchitecte.CYCLE_2020_2022 -> verifier2020a2022PourArchitecte(date);
            default -> false;
        };
    }

    private boolean verifierSiDateCompriseEntre(String date, LocalDate debut, LocalDate fin) {
        boolean resultat;
        if ( LocalDate.parse(date).isBefore(debut) )
            resultat = false;
        else
            resultat = !LocalDate.parse(date).isAfter(fin);
        return resultat;
    }

    private boolean verifier2016a2018PourArchitecte(String date) {
       return verifierSiDateCompriseEntre(date,
               ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2016,
               ConstantesArchitecte.ARCHITECTE_DATE_FIN_2018);
    }

    private boolean verifier2018a2020PourArchitecte(String date) {
        return verifierSiDateCompriseEntre(date,
                ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2018,
                ConstantesArchitecte.ARCHITECTE_DATE_FIN_2020);
    }

    private boolean verifier2020a2022PourArchitecte(String date) {
        return verifierSiDateCompriseEntre(date,
                ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2020,
                ConstantesArchitecte.ARCHITECTE_DATE_FIN_2022);
    }

    private boolean estDateGeologueValide(String date) {
        if(this.cycle.equals(ConstantesGeologue.CYCLE_2018_2021))
            return verifier2018a2021PourGeologue(date);
        else
            return false;
    }

    private boolean verifier2018a2021PourGeologue(String date) {
        return verifierSiDateCompriseEntre(date,
                ConstantesGeologue.GEOLOGUE_DATE_DEBUT_2018,
                ConstantesGeologue.GEOLOGUE_DATE_DEBUT_2018);
    }

    private boolean estDatePshycologueValide(String date) {
        if(this.cycle.equals(ConstantesPsychologues.CYCLE_2018_2023))
            return verifier2018a2023PourPsychologue(date);
        else
            return false;
    }

    private boolean verifier2018a2023PourPsychologue(String date) {
        return verifierSiDateCompriseEntre(date,
                ConstantesPsychologues.PSYCHO_DATE_DEBUT_2018,
               ConstantesPsychologues.PSYCHO_DATE_FIN_2023);
    }
}
