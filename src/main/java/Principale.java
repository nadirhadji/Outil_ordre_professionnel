import Service.ServiceJSON;
import Entite.Declaration;
import Entite.Reponse;
import Service.ServiceReponse;
import Service.ServiceValidation;
import Utils.Constantes;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class Principale {

    public static void main(String[] args) {
        verifierSiArgumentExiste(args);
        ServiceJSON serviceJson = new ServiceJSON(Constantes.ARG0);
        charger(serviceJson);
        Declaration declaration = new Declaration(serviceJson);
        ServiceValidation service = new ServiceValidation();
        service.validerDeclaration(declaration);
        ServiceReponse.ecrireFichierDeSortie(args[1],Reponse.obtenirInstance());
    }

    private static void verifierSiArgumentExiste(String[] args) {
        if (args.length < 2) {
            System.out.println("Argument invalide");
            System.exit(1);
        }
        else {
            Constantes.ARG0 = args[0];
            System.out.println(args[0]);
            Constantes.ARG1 = args[1];
            System.out.println(args[1]);
        }
    }

    private static void charger(ServiceJSON json) {
        try {
            json.charger();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
