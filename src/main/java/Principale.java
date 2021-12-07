import Service.ServiceDeclarationJSON;
import Entite.Declaration;
import Entite.Reponse;
import Service.ServiceReponse;
import Service.ServiceStatistique;
import Service.ServiceValidation;
import Utils.Constantes;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Principale {

    public static void main(String[] args) {
        run(args);
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
        ServiceDeclarationJSON serviceDeclarationJson = initlialiserServiceJson();
        Declaration declaration = serviceDeclarationJson.obtenirDeclaration();
        ServiceValidation service = new ServiceValidation();
        service.validerDeclaration(declaration);
        ServiceReponse.ecrireFichierDeSortie(args[1],Reponse.obtenirInstance());
    }

    private static ServiceDeclarationJSON initlialiserServiceJson() {
        ServiceDeclarationJSON serviceDeclarationJSON = null;
        try {
            serviceDeclarationJSON = new ServiceDeclarationJSON(Constantes.ARG0);
        } catch (Exception e) {
            if (e.getClass() == FileNotFoundException.class) {
                System.out.println("Le fichier " + Constantes.ARG0 + " n'existe pas");
                System.exit(-1);
            } else if (e.getClass() == ParseException.class) {
                System.out.println("Le format du fichier +" + Constantes.ARG0 + " ne respecte pas le format JSON");
                System.exit(-1);
            } else {
                System.out.println(e.getMessage());
                System.exit(-1);
            }
        }
        return serviceDeclarationJSON;
    }
}
