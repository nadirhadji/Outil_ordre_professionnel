package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.Reponse;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import Utils.ConstantesGeologue;
import Utils.ConstantesPsychologues;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceValidationActivite {

    private String ordre;
    private String cycle;

    public ServiceValidationActivite(String ordre, String cycle) {
        this.ordre = ordre;
        this.cycle = cycle;
    }

    public void enregistrerCycle(String cycle) {
        this.cycle = cycle;
    }

    public void reinitialiserContexte(String ordre, String cycle ) {
        this.ordre = ordre;
        this.cycle = cycle;
    }

    /**
     * L'odre l'execution des méthode des important.
     * Il faut commencer par le nombre d'heures de l'activite
     * @param activite
     */
    public void verifierActivite(Activite activite) {
        verifierNombreHeurePourActivite(activite);
        verifierDescription(activite);
        verifierDateActivite(activite);
        verifierCategorie(activite);
    }

    /*################## Service.Verification de la description #####################*/
    public void verifierDescription(Activite activite) {
        if (activite.obtenirDescription().length() < 20 ) {
            ServiceFinExecutionFatale.finExecutionDescription(
                    ServiceMessages.messageErreurDescription(activite).getErreur()
            );
        }
    }

    /*#################### Service.Verification de la categorie #####################*/
    public void verifierCategorie(Activite activite) {
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
    public void verifierNombreHeurePourActivite(Activite activite) {
        verifierNombreHeureNegatif(activite);
        verifierNombreHeuresMaximum(activite);
    }

    public void verifierNombreHeureNegatif(Activite activite) {
        if ( activite.obtenirHeures() < 0) {
            String message = ServiceMessages.messageErreurNombreHeuresPourActiviteNegatif(activite).getErreur();
            ServiceFinExecutionFatale.finExecutionHeuresNegatives(message);
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
        return nombreHeures > Constantes.NOMBRE_HEURE_ACTIVITE_MAX_PAR_JOUR;
    }

    /*############## Service.Verification la date d'une activité ##################*/
    public void verifierDateActivite(Activite activite) {
        String date = activite.obtenirDate();

        if ( estformatDateInvalide(date) ) {
            activite.ignorerActivite();
            Reponse.obtenirInstance().ajouterMessageInformation(
                    ServiceMessages.messageErreurActiviteDateNonReconnue(activite));
        }
        else if ( ! estDateValide(date)) {
            activite.ignorerActivite();
            Reponse.obtenirInstance().ajouterMessageInformation(
                    ServiceMessages.messageErreurDate(activite,ordre,cycle));
        }
    }

    public boolean estformatDateInvalide(String date){
        Pattern pattern = Pattern.compile("^([0-9]{4})(-)(1[0-2]|0[1-9])\\2(3[01]|0[1-9]|[12][0-9])$");
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()) {
            return estDateInexistante(date);
        }
        else
            return true;
    }

    public boolean estDateInexistante( String date) {
        try {
            LocalDate.parse(date);
            return false;
        } catch (java.time.format.DateTimeParseException e) {
            return true;
        }
    }

    public boolean estDateValide(String date) {
        return switch (ordre) {
            case ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES -> estDateArchitecteValide(date);
            case ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES -> estDateGeologueValide(date);
            case ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES -> estDatePshycologueValide(date);
            default -> false;
        };
    }

    public boolean estDateArchitecteValide(String date) {
        return switch (this.cycle) {
            case ConstantesArchitecte.CYCLE_2018_2020 -> verifier2018a2020PourArchitecte(date);
            case ConstantesArchitecte.CYCLE_2016_2018 -> verifier2016a2018PourArchitecte(date);
            case ConstantesArchitecte.CYCLE_2020_2022 -> verifier2020a2022PourArchitecte(date);
            default -> false;
        };
    }

    public boolean verifierSiDateCompriseEntre(String date, LocalDate debut, LocalDate fin) {
        LocalDate localDate = LocalDate.parse(date);
        return (localDate.isAfter(debut) || localDate.isEqual(debut)) &&
                (localDate.isBefore(fin) || localDate.isEqual(fin));
    }

    public boolean verifier2016a2018PourArchitecte(String date) {
       return verifierSiDateCompriseEntre(date,
               ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2016,
               ConstantesArchitecte.ARCHITECTE_DATE_FIN_2018);
    }

    public boolean verifier2018a2020PourArchitecte(String date) {
        return verifierSiDateCompriseEntre(date,
                ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2018,
                ConstantesArchitecte.ARCHITECTE_DATE_FIN_2020);
    }

    public boolean verifier2020a2022PourArchitecte(String date) {
        return verifierSiDateCompriseEntre(date,
                ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2020,
                ConstantesArchitecte.ARCHITECTE_DATE_FIN_2022);
    }

    public boolean estDateGeologueValide(String date) {
        if(this.cycle.equals(ConstantesGeologue.CYCLE_GEOLOGUE)) {
            return verifier2018a2021PourGeologue(date);
        }
        else
            return false;
    }

    public boolean verifier2018a2021PourGeologue(String date) {
        return verifierSiDateCompriseEntre(date,
                ConstantesGeologue.GEOLOGUE_DATE_DEBUT_2018,
                ConstantesGeologue.GEOLOGUE_DATE_FIN_2021);
    }

    public boolean estDatePshycologueValide(String date) {
        if(this.cycle.equals(ConstantesPsychologues.CYCLE_POUR_PSYCHOLOGUES))
            return verifier2018a2023PourPsychologue(date);
        else
            return false;
    }

    public boolean verifier2018a2023PourPsychologue(String date) {
        return verifierSiDateCompriseEntre(date,
                ConstantesPsychologues.DATE_DEBUT_ACTIVITE_PSYCHO,
               ConstantesPsychologues.DATE_FIN_ACTIVITE_PSYCHO);
    }
}
