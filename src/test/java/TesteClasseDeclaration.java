import Entite.Declaration;
import Entite.DeclarationJSON;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TesteClasseDeclaration {

    DeclarationJSON declarationJSON;
    Declaration declaration;
/*
    @BeforeAll
    public void initialisation() {
        declarationJSON = new DeclarationJSON("../ressourcesTeste/declaration.json");
        declaration = new Declaration(declarationJSON);
    }
*/
    @BeforeEach
    public void initialisation(){
         declaration = new Declaration();
         declaration.setCycle("2020-2022");
        // declaration.setHeuresTransfereesDuCyclePrecedent();
    }
    @Test
    public void testerObtenirCycle() {
        Assertions.assertEquals("2020-2022",declaration.obtenirCycle());
    }

    @Test
    public void testerObtenirHeurestransfere() {
        Assertions.assertEquals(2,declaration.obtenirHeurestransfere());
    }

    @Test
    public void testerObtenirOrdre() {
        Assertions.assertEquals("architecte", declaration.obtenirOrdre());
    }

    @Test
    public void testerObtenirActivites() {
        ArrayList listeActivite = new ArrayList(declaration.obtenirActivites());
    }

    @Test
    public void testerSoustraireAuNombreHeuresTransfere() {

    }

    @Test
    public void testerModifierNombreHeuresTransfereA7() {

    }

    @Test
    public void testerModifierNombreHeuresTransfereA0() {

    }
}
