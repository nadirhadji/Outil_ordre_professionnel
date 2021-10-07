import Entite.Activite;
import Entite.DeclarationJSON;

public class Principale {

    public static void main(String[] args) throws Exception{
        if (args.length < 2) {
            System.out.println("Argument invalide");
            System.exit(1);
        }
        DeclarationJSON json = new DeclarationJSON(args[0], args[1]);
        json.charger();
        System.out.println(json.ObtenirInfoGen("cycle"));

    }
}
