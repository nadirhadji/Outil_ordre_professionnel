package Service;

import Entite.Declaration;

public class ServiceValidation {

    public void validerDeclaration(Declaration declaration) {
        String ordre = declaration.obtenirOrdre();
        InterfaceVerification interfaceVerification = VerificationFactory.obtenirInstance(ordre);
        interfaceVerification.verifier(declaration);
    }
}