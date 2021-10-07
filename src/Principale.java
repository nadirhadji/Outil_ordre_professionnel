import Entite.Activite;
import Entite.DeclarationJSON;

public class Principale {

    public static void main(String[] args) throws Exception{
        if (args.length < 2) {
            System.out.println("Invalid arguments");
            System.exit(1);
        }
        DeclarationJSON json = new DeclarationJSON(args[0], args[1]);
        json.load();
        System.out.println(json.getInfoGen("cycle"));
        System.out.println(json.getNombreActivites());
        System.out.println(json.getInfoActivites("categorie", 3));
    }
}
