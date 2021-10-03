import Service.ServiceValidationDeclaration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesteValidationNumeroDePermis {

    @Test
    void verifierNumeroDePermisValide() {
        String s = "A0000";
        boolean resultat = ServiceValidationDeclaration.verifierNumeroDePermis(s);
        Assertions.assertEquals(true,resultat);
    }

    @Test
    void verifierAvecQueDesLettres() {
        String s = "AAAAA";
        boolean resultat = ServiceValidationDeclaration.verifierNumeroDePermis(s);
        Assertions.assertEquals(false,resultat);
    }

    @Test
    void verifierAvecPlusDeCaracteresQuePrevu() {
        String s = "A00000";
        boolean resultat = ServiceValidationDeclaration.verifierNumeroDePermis(s);
        Assertions.assertEquals(false,resultat);
    }

    @Test
    void verifierAvecQueDesChiffres() {
        String s = "00000";
        boolean resultat = ServiceValidationDeclaration.verifierNumeroDePermis(s);
        Assertions.assertEquals(false, resultat);
    }


}
