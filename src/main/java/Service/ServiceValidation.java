package Service;

import Exception.NumeroDePermisInvalideException;
import Entite.Declaration;
import Entite.Reponse;
import Utils.Constantes;

public class ServiceValidation {

    public void validerDeclaration(Declaration declaration) {
        InterfaceVerification interfaceVerification = VerificationFactory.obtenirInstance(declaration.obtenirOrdre());
        validerNumeroDePermis(declaration.obtenirNumeroDePermis());
        interfaceVerification.verifier(declaration);
    }

    public void validerNumeroDePermis(String numeroDePermis) {
        try {
            verifierNumeroDePermis(numeroDePermis);
        } catch (NumeroDePermisInvalideException e) {
            System.out.println(e.getMessage());
            ServiceReponse.ecrireFichierDeSortie(Constantes.ARG1,Reponse.obtenirInstance());
            System.exit(1);
        }
    }

    public void verifierNumeroDePermis(String numeroDePermis) throws NumeroDePermisInvalideException {
        if ( ! estNumeroDePermisValide(numeroDePermis) ) {
            Reponse.obtenirInstance().ajouterMessageErreur(
                    ServiceMessages.messageErreurNumeroDePermis(numeroDePermis)
            );
            throw new NumeroDePermisInvalideException(
                    ServiceMessages.messageErreurNumeroDePermis(numeroDePermis)
            );
        }
    }

    public boolean estNumeroDePermisValide(String numeroDePermis) {
        boolean premier = estPremierCaractereValide(numeroDePermis.charAt(0));
        boolean suite = contient4chiffres(numeroDePermis.substring(1));
        return premier && suite;
    }

    public boolean estPremierCaractereValide(Character premier) {
        return Constantes.LISTE_LETTRE_NUMERO_DE_PERMIS.contains(premier);
    }

    public boolean contient4chiffres(String chaine) {
        if (chaine.length() != 4)
            return false;
        else {
            return estUnChiffre(chaine);
        }
    }

    public boolean estUnChiffre(String chaine) {
        try {
            Integer.parseInt(chaine);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
}