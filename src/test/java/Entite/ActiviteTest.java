package Entite;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ActiviteTest {

    private Activite activite;

    @Before
    public void before() {
        activite = new Activite(
                "Ceci est une description",
                "atelier",
                3,
                "2020-06-08"
        );
    }

    @After
    public void after() {
        this.activite = null;
    }

    @Test
    public void testObtenirDescription(){
        Assertions.assertEquals("Ceci est une description",activite.obtenirDescription());
    }

    @Test
    public void testObtenirCategorie(){
        Assertions.assertEquals("atelier",activite.obtenirCategorie());
    }

    @Test
    public void testObtenirHeures() {
        Assertions.assertEquals(3,activite.obtenirHeures());
    }

    @Test
    public void testObtenirDate() {
        Assertions.assertEquals("2020-06-08",activite.obtenirDate());
    }

    @Test
    public void testSauvegarderHeures() {
        activite.sauvegarderHeures(5);
        Assertions.assertEquals(5,activite.obtenirHeures());
    }

    @Test
    public void testEstIgnoree() {
        Assertions.assertFalse(activite.estIgnoree());
    }

    @Test
    public void testIgnorerActivite() {
        activite.ignorerActivite();
        Assertions.assertTrue(activite.estIgnoree());
    }

    /**
     * Teste quand le nombre d'heure est superieur a 10 donc la
     * methode fait une modification
     */
    @Test
    public void testDecrementerNombresHeuresA10() {
        activite = new Activite("Ceci est une description",
                "atelier",
                12,
                "2020-06-08");
        activite.decrementerNombresHeuresA10();
        Assertions.assertEquals(10,activite.obtenirHeures());
    }

    /**
     * Teste quand le nombre d'heures est inferieur a 10, donc aucun
     * changement ne doit s'appliquer
     */
    @Test
    public void testDecrementerHeuresInferieuA10() {
        activite.decrementerNombresHeuresA10();
        Assertions.assertEquals(3,activite.obtenirHeures());
    }
} 
