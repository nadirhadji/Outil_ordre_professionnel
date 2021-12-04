package Service;

import Exception.CleJSONInexistanteException;
import org.junit.Test;
import org.junit.Before; 
import org.junit.jupiter.api.Assertions;
import org.json.simple.JSONObject;

public class ServiceDeclarationJSONTest {

    private ServiceDeclarationJSON serviceDeclarationJson;

    @Before
    public void before() throws Exception {
        serviceDeclarationJson = new ServiceDeclarationJSON("src/test/ressourcesTeste/declaration.json");
    }

    @Test
    public void testContientCleExistante() {
        Assertions.assertTrue(serviceDeclarationJson.contientCleObligatoire("ordre"));
    }

    @Test
    public void testContientCleInexistante() {
        Assertions.assertFalse(serviceDeclarationJson.contientCle("test"));
    }

    @Test
    public void testObtenirStringDeCleExistante() throws CleJSONInexistanteException {
        Assertions.assertEquals("A0001", serviceDeclarationJson.obtenirStringDeCle("numero_de_permis"));
    }

    @Test
    public void testObtenirStringDeCleInexistante() throws CleJSONInexistanteException {
        CleJSONInexistanteException e = Assertions.assertThrows(
                CleJSONInexistanteException.class,
                () -> serviceDeclarationJson.obtenirStringDeCle("test"),
                "La clé est inexistante"
        );
        Assertions.assertTrue(e.getMessage().contains("le champ test n'existe pas dans le fichier JSON"));
    }

    @Test
    public void testObtenirIntDeCle() throws Exception {
        Assertions.assertEquals(
                2, serviceDeclarationJson.obtenirIntDeCle("heures_transferees_du_cycle_precedent")
        );
    }

    @Test
    public void testObtenirIntDeCleInexistante() throws Exception{
        CleJSONInexistanteException e = Assertions.assertThrows(
                CleJSONInexistanteException.class,
                () -> serviceDeclarationJson.obtenirIntDeCle("test"),
                "La cle est inexistante"
        );
        Assertions.assertTrue(e.getMessage().contains("le champ test n'existe pas dans le fichier JSON"));
    }

    @Test
    public void testObtenirIntDeNonString() throws Exception {
        ClassCastException e = Assertions.assertThrows(
                ClassCastException.class,
                () -> serviceDeclarationJson.obtenirIntDeCle("ordre"),
                "Cette element n'est pas un entier"
        );
        Assertions.assertTrue(e.getMessage().contains("cannot be cast to class"));
    }

    @Test
    public void testCreerActivite() throws Exception{
        JSONObject jsonObject = new JSONObject();
    }
} 
