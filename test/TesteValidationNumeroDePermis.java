import Service.ServiceValidationDeclaration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesteValidationNumeroDePermis {

    ServiceValidationDeclaration serviceValidationDeclaration = new ServiceValidationDeclaration();

    @Test
    void verifierNumeroDePermisValide() {
        String s = "A0000";
        boolean resultat = serviceValidationDeclaration.estNumeroDePermisValide(s);
        Assertions.assertTrue(resultat);
    }

    @Test
    void verifierAvecQueDesLettres() {
        String s = "AAAAA";
        boolean resultat = serviceValidationDeclaration.estNumeroDePermisValide(s);
        Assertions.assertFalse(resultat);
    }

    @Test
    void verifierAvecPlusDeCaracteresQuePrevu() {
        String s = "A00000";
        boolean resultat = serviceValidationDeclaration.estNumeroDePermisValide(s);
        Assertions.assertFalse(resultat);
    }

    @Test
    void verifierAvecQueDesChiffres() {
        String s = "00000";
        boolean resultat = serviceValidationDeclaration.estNumeroDePermisValide(s);
        Assertions.assertFalse(resultat);
    }
}
