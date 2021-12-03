package Service;

import Entite.Activite;
import Entite.Declaration;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import Utils.ConstantesGeologue;
import Utils.ConstantesPsychologues;

public class ServiceMessages {

    public static String messageErreurCycleInvalide () {
        return "Le cycle entré n'est pas valide, " +
                "Le cycle doit être "+ Constantes.CYCLE_AUTORISE_POUR_ARCHITECTE;
    }

    public static String messageErreurCycleInvalidePsycho () {
        return "Le cycle entré n'est pas valide, " +
                "Le cycle doit être "+ ConstantesPsychologues.CYCLE_POUR_PSYCHOLOGUES;
    }

    public static String messageErreurCycleInvalideGeo () {
        return "Le cycle entré n'est pas valide, " +
                "Le cycle doit être de "+ ConstantesGeologue.ANNEE_DEBUT +
                " a "+ ConstantesGeologue.ANNEE_FIN;
    }

    public static String messageErreurDescription(Activite activite) {
        return "Erreur : L'activite "+activite.obtenirDescription() +
                "contient une description inferieur a 20 caracteres.";
    }

    public static String messageErreurNumeroDePermis(String numeroDePermis) {
        return "Le numero de permis "+ numeroDePermis +
                " n'est pas valide.";
    }

    public static String messageErreurNumeroDePermis(String numeroDePermis, String ordre) {
        return "Le numero de permis "+ numeroDePermis + "ne convient pas au éxigences "+
                " du l'ordre des "+ ordre;
    }

    public static String messageErreurDate(Activite activite, String ordre, String cycle) {
        return switch (ordre) {
            case ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES -> choisirMessageErreurArchitecteDate(activite, cycle);
            case ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES -> choisirMessageErreurGeologueDate(activite, cycle);
            case ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES -> choisirMessageErreurPsychologueDate(activite, cycle);
            default -> messageErreurDateDefault(activite);
        };
    }

    private static String choisirMessageErreurArchitecteDate(Activite activite, String cycle) {
        return switch (cycle) {
            case ConstantesArchitecte.CYCLE_2016_2018 -> messageErreurDateArchitecte2016a2018(activite);
            case ConstantesArchitecte.CYCLE_2018_2020 -> messageErreurDateArchitecte2018a2020(activite);
            case ConstantesArchitecte.CYCLE_2020_2022 -> messageErreurDateArchitecte2020a2022(activite);
            default -> messageErreurDateDefault(activite);
        };
    }

    private static String choisirMessageErreurGeologueDate(Activite activite, String cycle) {
        if ( cycle.equals(ConstantesGeologue.CYCLE_GEOLOGUE) )
            return  messageErreurDateGeologue2018a2021(activite);
        else
            return messageErreurDateDefault(activite);
    }

    public static String choisirMessageErreurPsychologueDate(Activite activite, String cycle) {
        if( cycle.equals(ConstantesPsychologues.CYCLE_POUR_PSYCHOLOGUES) )
            return messageErreurDatePsycho2018a2023(activite);
        else
            return messageErreurDateDefault(activite);
    }

    public static String messageErreurDateArchitecte2016a2018(Activite activite) {
        return " L'activité " + activite.obtenirDescription() + " realisée en date du " +
                activite.obtenirDate() + " a été faite en dehors des dates du cycle 2016-2018 " +
                " soit entre le " + ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2016 +
                " et " + ConstantesArchitecte.ARCHITECTE_DATE_FIN_2018;
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
                " soit entre le "+ ConstantesPsychologues.DATE_DEBUT_ACTIVITE_PSYCHO.toString() +
                " et "+ConstantesPsychologues.DATE_FIN_ACTIVITE_PSYCHO.toString();
    }

    public static String messageErreurDateDefault(Activite activite) {
        return " La date de l'activité "+activite.obtenirDescription() +
                " n'est pas valide. Verifier votre declaration";
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

    public static String messageErreurHeureTranfereNonSupporte(String ordre) {
        return "Les heures transfere ne sont pas supporté par l'ordre des "+ ordre;
    }

    public static String messageErreurNombreHeuresMinimumPourGroupeDeDiscussionGeo() {
        return "Le nombre d'heures entré pour Groupe De Discussion" + " est invalide, il doit être " +
                "supérieur ou egale a 1.";
    }
    public static String messageErreurNombreHeuresMinimumPourProjetGeo() {
        return "Le nombre d'heures entré pour Projet de Recherche" + " est invalide, il doit être " +
                "supérieur ou egale a 3.";
    }

    public static String messageErreurNombreHeuresMinimumPourCoursGeo() {
        return "Le nombre d'heures entré pour Cours" + " est invalide, il doit être " +
                "supérieur ou egale a 22.";
    }
    public static String messageErreurNombreHeuresPourCours() {
        return "Un minimum de 25 heures par cycle sont nécessaires dans la " +
                "catégorie cours";
    }
}
