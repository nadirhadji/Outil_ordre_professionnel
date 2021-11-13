package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.Reponse;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import Utils.ConstantesGeologue;
import Utils.ConstantesPsychologues;
import java.time.LocalDate;

public class ServiceValidationActivite {

    private final String ordre;
    private final String cycle;

    public ServiceValidationActivite(String ordre, String cycle) {
        this.ordre = ordre;
        this.cycle = cycle;
    }

    public void verifierActivite(Activite activite) {
        verifierDescription(activite);
        verifierDateActivite(activite);
        verifierNombreHeurePourActivite(activite);
        verifierCategorie(activite);
    }

    /*################## Service.Verification de la description #####################*/

    private void verifierDescription(Activite activite) {
        if (activite.obtenirDescription().length() < 20 ) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurDescription(activite)
            );
        }
    }

    /*#################### Service.Verification de la categorie #####################*/
    private void verifierCategorie(Activite activite) {
        if ( ! estUneCategorieReconnue(activite.obtenirCategorie()) ) {
            Reponse.obtenirInstance().ajouterMessageInformation(
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

    private void verifierNombreHeurePourActivite(Activite activite) {
        verifierNombreHeureNegatif(activite);
        verifierNombreHeuresMaximum(activite);
    }

    public void verifierNombreHeureNegatif(Activite activite) {
        if ( activite.obtenirHeures() < 0) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurNombreHeuresPourActiviteNegatif(activite)
            );
        }
    }

    public void verifierNombreHeuresMaximum(Activite activite) {
        int nombresHeures = activite.obtenirHeures();
        if(aNombreHeuresSuperieurAuMaximum(nombresHeures)) {
            Reponse.obtenirInstance().ajouterMessageInformation(
                    ServiceMessages.messageErreurNombreHeuresPourActiviteSuperieurAuMaximum(
                            activite));
            activite.decrementerNombresHeuresA10();
        }
    }

    public boolean aNombreHeuresSuperieurAuMaximum(int nombreHeures) {
        return nombreHeures > Constantes.NOMBRE_HEURE_MAXIMALE_POUR_UNE_ACTIVITE;
    }

    /*############## Service.Verification la date d'une activité ##################*/
    private void verifierDateActivite(Activite activite) {
        String date = activite.obtenirDate();
        if ( ! estformatDateValide(date) ) {
            activite.ignorerActivite();
            Reponse.obtenirInstance().ajouterMessageInformation(
                    ServiceMessages.messageErreurActiviteDateNonReconnue(activite));
        }
        else if ( !estDateValide(date)) {
            activite.ignorerActivite();
            Reponse.obtenirInstance().ajouterMessageInformation(
                    ServiceMessages.messageErreurDate(activite,ordre,cycle));
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
        if( ordre.equals(ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES) ) {
            return estDateArchitecteValide(date);
        }

        else if ( ordre.equals(ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES) ) {
            return estDateGeologueValide(date);
        }
        else if ( ordre.equals(ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES) ) {
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
        if ( LocalDate.parse(date).isBefore(debut) ) {
            resultat = false;
        }
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
        if(this.cycle.equals(ConstantesGeologue.CYCLE_GEOLOGUE)) {
            return verifier2018a2021PourGeologue(date);
        }
        else
            return false;
    }

    private boolean verifier2018a2021PourGeologue(String date) {
        return verifierSiDateCompriseEntre(date,
                ConstantesGeologue.GEOLOGUE_DATE_DEBUT_2018,
                ConstantesGeologue.GEOLOGUE_DATE_FIN_2021);
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
