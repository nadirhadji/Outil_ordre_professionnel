import Entite.Activite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TesteClasseActivite {

    Activite activite;

    @BeforeAll
    public void initialiser() {
        this.activite = new Activite("Ceci est une description",
                "cours",
                5,
                "2020-05-12");
    }

    @Test
    public void testeObtenirDescription() {
        Assertions.assertEquals("Ceci est une description",activite.obtenirDescription());
    }

    @Test
    public void testeObtenirCategorie() {
        Assertions.assertEquals("cours",activite.obtenirCategorie());
    }

    @Test
    public void testeObtenirHeure() {
        activite = new Activite("Ceci est une description",
                "cours",
                5,
                "2020-05-12");
        Assertions.assertEquals(5,activite.obtenirHeures());
    }

    @Test
    public void testeObtenirDate() {
        Assertions.assertEquals("2020-05-12",activite.obtenirDate());
    }

    @Test
    public void testeSauvegarderHeures() {
        activite.sauvegarderHeures(6);
        Assertions.assertEquals(6,activite.obtenirHeures());
    }

    @Test
    public void testerEstIgnoree() {
        Assertions.assertFalse(activite.estIgnoree());
    }

    @Test
    public void testerIgnorerActivite() {
        activite.ignorerActivite();
        Assertions.assertTrue(activite.estIgnoree());
    }
}
