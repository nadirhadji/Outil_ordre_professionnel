import Entite.Statistique;
import Service.*;
import Entite.Declaration;
import Entite.Reponse;
import Utils.Constantes;

public class Principale {

    public static void main(String[] args) {
        run(args);
        Statistique statistique = new Statistique();
        ServiceEcriture.ecrireFichierStatistique(ServiceStatistique.PATH_TO_STATS, statistique);
    }

    private static void run(String[] args) {
        switch (args[0]) {
            case "-S" -> ServiceStatistique.afficher();
            case "-SR" -> ServiceStatistique.reinitialiser();
            default -> {
                verifierSyntaxe(args);
                verifierDeclaration(args);
            }
        }
    }

    private static void verifierSyntaxe(String[] args) {
        if (args.length == 2) {
            Constantes.ARG0 = args[0];
            Constantes.ARG1 = args[1];
        }
        else {
            System.out.println("Argument invalide");
            System.exit(1);
        }
    }

    private static void verifierDeclaration(String[] args) {
        ServiceDeclarationJSON declarationJSON = new ServiceDeclarationJSON(args[0]);
        Declaration declaration = declarationJSON.obtenirDeclaration();
        ServiceValidation service = new ServiceValidation();
        service.validerDeclaration(declaration);
        ServiceStatistique.mettreAjour(declaration);
        ServiceEcriture.ecrireFichierReponse(args[1],Reponse.obtenirInstance());
    }
}
