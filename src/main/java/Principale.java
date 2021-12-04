import Service.ServiceDeclarationJSON;
import Entite.Declaration;
import Entite.Reponse;
import Service.ServiceReponse;
import Service.ServiceStatistique;
import Service.ServiceValidation;
import Utils.Constantes;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Principale {

    public static void main(String[] args) {
        run(args);
    }

    private static void run(String[] args) {
        switch (args[0]) {
            case "-S" -> genererStatistiques();
            case "-SR" -> reinitialiserStatistiques();
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

    private static void genererStatistiques() {
        initialiserFichierStatistique();
    }

    private static void initialiserFichierStatistique() {
        try {
            ServiceStatistique.initialiserFichier();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void reinitialiserStatistiques() {

    }

    private static ServiceDeclarationJSON initlialiserServiceJson() {
        ServiceDeclarationJSON serviceDeclarationJSON = null;
        try {
            serviceDeclarationJSON = new ServiceDeclarationJSON(Constantes.ARG0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return serviceDeclarationJSON;
    }
}
