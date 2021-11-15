package Service;

import Exception.NumeroDePermisInvalideException;
import Entite.Declaration;
import Entite.Reponse;
import Utils.Constantes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceValidation {

    public void validerDeclaration(Declaration declaration) {
        InterfaceVerification interfaceVerification = VerificationFactory.obtenirInstance(declaration.obtenirOrdre());
        validerNumeroDePermis(declaration.obtenirNumeroDePermis());
        interfaceVerification.verifier(declaration);
    }

    public void validerNumeroDePermis(String numeroDePermis) {
        Pattern pattern = Pattern.compile("^(A|R|S|Z)[0-9]{4}$");
        Matcher matcher = pattern.matcher(numeroDePermis);
        if ( ! matcher.find() ) {
            String message = ServiceMessages.messageErreurNumeroDePermis(numeroDePermis);
            Reponse.obtenirInstance().ajouterMessageErreur(message);
            ServiceReponse.ecrireFichierDeSortie(Constantes.ARG1,Reponse.obtenirInstance());
            System.out.println(message);
            System.exit(1);
        }
    }
}