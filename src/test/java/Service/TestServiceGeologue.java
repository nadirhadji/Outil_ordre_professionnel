package Service;

import Entite.Declaration;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestServiceGeologue {
    Declaration actuel;

    @After
    void reinitialisation()
    {
        actuel = null;
    }


    @Test
    public void cycleValide()
    {
        //TODO
        String attendu = "2018-2021";
        actuel = new Declaration();
        actuel.setCycle("2018-2021");
        assertEquals(attendu,actuel.obtenirCycle());
    }

    @Test
    public void cycleInvalide()
    {
        //TODO
        String attendu = "2018-2020";
        actuel = new Declaration();
        actuel.setCycle("2018-2021");
        assertNotEquals(attendu,actuel.obtenirCycle());
    }

    @Disabled
    @Test
    public void heureCoursValide()
    {
         //TODO
    }

    @Test
    public void heureProjetRechercheValide()
    {
        //TODO
    }


    @Test
    public void heureGroupeDiscussionValide()
    {
        //TODO
    }

    @Test
    public void heureTotalValide()
    {
        //TODO
    }

    @Test
    public void champsHeuresTransfereNonExistant()
    {
        //TODO
    }
    
}
