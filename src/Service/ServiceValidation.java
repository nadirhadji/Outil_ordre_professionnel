package Service;

import Entite.Declaration;
import Entite.Reponse;

public class ServiceValidation {

    public void validerDeclaration(Declaration declaration, Reponse reponse) {
        InterfaceVerification interfaceVerification = VerificationFactory.obtenirInstance(declaration.obtenirOrdre());
        interfaceVerification.verifier(declaration,reponse);
    }
}
