package Service;

import Entite.Activite;
import Utils.Constantes;

public class ServiceMessages {

    public static String messageErreurCycleInvalide () {
        String cycleInvalide = "Le cycle entré n'est pas valide, " +
                "Le cycle doit être "+ Constantes.CYCLE_AUTORISEE +"\n";
        return cycleInvalide;
    }

    public static String messageErreurActiviteHorsDateReconnue(Activite activite) {
        String horsDate = " L'activité " + activite.obtenirCategorie() +
              " n'a pas été complétée entre le" +
                Constantes.DATE_DEBUT_ACTIVITE_AUTORISEE+ " et "+
                Constantes.DATE_FIN_ACTIVITE_AUTORISEE;
        return horsDate;
    }

    public static String erreurMessageCategorieNonReconnue(Activite activite){
        String categorieNonReconnue = "L'activité "+
                activite.obtenirCategorie() + " n'est pas dans une "+
                "catégorie reconnue. Elle sera ignorée\n";
        return categorieNonReconnue;
    }

    public static String messageErreurHeuresTransfereInferieurA0(){
        String heuresTransfereesNegatif = "Les heures "+
                "transférées du cycle précédent ne peuvent pas être négatif\n";
        return heuresTransfereesNegatif;
    }

    public static String messageInfosHeuresTransfereSuperieurA7(){
        String tropDHeuresTransferees = "Les heures transférées du cycle "+
             "précédent dépassent 7. Uniquement 7 heures seront considérées\n";
        return  tropDHeuresTransferees;
    }
    //TODO mettre le bon NbHeures
    public static String messageNombreHeuresTotalMoinsDe40() {
        String peuDHeuresTotal= "Incomplet, il manque + (40heures - NbHeures) " +
                        "pour compléter le cycle\n";
        return peuDHeuresTotal;
    }

    public static String messageErreurNombreHeuresInsuffisantDansCategorie(){
        String nbrHeuresInsuffisantDansCategorie = "Le nombre d'Heures "+
                "déclarées dans les catégories: cours, atelier, séminaire,"+
                " colloque, conférence, lecture dirigée n'atteint pas 17\n";
        return nbrHeuresInsuffisantDansCategorie;
    }

    public static String messageErreurNombreHeuresPourActiviteInvalide(Activite activite){
        String nbrHeuresInvalide = "Le nombre d'heures entré pour " +
                activite.obtenirCategorie() + " est invalide, il doit être"+
                        "supérieur ou égal à 1. L'activité sera ignorée\n";
        return nbrHeuresInvalide;
    }
}
