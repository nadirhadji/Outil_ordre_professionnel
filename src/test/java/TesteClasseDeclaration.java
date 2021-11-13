import Entite.Declaration;
import Service.ServiceJSON;
import org.junit.jupiter.api.Assertions;
<<<<<<< HEAD
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
=======
>>>>>>> master
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TesteClasseDeclaration {

    ServiceJSON serviceJson;
    Declaration declaration;
<<<<<<< HEAD
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
=======

>>>>>>> master
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
