package Service;

import Entite.Activite;
import Utils.Constantes;

public class ServiceMessages {

    public static String messageErreurCycleInvalide () {
        return "Le cycle entré n'est pas valide, " +
                "Le cycle doit être "+ Constantes.CYCLE_AUTORISEE +"\n";
    }

    public static String messageErreurActiviteHorsDateReconnue(Activite activite) {
        return " L'activité " + activite.obtenirCategorie() +
              " n'a pas été complétée entre le" +
                Constantes.DATE_DEBUT_ACTIVITE_AUTORISEE+ " et "+
                Constantes.DATE_FIN_ACTIVITE_AUTORISEE;
    }

    public static String erreurMessageCategorieNonReconnue(Activite activite){
        return "L'activité "+
                activite.obtenirCategorie() + " n'est pas dans une "+
                "catégorie reconnue. Elle sera ignorée\n";
    }

    public static String messageErreurHeuresTransfereInferieurA0(){
        return "Les heures "+
                "transférées du cycle précédent ne peuvent pas être négatif\n";
    }

    public static String messageInfosHeuresTransfereSuperieurA7(){
        return "Les heures transférées du cycle "+
             "précédent dépassent 7. Uniquement 7 heures seront considérées\n";
    }

    public static String messageNombreHeuresTotalMoinsDe40(int nombreHeuresManquante) {
        return "Incomplet, il manque " +
                nombreHeuresManquante +
                "pour compléter le cycle\n";
    }

    public static String messageErreurHeuresDansActiviteDeGroupe(){
        return "Le nombre d'Heures "+
                "déclarées dans les catégories: cours, atelier, séminaire,"+
                " colloque, conférence, lecture dirigée n'atteint pas 17\n";
    }

    public static String messageErreurNombreHeuresPourActiviteInvalide(Activite activite){
        return "Le nombre d'heures entré pour " +
                activite.obtenirCategorie() + " est invalide, il doit être"+
                        "supérieur ou égal à 1. L'activité sera ignorée\n";
    }
}
