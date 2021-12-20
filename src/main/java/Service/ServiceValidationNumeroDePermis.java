package Service;

import Entite.MessageErreur;
import Entite.Reponse;
import Utils.Constantes;
import Utils.RegexNumeroDePermis;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceValidationNumeroDePermis {

    public static boolean architecte(String numeroDePermis) {
        return verifierNumeroDePermis(numeroDePermis, RegexNumeroDePermis.ARCHITECTE);
    }

    public static boolean geologue(String numeroDePermis) {
        return verifierNumeroDePermis(numeroDePermis, RegexNumeroDePermis.GEOLOGUE);
    }

    public static boolean podiatre(String numeroDePermis) {
        return verifierNumeroDePermis(numeroDePermis, RegexNumeroDePermis.PODIATRE);
    }

    public static boolean psychologues(String numeroDePermis) {
        return verifierNumeroDePermis(numeroDePermis, RegexNumeroDePermis.PSHYCOLOGUE);
    }

    private static boolean verifierNumeroDePermis(String numeroDePermis, String regex) {
        boolean estNumeroDePermisValide = estNumeroDePermisValide(numeroDePermis, regex);
        if (! estNumeroDePermisValide ) {
            MessageErreur message = ServiceMessages.messageErreurNumeroDePermis(numeroDePermis);
            System.out.println(message.getErreur());
            ServiceFinExecutionFatale.finExecutionPermisInvalide(message);
        }
        return estNumeroDePermisValide;
    }

    private static boolean estNumeroDePermisValide(String numeroDePermis, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(numeroDePermis);
        return matcher.find();
    }
}
