package Service;

import Exception.NumeroDePermisInvalideException;
import Entite.Reponse;
import org.junit.Test;
import org.junit.After;
import org.junit.jupiter.api.Assertions;

public class ServiceValidationTest {

    ServiceValidation serviceValidation = new ServiceValidation();

    @After
    public void after() throws Exception {
        Reponse.supprimerInstance();
    }

    @Test
    public void testVerifierNumeroDePermisA() throws Exception {
        serviceValidation.validerNumeroDePermis("A0000");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierNumeroDePermisR() throws Exception {
        serviceValidation.validerNumeroDePermis("R0000");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierNumeroDePermisS() throws Exception {
        serviceValidation.validerNumeroDePermis("S0000");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierNumeroDePermisZ() throws Exception {
        serviceValidation.validerNumeroDePermis("Z0000");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }
} 
