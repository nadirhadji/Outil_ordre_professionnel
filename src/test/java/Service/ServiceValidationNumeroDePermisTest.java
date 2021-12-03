package Service;

import Entite.Reponse;
import Utils.Constantes;
import com.github.stefanbirkner.systemlambda.SystemLambda;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ServiceValidationNumeroDePermisTest {

    @BeforeEach
    void init() {
        Constantes.ARG1 = "src/test/ressources/reponse.json";
    }

    @AfterEach
    void detruire() {
        Constantes.ARG1 = "";
        Reponse.supprimerInstance();
    }

    /**
     * Test pour numero de permis architecte valide avec T et 4 chiffre
     */
    @Test
    void validerNumeroDePermis01() {
        Assertions.assertTrue(ServiceValidationNumeroDePermis.validerNumeroDePermis(
                "T0000","architectes")
        );
    }

    /**
     * Test pour numero de permis architecte valide avec A et 4 chiffre
     */
    @Test
    void validerNumeroDePermis02() {
        Assertions.assertTrue(ServiceValidationNumeroDePermis.validerNumeroDePermis(
                "A0000","architectes")
        );
    }

    /**
     * Test pour numero de permis architecte avec lettre Invalide  et 4 chiffre
     */
    @Test
    void validerNumeroDePermis03() throws Exception {

        int status = SystemLambda.catchSystemExit(() ->
                ServiceValidationNumeroDePermis.validerNumeroDePermis(
                        "M0000","architectes")
        );
        Assertions.assertEquals(status,5);
    }

    /**
     * Test pour numero de permis architecte avec lettre valide et 5 chiffre
     */
    @Test
    void validerNumeroDePermis04() throws Exception {

        int status = SystemLambda.catchSystemExit(() ->
                ServiceValidationNumeroDePermis.validerNumeroDePermis(
                        "A00000","architectes")
        );
        Assertions.assertEquals(status,5);
    }

    /**
     * Test pour numero de permis architecte avec lettre invalide et 5 chiffre
     */
    @Test
    void validerNumeroDePermis05() throws Exception {

        int status = SystemLambda.catchSystemExit(() ->
                ServiceValidationNumeroDePermis.validerNumeroDePermis(
                        "X00000","architectes")
        );
        Assertions.assertEquals(status,5);
    }

    /**
     * Test pour numero de permis architecte avec aucune lettre
     */
    @Test
    void validerNumeroDePermis06() throws Exception {

        int status = SystemLambda.catchSystemExit(() ->
                ServiceValidationNumeroDePermis.validerNumeroDePermis(
                        "00000","architectes")
        );
        Assertions.assertEquals(status,5);
    }

    /**
     * Test pour numero de permis architecte avec aucun chiffre
     */
    @Test
    void validerNumeroDePermis07() throws Exception {

        int status = SystemLambda.catchSystemExit(() ->
                ServiceValidationNumeroDePermis.validerNumeroDePermis(
                        "XCTRT","architectes")
        );
        Assertions.assertEquals(status,5);
    }
}