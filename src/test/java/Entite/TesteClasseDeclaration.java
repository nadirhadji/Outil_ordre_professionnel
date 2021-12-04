package Entite;

import Service.ServiceDeclarationJSON;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TesteClasseDeclaration {

    ServiceDeclarationJSON serviceDeclarationJson;
    Declaration declaration;

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
}
