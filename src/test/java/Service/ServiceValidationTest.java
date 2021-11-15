package Service;

import Exception.NumeroDePermisInvalideException;
import Entite.Reponse;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.jupiter.api.Assertions;

public class ServiceValidationTest {

    ServiceValidation serviceValidation = new ServiceValidation();

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
        Reponse.supprimerInstance();
    }

    @Test
    public void testVerifierNumeroDePermis() throws Exception {
        serviceValidation.verifierNumeroDePermis("A0000");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testEstNumeroDePermisValide() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: estPremierCaractereValide(Character premier)
    *
    */
    @Test
    public void testEstPremierCaractereValide() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: contient4chiffres(String chaine)
    *
    */
    @Test
    public void testContient4chiffres() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: estUnChiffre(String chaine)
    *
    */
    @Test
    public void testEstUnChiffre() throws Exception {
    //TODO: Test goes here...
    }

} 
