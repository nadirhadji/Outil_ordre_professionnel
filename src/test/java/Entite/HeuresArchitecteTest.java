package Entite;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import org.junit.jupiter.api.Assertions;

public class HeuresArchitecteTest {

    HeuresArchitecte heures;

    @Before
    public void before() throws Exception {
        heures = new HeuresArchitecte();
        heures.incrementerRedactionProfessionel(1);
        heures.incrementerProjetDeRecherche(2);
        heures.incrementerGroupeDeDiscussion(3);
        heures.incrementerPresentation(4);
        heures.incrementerActiviteDeGroupe(5);
        heures.enregistrerMinimumAvantValidation(30);
    }

    @After
    public void after() {
        heures = null;
    }

    @Test
    public void testObtenirActiviteDeGroupe() {
        Assertions.assertEquals(5,heures.obtenirActiviteDeGroupe());
    }

    @Test
    public void testObtenirPresentation() {
        Assertions.assertEquals(4,heures.obtenirPresentation());
    }

    @Test
    public void testObtenirGroupeDeDiscussion()  {
        Assertions.assertEquals(3,heures.obtenirGroupeDeDiscussion());
    }

    @Test
    public void testObtenirProjetDeRecherche() {
        Assertions.assertEquals(2,heures.obtenirProjetDeRecherche());
    }

    @Test
    public void testObtenirRedactionProfessionel() {
        Assertions.assertEquals(1,heures.obtenirRedactionProfessionel());
    }

    @Test
    public void testObtenirMinimumAvantValidation() {
        Assertions.assertEquals(30,heures.obtenirMinimumAvantValidation());
    }

    //Ici le nombre d'heure pour activite de groupe est initialiser a 5
    @Test
    public void testIncrementerActiviteDeGroupe() {
        heures.incrementerActiviteDeGroupe(5);
        Assertions.assertEquals(10,heures.obtenirActiviteDeGroupe());
    }

    //Ici le nombre d'heure pour presentation est initialiser a 4
    @Test
    public void testIncrementerPresentation() throws Exception {
        heures.incrementerPresentation(4);
        Assertions.assertEquals(8,heures.obtenirPresentation());
    }

    //Ici le nombre d'heure pour groupe de discussion on est initialiser a 3
    @Test
    public void testIncrementerGroupeDeDiscussion() throws Exception {
        heures.incrementerGroupeDeDiscussion(3);
        Assertions.assertEquals(6,heures.obtenirGroupeDeDiscussion());
    }

    //Ici le nombre d'heure pour Projet de recherche on est initialiser a 2
    @Test
    public void testIncrementerProjetDeRecherche() throws Exception {
        heures.incrementerProjetDeRecherche(2);
        Assertions.assertEquals(4,heures.obtenirProjetDeRecherche());
    }

    //Ici le nombre d'heure pour redaction professionel on est initialiser a 1
    @Test
    public void testIncrementerRedactionProfessionel() throws Exception {
        heures.incrementerRedactionProfessionel(1);
        Assertions.assertEquals(2,heures.obtenirRedactionProfessionel());
    }

    @Test
    public void testEnregistrerActiviteDeGroupe() throws Exception {
        heures.enregistrerActiviteDeGroupe(10);
        Assertions.assertEquals(10,heures.obtenirActiviteDeGroupe());
    }

    @Test
    public void testEnregistrerPresentation() throws Exception {
        heures.enregistrerPresentation(11);
        Assertions.assertEquals(11,heures.obtenirPresentation());
    }

    @Test
    public void testEnregistrerGroupeDeDiscussion() throws Exception {
        heures.enregistrerGroupeDeDiscussion(12);
        Assertions.assertEquals(12,heures.obtenirGroupeDeDiscussion());
    }

    @Test
    public void testEnregistrerProjetDeRecherche() throws Exception {
        heures.enregistrerProjetDeRecherche(13);
        Assertions.assertEquals(13,heures.obtenirProjetDeRecherche());
    }

    @Test
    public void testEnregistrerRedactionProfessionel() throws Exception {
        heures.enregistrerRedactionProfessionel(14);
        Assertions.assertEquals(14,heures.obtenirRedactionProfessionel());
    }

    @Test
    public void testEnregistrerMinimumAvantValidation() throws Exception {
        heures.enregistrerMinimumAvantValidation(40);
        Assertions.assertEquals(40,heures.obtenirMinimumAvantValidation());
    }
} 
