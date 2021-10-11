import Entite.DeclarationJSON;
import Entite.Declaration;
import Entite.Reponse;
import Service.ServiceValidationDeclaration;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Principale {

    public static void main(String[] args) {
        verifierSiArgumentExiste(args);
        DeclarationJSON declarationJSON = new DeclarationJSON(args[0], args[1]);
        charger(declarationJSON);
        Reponse reponse = new Reponse();
        Declaration general = new Declaration(declarationJSON);
        ServiceValidationDeclaration service = new ServiceValidationDeclaration();
        service.verifierDeclaration(general,reponse);
        reponse.ecrireFichierDeSortie(args[1]);
    }

    private static void verifierSiArgumentExiste(String[] args) {
        if (args.length < 2) {
            System.out.println("Argument invalide");
            System.exit(1);
        }
    }

    private static void charger(DeclarationJSON json) {
        try {
            json.charger();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
