package Service;

import Entite.Reponse;
import Utils.CodeErreur;
import Utils.Constantes;
import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.jupiter.api.*;

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
        Assertions.assertTrue(ServiceValidationNumeroDePermis.architecte("T0000"));
    }

    /**
     * Test pour numero de permis architecte valide avec A et 4 chiffre
     */
    @Test
    void validerNumeroDePermis02() {
        Assertions.assertTrue(ServiceValidationNumeroDePermis.architecte(
                "A0000")
        );
    }

    /**
     * Test pour numero de permis architecte avec lettre Invalide  et 4 chiffre
     */
    @Test
    void validerNumeroDePermis03() throws Exception {

        int status = SystemLambda.catchSystemExit(() ->
                ServiceValidationNumeroDePermis.architecte(
                        "M0000"));
        Assertions.assertEquals(CodeErreur.FATAL_NUMERO_PERMIS,status);
    }

    /**
     * Test pour numero de permis architecte avec lettre valide et 5 chiffre
     */
    @Test
    void validerNumeroDePermis04() throws Exception {

        int status = SystemLambda.catchSystemExit(() ->
                ServiceValidationNumeroDePermis.architecte(
                        "A00000"));
        Assertions.assertEquals(CodeErreur.FATAL_NUMERO_PERMIS,status);
    }

    /**
     * Test pour numero de permis architecte avec lettre invalide et 5 chiffre
     */
    @Test
    void validerNumeroDePermis05() throws Exception {

        int status = SystemLambda.catchSystemExit(() ->
                ServiceValidationNumeroDePermis.architecte(
                        "X00000"));
        Assertions.assertEquals(CodeErreur.FATAL_NUMERO_PERMIS,status);
    }

    /**
     * Test pour numero de permis architecte avec aucune lettre
     */
    @Test
    void validerNumeroDePermis06() throws Exception {

        int status = SystemLambda.catchSystemExit(() ->
                ServiceValidationNumeroDePermis.architecte(
                        "00000"));
        Assertions.assertEquals(CodeErreur.FATAL_NUMERO_PERMIS,status);
    }

    /**
     * Test pour numero de permis architecte avec aucun chiffre
     */
    @Test
    void validerNumeroDePermis07() throws Exception {

        int status = SystemLambda.catchSystemExit(() ->
                ServiceValidationNumeroDePermis.architecte(
                        "XCTRT"));
        Assertions.assertEquals(CodeErreur.FATAL_NUMERO_PERMIS,status);
    }
}