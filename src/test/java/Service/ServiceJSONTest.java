package Service;

import Entite.Activite;
import Exception.CleJSONInexistanteException;
import Service.ServiceJSON;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.Before; 
import org.junit.jupiter.api.Assertions;
import org.json.simple.JSONObject;
import java.util.List;

public class ServiceJSONTest {

    private ServiceJSON serviceJson;

    @Before
    public void before() throws Exception {
        serviceJson = new ServiceJSON("src/test/ressourcesTeste/declaration.json");
        serviceJson.charger();
    }

    @Test
    public void testChargerParceException() {
        ServiceJSON declarationErrone =
                new ServiceJSON("src/test/ressourcesTeste/declarationNonJson.json");
        Exception exception = Assertions.assertThrows(
                ParseException.class,
                () -> declarationErrone.charger(),
                "Le parce n'a pas fonctionner"
        );
        Assertions.assertNull(exception.getMessage());
    }

    @Test
    public void testContientCleExistante() {
        Assertions.assertTrue(serviceJson.contientCle("ordre"));
    }

    @Test
    public void testContientCleInexistante() {
        Assertions.assertFalse(serviceJson.contientCle("test"));
    }

    @Test
    public void testObtenirStringDeCleExistante() throws CleJSONInexistanteException {
        Assertions.assertEquals("A0001", serviceJson.obtenirStringDeCle("numero_de_permis"));
    }

    @Test
    public void testObtenirStringDeCleInexistante() throws CleJSONInexistanteException {
        CleJSONInexistanteException e = Assertions.assertThrows(
                CleJSONInexistanteException.class,
                () -> serviceJson.obtenirStringDeCle("test"),
                "La clé est inexistante"
        );
        Assertions.assertTrue(e.getMessage().contains("le champ test n'existe pas dans le fichier JSON"));
    }

    @Test
    public void testObtenirIntDeCle() throws Exception {
        Assertions.assertEquals(
                2, serviceJson.obtenirIntDeCle("heures_transferees_du_cycle_precedent")
        );
    }

    @Test
    public void testObtenirIntDeCleInexistante() throws Exception{
        CleJSONInexistanteException e = Assertions.assertThrows(
                CleJSONInexistanteException.class,
                () -> serviceJson.obtenirIntDeCle("test"),
                "La cle est inexistante"
        );
        Assertions.assertTrue(e.getMessage().contains("le champ test n'existe pas dans le fichier JSON"));
    }

    @Test
    public void testObtenirIntDeNonString() throws Exception {
        ClassCastException e = Assertions.assertThrows(
                ClassCastException.class,
                () -> serviceJson.obtenirIntDeCle("ordre"),
                "Cette element n'est pas un entier"
        );
        Assertions.assertTrue(e.getMessage().contains("cannot be cast to class"));
    }

    @Test
    public void testCreerActivite() throws Exception{
        JSONObject jsonObject = new JSONObject();
    }
} 
