package Service;

import Entite.Activite;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import Utils.ConstantesGeologue;
import Utils.ConstantesPsychologues;

public class ServiceMessages {

    public static String messageErreurCycleInvalide () {
        return "Le cycle entré n'est pas valide, " +
                "Le cycle doit être "+ Constantes.CYCLE_AUTORISEE;
    }

    public static String messageErreurDate(String desciption, String date, String debut, String fin) {
        return " L'activité "+desciption+" realisée en date du "+
                date+ " a été faite en dehors des dates du cycle 2016-2018 "+
                " soit entre le "+ debut +
                " et "+fin;
    }

    public static String messageErreurDateArchitecte2016a2018(Activite activite) {
        return " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2016-2018 "+
                " soit entre le "+ ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2016.toString() +
                " et "+ConstantesArchitecte.ARCHITECTE_DATE_FIN_2018.toString();
    }

    public static String messageErreurDateArchitecte2018a2020(Activite activite) {
        return " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2018-2020 "+
                " soit entre le "+ ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2018.toString() +
                " et "+ConstantesArchitecte.ARCHITECTE_DATE_FIN_2020.toString();
    }

    public static String messageErreurDateArchitecte2020a2022(Activite activite) {
        return " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2020-2022 "+
                " soit entre le "+ ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2020.toString() +
                " et "+ConstantesArchitecte.ARCHITECTE_DATE_FIN_2022.toString();
    }

    public static String messageErreurDateGeologue2018a2021(Activite activite) {
        return " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2018-2021 "+
                " soit entre le "+ ConstantesGeologue.GEOLOGUE_DATE_DEBUT_2018.toString() +
                " et "+ConstantesGeologue.GEOLOGUE_DATE_FIN_2021.toString();
    }

    public static String messageErreurDatePsycho2018a2023(Activite activite) {
        return " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2018-2023 "+
                " soit entre le "+ ConstantesPsychologues.PSYCHO_DATE_DEBUT_2018.toString() +
                " et "+ConstantesPsychologues.PSYCHO_DATE_FIN_2023.toString();
    }

    public static String messageErreurActiviteHorsDateReconnue(Activite activite) {
        return " L'activité " + activite.obtenirCategorie() +
              " n'a pas été complétée entre le" +
                Constantes.DATE_DEBUT_ACTIVITE_AUTORISEE+ " et "+
                Constantes.DATE_FIN_ACTIVITE_AUTORISEE;
    }

    public static String messageErreurActiviteDateNonReconnue(Activite activite) {
        return " L'activité " + activite.obtenirDescription() +
                " n'a pas la Date en format ISO8601 (AAAA-MM-JJ)";
    }

    public static String erreurMessageCategorieNonReconnue(Activite activite){
        return "L'activité "+
                activite.obtenirDescription() + " n'est pas dans une "+
                "catégorie reconnue. Elle sera ignorée";
    }

    public static String messageErreurHeuresTransfereInferieurA0(){
        return "Les heures "+
                "transférées du cycle précédent ne peuvent pas être négatif";
    }

    public static String messageInfosHeuresTransfereSuperieurA7(){
        return "Les heures transférées du cycle "+
             "précédent dépassent 7. Uniquement 7 heures seront considérées";
    }

    public static String messageNombreHeuresTotalMoinsDe40(int nombreHeuresManquante) {
        return "Incomplet, il manque " +
                nombreHeuresManquante +
                " heures pour compléter le cycle";
    }

    public static String messageErreurHeuresDansActiviteDeGroupe(){
        return "Le nombre d'Heures "+
                "déclarées dans les catégories: cours, atelier, séminaire,"+
                " colloque, conférence, lecture dirigée n'atteint pas 17";
    }

    public static String messageErreurNombreHeuresPourActiviteNegatif(Activite activite) {
        return "Le nombre d'heures entré pour " +
                activite.obtenirCategorie() + " est invalide, il doit être "+
                        "supérieur ou égal à 1. L'activité sera ignorée";
    }

    public static String messageErreurNombreHeuresPourActiviteSuperieurAuMaximum(Activite activite) {
        return "Le nombre d'heures entré pour " +
                activite.obtenirCategorie() + " est invalide, il doit être "+
                "supérieur au maximum 10. Seulement 10 heures seront prise en compte.";
    }
}
