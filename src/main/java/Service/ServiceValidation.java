package Service;

import Entite.Declaration;

public class ServiceValidation {

    public void validerDeclaration(Declaration declaration) {
        String nom = declaration.obtenirNom();
        String prenom = declaration.obtenirPrenom();
        int sexe = declaration.obtenirSexe();
        String ordre = declaration.obtenirOrdre();
        String cycle = declaration.obtenirCycle();
        InterfaceVerification interfaceVerification = VerificationFactory.obtenirInstance(ordre,cycle);
        interfaceVerification.verifiationGeneral(declaration);
        interfaceVerification.verificationDesActivite(declaration);
        interfaceVerification.verifierSpecifiqueOrdre(declaration);
    }
}