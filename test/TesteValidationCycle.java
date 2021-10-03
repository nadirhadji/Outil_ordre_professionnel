import Service.ServiceValidationDeclaration;
import Utils.Constantes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesteValidationCycle {

    @Test
    public void verifierCycleValideAvecConstante() {
        boolean resultat = ServiceValidationDeclaration.verifierCycle(Constantes.CYCLE_AUTORISEE);
        Assertions.assertTrue(resultat);
    }

    @Test
    public void verifierCycleValideAvecVariable() {
        String s = "2020-2022";
        boolean resultat = ServiceValidationDeclaration.verifierCycle(s);
        Assertions.assertTrue(resultat);
    }

    @Test
    public void verifierCycleInvalide() {
        String s = "2021-2012";
        boolean resultat = ServiceValidationDeclaration.verifierCycle(s);
        Assertions.assertFalse(resultat);
    }
}
