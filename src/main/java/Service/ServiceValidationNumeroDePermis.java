package Service;

import Entite.Reponse;
import Utils.Constantes;
import Utils.RegexNumeroDePermis;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceValidationNumeroDePermis {

    public static boolean validerNumeroDePermis(String numeroDePermis, String ordre) {
        return switch (ordre) {
            case "architectes" -> verifierNumeroDePermis(numeroDePermis, RegexNumeroDePermis.ARCHITECTE);
            case "géologues", "podologue" -> verifierNumeroDePermis(numeroDePermis, RegexNumeroDePermis.GEOLOGUE_PODOLOGUE);
            case "psychologues" -> verifierNumeroDePermis(numeroDePermis, RegexNumeroDePermis.PSHYCOLOGUE);
            default -> false;
        };
    }

    private static boolean verifierNumeroDePermis(String numeroDePermis, String regex) {
        boolean estNumeroDePermisValide = estNumeroDePermisValide(numeroDePermis, regex);
        if (! estNumeroDePermisValide )
            finirExecution(numeroDePermis);
        return estNumeroDePermisValide;
    }

    private static boolean estNumeroDePermisValide(String numeroDePermis, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numeroDePermis);
        return matcher.find();
    }

    private static void finirExecution(String numeroDePermis) {
        String message = ServiceMessages.messageErreurNumeroDePermis(numeroDePermis);
        Reponse.obtenirInstance().ajouterMessageErreur(message);
        ServiceReponse.ecrireFichierDeSortie(Constantes.ARG1,Reponse.obtenirInstance());
        System.out.println(message);
        System.exit(5);
    }
}
