import Service.ServiceValidationDeclaration;
import Utils.Constantes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesteValidationCycle {

    ServiceValidationDeclaration serviceValidationDeclaration = new ServiceValidationDeclaration();

    @Test
    public void verifierCycleValideAvecConstante() {
        boolean resultat = serviceValidationDeclaration.estCycleValide(Constantes.CYCLE_AUTORISEE);
        Assertions.assertTrue(resultat);
    }

    @Test
    public void verifierCycleValideAvecVariable() {
        String s = "2020-2022";
        boolean resultat = serviceValidationDeclaration.estCycleValide(s);
        Assertions.assertTrue(resultat);
    }

    @Test
    public void verifierCycleInvalide() {
        String s = "2021-2012";
        boolean resultat = serviceValidationDeclaration.estCycleValide(s);
        Assertions.assertFalse(resultat);
    }
}
