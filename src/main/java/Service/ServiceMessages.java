package Service;

import Entite.Activite;
import Entite.MessageErreur;
import Utils.*;

public class ServiceMessages {

    public static MessageErreur messageErreurSexeInvalide (){
        return new MessageErreur(CodeErreur.DECLARATION_INVALIDE,
                "Le champ sexe doit avoir seulement les valeurs 0,1,2");
    }

    public static MessageErreur messageErreurCycleInvalideArchitecte () {
        return new MessageErreur(CodeErreur.DECLARATION_INVALIDE,
                "Le cycle entrée n'est pas valide, " +
                "Le cycle doit être "+ Constantes.CYCLE_AUTORISE_POUR_ARCHITECTE);
    }

    public static MessageErreur messageErreurCycleInvalidePsycho () {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                "Le cycle entré n'est pas valide, " +
                "Le cycle doit être "+ ConstantesPsychologues.CYCLE_POUR_PSYCHOLOGUES);
    }

    public static MessageErreur messageErreurCycleInvalideGeo () {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                "Le cycle entré n'est pas valide, " +
                "Le cycle doit être de "+ ConstantesGeologue.ANNEE_DEBUT +
                " a "+ ConstantesGeologue.ANNEE_FIN);
    }

    public static MessageErreur messageErreurDescription(Activite activite) {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                "Erreur : L'activite "+activite.obtenirDescription() +
                "contient une description inferieur a 20 caracteres.");
    }

    public static MessageErreur messageErreurNumeroDePermis(String numeroDePermis) {
        return new MessageErreur( CodeErreur.ERREUR_NUMERO_PERMIS,
                "Le numero de permis "+ numeroDePermis + " n'est pas valide.");
    }

    public static MessageErreur messageErreurNumeroDePermis(String numeroDePermis, String ordre) {
        return new MessageErreur( CodeErreur.ERREUR_NUMERO_PERMIS,
                "Le numero de permis "+ numeroDePermis + "ne convient pas au éxigences "+
                " du l'ordre des "+ ordre);
    }

    public static MessageErreur messageErreurDate(Activite activite, String ordre, String cycle) {
        return switch (ordre) {
            case ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES -> choisirMessageErreurArchitecteDate(activite, cycle);
            case ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES -> choisirMessageErreurGeologueDate(activite, cycle);
            case ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES -> choisirMessageErreurPsychologueDate(activite, cycle);
            default -> messageErreurDateDefault(activite);
        };
    }

    private static MessageErreur choisirMessageErreurArchitecteDate(Activite activite, String cycle) {
        return switch (cycle) {
            case ConstantesArchitecte.CYCLE_2016_2018 -> messageErreurDateArchitecte2016a2018(activite);
            case ConstantesArchitecte.CYCLE_2018_2020 -> messageErreurDateArchitecte2018a2020(activite);
            case ConstantesArchitecte.CYCLE_2020_2022 -> messageErreurDateArchitecte2020a2022(activite);
            default -> messageErreurDateDefault(activite);
        };
    }

    private static MessageErreur choisirMessageErreurGeologueDate(Activite activite, String cycle) {
        if ( cycle.equals(ConstantesGeologue.CYCLE_GEOLOGUE) )
            return messageErreurDateGeologue2018a2021(activite);
        else
            return messageErreurDateDefault(activite);
    }

    public static MessageErreur choisirMessageErreurPsychologueDate(Activite activite, String cycle) {
        if( cycle.equals(ConstantesPsychologues.CYCLE_POUR_PSYCHOLOGUES) )
            return messageErreurDatePsycho2018a2023(activite);
        else
            return messageErreurDateDefault(activite);
    }

    public static MessageErreur messageErreurDateArchitecte2016a2018(Activite activite) {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                " L'activité " + activite.obtenirDescription() + " realisée en date du " +
                activite.obtenirDate() + " a été faite en dehors des dates du cycle 2016-2018 " +
                " soit entre le " + ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2016 +
                " et " + ConstantesArchitecte.ARCHITECTE_DATE_FIN_2018);
    }

    public static MessageErreur messageErreurDateArchitecte2018a2020(Activite activite) {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2018-2020 "+
                " soit entre le "+ ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2018.toString() +
                " et "+ConstantesArchitecte.ARCHITECTE_DATE_FIN_2020.toString());
    }

    public static MessageErreur messageErreurDateArchitecte2020a2022(Activite activite) {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2020-2022 "+
                " soit entre le "+ ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2020.toString() +
                " et "+ConstantesArchitecte.ARCHITECTE_DATE_FIN_2022.toString());
    }

    public static MessageErreur messageErreurDateGeologue2018a2021(Activite activite) {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2018-2021 "+
                " soit entre le "+ ConstantesGeologue.GEOLOGUE_DATE_DEBUT_2018.toString() +
                " et "+ConstantesGeologue.GEOLOGUE_DATE_FIN_2021.toString());
    }

    public static MessageErreur messageErreurDatePsycho2018a2023(Activite activite) {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2018-2023 "+
                " soit entre le "+ ConstantesPsychologues.DATE_DEBUT_ACTIVITE_PSYCHO.toString() +
                " et "+ConstantesPsychologues.DATE_FIN_ACTIVITE_PSYCHO.toString());
    }

    public static MessageErreur messageErreurDateDefault(Activite activite) {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                " La date de l'activité "+activite.obtenirDescription() +
                " n'est pas valide. Verifier votre declaration");
    }

    public static MessageErreur messageErreurActiviteDateNonReconnue(Activite activite) {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                " L'activité " + activite.obtenirDescription() +
                " n'a pas la Date en format ISO8601 (AAAA-MM-JJ)");
    }

    public static MessageErreur erreurMessageCategorieNonReconnue(Activite activite){
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                "L'activité "+ activite.obtenirDescription() +
                        " n'est pas dans unecatégorie reconnue. Elle sera ignorée");
    }

    public static MessageErreur messageErreurHeuresTransfereInferieurA0(){
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,"Les heures "+
                "transférées du cycle précédent ne peuvent pas être négatif");
    }

    public static MessageErreur messageInfosHeuresTransfereSuperieurA7(){
        return new MessageErreur( CodeErreur.ERREUR_ACCEPTE,"Les heures transférées du cycle "+
                "précédent dépassent 7. Uniquement 7 heures seront considérées");
    }

    public static MessageErreur messageNombreHeuresTotalMoinsDe40(int nombreHeuresManquante) {
        return new MessageErreur( CodeErreur.DECLARATION_INCOMPLETE,"Incomplet, il manque " +
                nombreHeuresManquante +
                " heures pour compléter le cycle");
    }

    public static MessageErreur messageErreurHeuresDansActiviteDeGroupe(){
        return new MessageErreur( CodeErreur.DECLARATION_INCOMPLETE,"Le nombre d'heures "+
                "déclarées dans les catégories: cours, atelier, séminaire,"+
                " colloque, conférence, lecture dirigée n'atteint pas 17");
    }

    public static MessageErreur messageErreurNombreHeuresPourActiviteNegatif(Activite activite) {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,"Le nombre d'heures entré pour " +
                activite.obtenirCategorie() + " est invalide, il doit être "+
                "supérieur ou égal à 1. L'activité sera ignorée");
    }

    public static MessageErreur messageErreurNombreHeuresPourActiviteSuperieurAuMaximum(Activite activite) {
        return new MessageErreur( CodeErreur.ERREUR_ACCEPTE,"Le nombre d'heures entré pour " +
                activite.obtenirCategorie() + " est invalide, il doit être "+
                "supérieur au maximum 10. Seulement 10 heures seront prise en compte.");
    }

    public static MessageErreur messageErreurHeureTranfereNonSupporte(String ordre) {
        return new MessageErreur( CodeErreur.ERREUR_ACCEPTE,
                "Les heures transfere ne sont pas supporté " +
                        "par l'ordre des "+ ordre);
    }

    public static MessageErreur messageErreurNombreHeuresMinimumPourGroupeDeDiscussionGeo() {
        return new MessageErreur( CodeErreur.DECLARATION_INCOMPLETE,
                "Le nombre d'heures entré pour Groupe De Discussion " +
                        "est invalide, il doit être supérieur ou egale a 1.");
    }

    public static MessageErreur messageErreurNombreHeuresMinimumPourProjetGeo() {
        return new MessageErreur( CodeErreur.DECLARATION_INCOMPLETE,
                "Le nombre d'heures entré pour Projet de Recherche"
                        + " est invalide, il doit être supérieur ou egale a 3.");
    }

    public static MessageErreur messageErreurNombreHeuresMinimumPourCoursGeo() {
        return new MessageErreur( CodeErreur.DECLARATION_INCOMPLETE,
                "Le nombre d'heures entré pour Cours est invalide, " +
                        "il doit être supérieur ou egale a 22.");
    }

    public static MessageErreur messageErreurNombreHeuresPourCours() {
        return new MessageErreur( CodeErreur.DECLARATION_INCOMPLETE,
                "Un minimum de 25 heures par cycle sont nécessaires " +
                        "dans la catégorie cours");
    }

    public static MessageErreur messageErreurChampDansJsonInexistante(String champ) {
        return new MessageErreur( CodeErreur.DECLARATION_INVALIDE,
                "Le champ "+ champ +"est inexistant dans votre fichier d'entrée.");
    }

    public static MessageErreur messageErreurNumPermisGeoLettres() {
        return new MessageErreur( CodeErreur.ERREUR_NUMERO_PERMIS,
                "Le numero de Permis est invalide car les deux " +
                        "premieres lettres ne correspondent " +
                        "pas aux premieres lettres du nom et prenom");
    }
}
