import java.net.SocketOption;

public class ServiceMessages {

    public String messageErreurCycleInvalide () {
        String cycleInvalide = "Le cycle entré n'est pas valide, " +
                "Le cycle doit être \"2020-2022\"\n";
        return cycleInvalide;
    }

    public String messageErreurActiviteHorsDateReconnue() {
        String horsDate = " L'activité " + activite.obtenirCategorie() +
              " n'a pas été complétée dans le cycle prévue \"2020-2022\"\n";
        return horsDate;
    }

    public String erreurMessageCategorieNonReconnue(){
        String categorieNonReconnue = "L'activité "+
                activite.obtenirCategorie() + " n'est pas dans une "+
                "catégorie reconnue. Elle sera ignorée\n";
        return categorieNonReconnue;
    }

    public String messageErreurHeuresTransfereInferieurA0(){
        String heuresTransfereesNegatif = "Les heures "+
                "transférées du cycle précédent ne peuvent pas être négatif\n";
        return heuresTransfereesNegatif;
    }

    public String messageInfosHeuresTransfereSuperieurA7(){
        String tropDHeuresTransferees = "Les heures transférées du cycle "+
             "précédent dépassent 7. Uniquement 7 heures seront considérées\n";
        return  tropDHeuresTransferees;
    }
    //TODO mettre le bon NbHeures
    public String messageNombreHeuresTotalMoinsDe40(){
        String peuDHeuresTotal= "Incomplet, il manque + (40heures - NbHeures) " +
                        "pour compléter le cycle\n";
        return peuDHeuresTotal;
    }

    public String messageErreurNombreHeuresInsuffisantDansCategorie(){
        String nbrHeuresInsuffisantDansCategorie = "Le nombre d'Heures "+
                "déclarées dans les catégories: cours, atelier, séminaire,"+
                " colloque, conférence, lecture dirigée n'atteint pas 17\n";
        return nbrHeuresInsuffisantDansCategorie;
    }

    public String messageErreurNombreHeuresPourActiviteInvalide(){
        String nbrHeuresInvalide = "Le nombre d'heures entré pour " +
                activite.obtenirCategorie()) + " est invalide, il doit être"+
                        "supérieur ou égal à 1. L'activité sera ignorée\n";

    }

}
