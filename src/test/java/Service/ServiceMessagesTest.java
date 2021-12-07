package Service;

import Entite.Activite;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import Utils.ConstantesGeologue;
import Utils.ConstantesPsychologues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ServiceMessagesTest {

    Activite activite = new Activite("description","cours",4,"2020-02-02");

    @Test
    void messageErreurSexeInvalide(){
       String message = "Le champ sexe doit avoir seulement les valeurs 0,1,2";
       Assertions.assertEquals(message, ServiceMessages.messageErreurSexeInvalide());
    }

    @Test
    void messageErreurCycleInvalide() {
        String message = "Le cycle entré n'est pas valide, " +
                "Le cycle doit être "+ Constantes.CYCLE_AUTORISE_POUR_ARCHITECTE;
        Assertions.assertEquals(message, ServiceMessages.messageErreurCycleInvalideArchitecte());
    }

    @Test
    void messageErreurCycleInvalidePsycho() {
        String message = "Le cycle entré n'est pas valide, " +
                "Le cycle doit être "+ ConstantesPsychologues.CYCLE_POUR_PSYCHOLOGUES;
        Assertions.assertEquals(message,ServiceMessages.messageErreurCycleInvalidePsycho());
    }

    @Test
    void messageErreurCycleInvalideGeo() {
        String message = "Le cycle entré n'est pas valide, " +
                "Le cycle doit être de "+ ConstantesGeologue.ANNEE_DEBUT +
                " a "+ ConstantesGeologue.ANNEE_FIN;
        Assertions.assertEquals(message,ServiceMessages.messageErreurCycleInvalideGeo());
    }

    @Test
    void messageErreurDescription() {
        String message = "Erreur : L'activite "+activite.obtenirDescription() +
                "contient une description inferieur a 20 caracteres.";
        Assertions.assertEquals(message,ServiceMessages.messageErreurDescription(activite));
    }

    @Test
    void messageErreurNumeroDePermis() {
        String numeroDePermis = "DOIQ"; //invalide numero de permis
        String message = "Le numero de permis "+ numeroDePermis +
                " n'est pas valide.";
        Assertions.assertEquals(message,ServiceMessages.messageErreurNumeroDePermis(numeroDePermis));
    }

    @Test
    void messageErreurDate() {
    }

    @Test
    void messageErreurDateArchitecte2016a2018() {
        String message = " L'activité " + activite.obtenirDescription() + " realisée en date du " +
                activite.obtenirDate() + " a été faite en dehors des dates du cycle 2016-2018 " +
                " soit entre le " + ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2016 +
                " et " + ConstantesArchitecte.ARCHITECTE_DATE_FIN_2018;
        Assertions.assertEquals(message,ServiceMessages.messageErreurDateArchitecte2016a2018(activite));
    }

    @Test
    void messageErreurDateArchitecte2018a2020() {
        String message = " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2018-2020 "+
                " soit entre le "+ ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2018.toString() +
                " et "+ConstantesArchitecte.ARCHITECTE_DATE_FIN_2020.toString();
        Assertions.assertEquals(message,ServiceMessages.messageErreurDateArchitecte2018a2020(activite));
    }

    @Test
    void messageErreurDateArchitecte2020a2022() {
        String message = " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2020-2022 "+
                " soit entre le "+ ConstantesArchitecte.ARCHITECTE_DATE_DEBUT_2020.toString() +
                " et "+ConstantesArchitecte.ARCHITECTE_DATE_FIN_2022.toString();
        Assertions.assertEquals(message,ServiceMessages.messageErreurDateArchitecte2020a2022(activite));
    }

    @Test
    void messageErreurDateGeologue2018a2021() {
        String message = " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2018-2021 "+
                " soit entre le "+ ConstantesGeologue.GEOLOGUE_DATE_DEBUT_2018.toString() +
                " et "+ConstantesGeologue.GEOLOGUE_DATE_FIN_2021.toString();
        Assertions.assertEquals(message,ServiceMessages.messageErreurDateGeologue2018a2021(activite));
    }

    @Test
    void messageErreurDatePsycho2018a2023() {
        String message = " L'activité "+activite.obtenirDescription()+" realisée en date du "+
                activite.obtenirDate()+ " a été faite en dehors des dates du cycle 2018-2023 "+
                " soit entre le "+ ConstantesPsychologues.DATE_DEBUT_ACTIVITE_PSYCHO.toString() +
                " et "+ConstantesPsychologues.DATE_FIN_ACTIVITE_PSYCHO.toString();
        Assertions.assertEquals(message,ServiceMessages.messageErreurDatePsycho2018a2023(activite));
    }

    @Test
    void messageErreurDateDefault() {
        String message = " La date de l'activité "+activite.obtenirDescription() +
                " n'est pas valide. Verifier votre declaration";
        Assertions.assertEquals(message,ServiceMessages.messageErreurDateDefault(activite));
    }

    @Test
    void messageErreurActiviteDateNonReconnue() {
        String message = " L'activité " + activite.obtenirDescription() +
                " n'a pas la Date en format ISO8601 (AAAA-MM-JJ)";
        Assertions.assertEquals(message,ServiceMessages.messageErreurActiviteDateNonReconnue(activite));
    }

    @Test
    void erreurMessageCategorieNonReconnue() {
        String message =  "L'activité "+
                activite.obtenirDescription() + " n'est pas dans une "+
                "catégorie reconnue. Elle sera ignorée";
        Assertions.assertEquals(message,ServiceMessages.erreurMessageCategorieNonReconnue(activite));
    }

    @Test
    void messageErreurHeuresTransfereInferieurA0() {
        String message = "Les heures "+
                "transférées du cycle précédent ne peuvent pas être négatif";
        Assertions.assertEquals(message,ServiceMessages.messageErreurHeuresTransfereInferieurA0());
    }

    @Test
    void messageInfosHeuresTransfereSuperieurA7() {
        String message = "Les heures transférées du cycle "+
                "précédent dépassent 7. Uniquement 7 heures seront considérées";
        Assertions.assertEquals(message,ServiceMessages.messageInfosHeuresTransfereSuperieurA7());
    }

    @Test
    void messageNombreHeuresTotalMoinsDe40() {
        int nombreHeuresManquante = 10;
        String message = "Incomplet, il manque " +
                nombreHeuresManquante +
                " heures pour compléter le cycle";
        Assertions.assertEquals(message,ServiceMessages.messageNombreHeuresTotalMoinsDe40(nombreHeuresManquante));
    }

    @Test
    void messageErreurHeuresDansActiviteDeGroupe() {
        String message = "Le nombre d'Heures "+
                "déclarées dans les catégories: cours, atelier, séminaire,"+
                " colloque, conférence, lecture dirigée n'atteint pas 17";
        Assertions.assertEquals(message,ServiceMessages.messageErreurHeuresDansActiviteDeGroupe());
    }

    @Test
    void messageErreurNombreHeuresPourActiviteNegatif() {
        String message = "Le nombre d'heures entré pour " +
                activite.obtenirCategorie() + " est invalide, il doit être "+
                "supérieur ou égal à 1. L'activité sera ignorée";
        Assertions.assertEquals(message,ServiceMessages.messageErreurNombreHeuresPourActiviteNegatif(activite));
    }

    @Test
    void messageErreurNombreHeuresPourActiviteSuperieurAuMaximum() {
        String message = "Le nombre d'heures entré pour " +
                activite.obtenirCategorie() + " est invalide, il doit être "+
                "supérieur au maximum 10. Seulement 10 heures seront prise en compte.";
        Assertions.assertEquals(message,ServiceMessages.messageErreurNombreHeuresPourActiviteSuperieurAuMaximum(activite));
    }

    @Test
    void messageErreurHeureTranfereNonSupporte() {
        String ordre = "architectes";
        String message = "Les heures transfere ne sont pas supporté par l'ordre des "+ ordre;
        Assertions.assertEquals(message,ServiceMessages.messageErreurHeureTranfereNonSupporte(ordre));
    }

    @Test
    void messageErreurNombreHeuresMinimumPourGroupeDeDiscussionGeo() {
        String message = "Le nombre d'heures entré pour Groupe De Discussion" + " est invalide, il doit être " +
                "supérieur ou egale a 1.";
        Assertions.assertEquals(message,ServiceMessages.messageErreurNombreHeuresMinimumPourGroupeDeDiscussionGeo());
    }

    @Test
    void messageErreurNombreHeuresMinimumPourProjetGeo() {
        String message = "Le nombre d'heures entré pour Projet de Recherche" + " est invalide, il doit être " +
                "supérieur ou egale a 3.";
        Assertions.assertEquals(message,ServiceMessages.messageErreurNombreHeuresMinimumPourProjetGeo());
    }

    @Test
    void messageErreurNombreHeuresMinimumPourCoursGeo() {
        String message = "Le nombre d'heures entré pour Cours" + " est invalide, il doit être " +
                "supérieur ou egale a 22.";
        Assertions.assertEquals(message,ServiceMessages.messageErreurNombreHeuresMinimumPourCoursGeo());
    }

    @Test
    void messageErreurNombreHeuresPourCours() {
        String message = "Un minimum de 25 heures par cycle sont nécessaires dans la " +
                "catégorie cours";
        Assertions.assertEquals(message,ServiceMessages.messageErreurNombreHeuresPourCours());
    }
}