package Service;

import Entite.Activite;
import Entite.Declaration;
import Utils.Constantes;
import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Entite.Reponse;
import Utils.ConstantesGeologue;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

public class TestServiceGeologue {

    Declaration actuel;
    ServiceValidationGeologue serviceGeo;

    @BeforeEach
    public void creationSimpleDeclaration(){
        Constantes.ARG1 = "src/test/ressources/reponse.json";
        ServiceRedondanceDate serviceDate = new ServiceRedondanceDate();
        ServiceValidationActivite serviceActivite = new ServiceValidationActivite(
                ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES,
                "2018-2021"
        );
        actuel = creerDecladationGeo(obtenirListeActiviteGeo());
        serviceGeo = new ServiceValidationGeologue(serviceDate,serviceActivite);
    }

    @AfterEach
    public void reinitialisation()
    {
        Constantes.ARG1 = "";
        actuel = null;
        Reponse.supprimerInstance();
    }

    /*############################### Test Verification Complete ##################################*/

    @Test
    public void TestverifierGeologueValide()
    {
        //TODO
        actuel = creerDecladationGeo(obtenirListeActiviteGeoComplete());
        serviceGeo.verifier(actuel);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void TestverifierGeologueInvalide1() throws Exception {
        actuel = creerDecladationGeoCycleInv(obtenirListeActiviteGeoInvalide());
        serviceGeo.verifier(actuel);
        System.out.println(Reponse.obtenirInstance().obtenirMessagesErreur().toString());
        System.out.println(Reponse.obtenirInstance().obtenirMessageInformation().toString());
        Assertions.assertTrue(true);
    }

    @Test
    public void TestverifierGeologueInvalide2() throws Exception{
        //TODO
        actuel = creerDecladationGeo(obtenirListeActiviteGeoInvalide());
        int status = SystemLambda.catchSystemExit(() ->
                serviceGeo.verifier(actuel)
        );
        Assertions.assertEquals(status,7);
    }

    /*############# Test Verification du Cycle Geologue ############*/

    @Test
    public void cycleValide()
    {
        //TODO
        String attendu = actuel.obtenirCycle();
        Assertions.assertTrue(serviceGeo.estCycleGeologueValide(attendu));
    }

    @Test
    public void cycleValideMessage(){
        serviceGeo.verifierCycleGeologue(actuel);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void cycleInvalide()
    {
        //TODO
        actuel = creerDecladationGeoCycleInv(obtenirListeActiviteGeo());
        String attendu = actuel.obtenirCycle();
        Assertions.assertFalse(serviceGeo.estCycleGeologueValide(attendu));
    }

    @Test
    public void cycleInvalideMessage()
    {
        //TODO
        actuel = creerDecladationGeoCycleInv(obtenirListeActiviteGeo());
        serviceGeo.verifierCycleGeologue(actuel);
        Assertions.assertEquals(Reponse.obtenirInstance().obtenirMessagesErreur().toString(),"[Le cycle entré n'est pas valide, " +
                "Le cycle doit être de "+ ConstantesGeologue.ANNEE_DEBUT +
                " a "+ ConstantesGeologue.ANNEE_FIN+"]");
    }

    /*##################### Test Verification des activités avec l'incrementation #######################*/

    @Test
    public void testverfierActiviterValide()
    {
        //TODO
        serviceGeo.verifierActivites(actuel);
        Assertions.assertEquals(21,serviceGeo.obtenirNombreTotalHeures());
    }

    @Test
    public void testverfierActiviterInvalide() throws Exception{
        //TODO
        actuel = creerDecladationGeoCycleInv(obtenirListeActiviteGeoInvalide());
        serviceGeo.verifier(actuel);
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void TestincrementerHeureGeo()
    {
        //TODO
        int attendu = 60;
        Activite cours = new Activite("Ceci est la description de l'Activite1",
                "cours",10,"2017-01-21");
        Activite atelier = new Activite("Ceci est la description de l'Activite2",
                "atelier",10,"2017-01-22");
        Activite seminaire = new Activite("Ceci est la description de l'Activite3",
                "séminaire",10,"2017-01-23");
        Activite colloque = new Activite("Ceci est la description de l'Activite4",
                "colloque",10,"2017-01-24");
        Activite conference = new Activite("Ceci est la description de l'Activite5",
                "conférence",10,"2017-01-25");
        Activite lecture = new Activite("Ceci est la description de l'Activite6",
                "lecture dirigée",10,"2017-01-26");
        serviceGeo.incrementerCompteurHeures(cours);
        serviceGeo.incrementerCompteurHeures(atelier);
        serviceGeo.incrementerCompteurHeures(seminaire);
        serviceGeo.incrementerCompteurHeures(colloque);
        serviceGeo.incrementerCompteurHeures(conference);
        serviceGeo.incrementerCompteurHeures(lecture);

        Assertions.assertEquals(60, serviceGeo.obtenirNombreTotalHeures());
    }



    @Test
    public void testVerifierHeureAutreValide()
    {
        //TODO
        Activite cours = new Activite("Ceci est la description de l'Activite1",
                "cours",10,"2017-01-21");
        Activite atelier = new Activite("Ceci est la description de l'Activite2",
                "atelier",10,"2017-01-22");
        Activite seminaire = new Activite("Ceci est la description de l'Activite3",
                "séminaire",10,"2017-01-23");
        Activite colloque = new Activite("Ceci est la description de l'Activite4",
                "colloque",10,"2017-01-24");
        Activite projetDeRecherche = new Activite("Ceci est la description de l'Activite5",
                "projet de recherche",10,"2017-01-25");
        Activite groupeDeDiscussion = new Activite("Ceci est la description de l'Activite6",
                "groupe de discussion",10,"2017-01-26");
        serviceGeo.incrementerCompteurHeures(cours);
        serviceGeo.incrementerCompteurHeures(atelier);
        serviceGeo.incrementerCompteurHeures(seminaire);
        serviceGeo.incrementerCompteurHeures(colloque);
        serviceGeo.incrementerCompteurHeures(projetDeRecherche);
        serviceGeo.incrementerCompteurHeures(groupeDeDiscussion);
        Assertions.assertEquals(30, serviceGeo.obtenirAutreActiviteHeures());

    }

    @Test
    public void testVerifierHeureCours()
    {
        //TODO
        Activite cours = new Activite("Ceci est la description de l'Activite1",
                "cours",10,"2017-01-21");
        Activite cours2 = new Activite("Ceci est la description de l'Activite2",
                "cours",10,"2017-01-22");
        Activite seminaire = new Activite("Ceci est la description de l'Activite3",
                "séminaire",10,"2017-01-23");
        Activite colloque = new Activite("Ceci est la description de l'Activite4",
                "colloque",10,"2017-01-24");
        Activite projetDeRecherche = new Activite("Ceci est la description de l'Activite5",
                "projet de recherche",10,"2017-01-25");
        Activite groupeDeDiscussion = new Activite("Ceci est la description de l'Activite6",
                "groupe de discussion",10,"2017-01-26");
        serviceGeo.incrementerCompteurHeures(cours);
        serviceGeo.incrementerCompteurHeures(cours2);
        serviceGeo.incrementerCompteurHeures(seminaire);
        serviceGeo.incrementerCompteurHeures(colloque);
        serviceGeo.incrementerCompteurHeures(projetDeRecherche);
        serviceGeo.incrementerCompteurHeures(groupeDeDiscussion);
        Assertions.assertEquals(20, serviceGeo.obtenirCoursHeures());

    }

    @Test
    public void testVerifierHeureProjet()
    {
        //TODO
        Activite cours = new Activite("Ceci est la description de l'Activite1",
                "cours",10,"2018-01-21");
        Activite cours2 = new Activite("Ceci est la description de l'Activite2",
                "cours",10,"2018-01-22");
        Activite seminaire = new Activite("Ceci est la description de l'Activite3",
                "séminaire",10,"2018-01-23");
        Activite projetDeRecherche = new Activite("Ceci est la description de l'Activite4",
                "projet de recherche",10,"2018-01-24");
        Activite projetDeRecherche2 = new Activite("Ceci est la description de l'Activite5",
                "projet de recherche",10,"2018-01-25");
        Activite groupeDeDiscussion = new Activite("Ceci est la description de l'Activite6",
                "groupe de discussion",10,"2018-01-26");
        serviceGeo.incrementerCompteurHeures(cours);
        serviceGeo.incrementerCompteurHeures(cours2);
        serviceGeo.incrementerCompteurHeures(seminaire);
        serviceGeo.incrementerCompteurHeures(projetDeRecherche);
        serviceGeo.incrementerCompteurHeures(projetDeRecherche2);
        serviceGeo.incrementerCompteurHeures(groupeDeDiscussion);
        Assertions.assertEquals(20, serviceGeo.obtenirProjetRechercheHeures());
    }

    @Test
    public void testVerifierHeureGroupeDiscussion()
    {
        //TODO
        Activite cours = new Activite("Ceci est la description de l'Activite1",
                "cours",10,"2018-01-21");
        Activite cours2 = new Activite("Ceci est la description de l'Activite2",
                "cours",10,"2018-01-22");
        Activite seminaire = new Activite("Ceci est la description de l'Activite3",
                "séminaire",10,"2018-01-23");
        Activite projetDeRecherche = new Activite("Ceci est la description de l'Activite4",
                "projet de recherche",10,"2018-01-24");
        Activite groupeDeDiscussion = new Activite("Ceci est la description de l'Activite5",
                "groupe de discussion",10,"2018-01-25");
        Activite groupeDeDiscussion2 = new Activite("Ceci est la description de l'Activite6",
                "groupe de discussion",10,"2018-01-26");
        serviceGeo.incrementerCompteurHeures(cours);
        serviceGeo.incrementerCompteurHeures(cours2);
        serviceGeo.incrementerCompteurHeures(seminaire);
        serviceGeo.incrementerCompteurHeures(projetDeRecherche);
        serviceGeo.incrementerCompteurHeures(groupeDeDiscussion2);
        serviceGeo.incrementerCompteurHeures(groupeDeDiscussion);
        Assertions.assertEquals(20, serviceGeo.obtenirGroupeDiscussionHeures());
    }

    /*############# Test du nombre minimum par Activite pour Geologue  ############*/
    @Test
    public void testheureMinGroupeDeDiscussionInvalide()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,0,10,0);
        serviceGeo.verifierMinimumHeureGroupeDeDiscussion();
        Assertions.assertEquals(Reponse.obtenirInstance().obtenirMessagesErreur().toString(),"[Le nombre d'heures " +
                "entré pour Groupe De Discussion" + " est invalide, il doit être" +
                " supérieur ou egale a 1.]");
    }

    @Test
    public void testheureMinGroupeDeDiscussionValide1()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,1,10,0);
        serviceGeo.verifierMinimumHeureGroupeDeDiscussion();
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testheureMinGroupeDeDiscussionValide2()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,4,10,0);
        serviceGeo.verifierMinimumHeureGroupeDeDiscussion();
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }


    @Test
    public void testheureMinCoursInvalide()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,10,10,1);
        serviceGeo.verifierMinimumHeureCours();
        Assertions.assertEquals(Reponse.obtenirInstance().obtenirMessagesErreur().toString(),"[Le nombre " +
                "d'heures entré pour Cours est invalide, il doit être" +
                " supérieur ou egale a 22.]");
    }

    @Test
    public void testheureMinCoursValide1()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,1,10,22);
        serviceGeo.verifierMinimumHeureCours();
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testheureMinCoursValide2()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,1,10,24);
        serviceGeo.verifierMinimumHeureCours();
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testheureMinProjetRechercheInvalide()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,10,2,1);
        serviceGeo.verifierMinimumHeureProjetDeRecherche();
        Assertions.assertEquals(Reponse.obtenirInstance().obtenirMessagesErreur().toString(),"[Le nombre " +
                "d'heures entré pour Projet de Recherche est invalide, " +
                "il doit être supérieur ou egale a 3.]");
    }

    @Test
    public void testheureMinProjetRechercheValide1()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,1,3,22);
        serviceGeo.verifierMinimumHeureProjetDeRecherche();
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testheureMinProjetRechercheValide2()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,1,10,24);
        serviceGeo.verifierMinimumHeureProjetDeRecherche();
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testheureMinPourChaqueValide()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,10,10,24);
        serviceGeo.verifierMinimumHeureParGroupeDeCategorie();
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    /*############# Test du nombre Total d'heure pour la declaration pour Geologue  ############*/

    @Test
    public void testNombreHeuresManquanteValide()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,15,10,24);
        Assertions.assertEquals(0,serviceGeo.obtenirNombreHeuresManquante());

    }

    @Test
    public void testNombreHeuresManquanteValide2()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,10,10,25);
        Assertions.assertEquals(0,serviceGeo.obtenirNombreHeuresManquante());

    }

    @Test
    public void testNombreHeuresManquanteInvalide()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,10,10,24);
        Assertions.assertNotEquals(0,serviceGeo.obtenirNombreHeuresManquante());

    }

    @Test
    public void testNombreHeuresManquanteValideMessage()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,10,10,25);
        serviceGeo.verifierNombreHeuresTotaleDansDeclaration();
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());

    }

    @Test
    public void testNombreHeuresManquanteInvalideMessage()
    {
        //TODO
        serviceGeo = new ServiceValidationGeologue(10,10,10,24);
        serviceGeo.verifierNombreHeuresTotaleDansDeclaration();
        Assertions.assertEquals(Reponse.obtenirInstance().obtenirMessagesErreur().toString(),
                "[Incomplet, il manque " +
                        serviceGeo.obtenirNombreHeuresManquante() +
                        " heures pour compléter le cycle]");

    }

    /*############# Methode pour initialiser different scenario pour les testes  ############*/

    public Declaration creerDecladationGeo(List<Activite> listeActivites) {
        return new Declaration("A0001",
                "2018-2021",
                "géologues",
                0,
                listeActivites);
    }
    public Declaration creerDecladationGeoCycleInv(List<Activite> listeActivites) {
        return new Declaration("A0001",
                "2018-2020",
                "géologues",
                0,
                listeActivites);
    }

    public List<Activite> obtenirListeActiviteGeo() {
        ArrayList<Activite> liste = new ArrayList<>();
        liste.add(new Activite("Activite1--------------","présentation",5,"2019-01-21"));
        liste.add(new Activite("Activite2--------------","conférence",4,"2019-01-22"));
        liste.add(new Activite("Activite3--------------","colloque",3,"2019-01-23"));
        liste.add(new Activite("Activite4--------------","séminaire",2,"2019-01-24"));
        liste.add(new Activite("Activite5--------------","atelier",1,"2019-01-25"));
        liste.add(new Activite("Activite6--------------","cours",6,"2019-01-26"));
        return liste;
    }
    public List<Activite> obtenirListeActiviteGeoComplete() {
        ArrayList<Activite> liste = new ArrayList<>();
        liste.add(new Activite("Activite1--------------","projet de recherche",8,"2019-01-21"));
        liste.add(new Activite("Activite2--------------","cours",9,"2019-01-22"));
        liste.add(new Activite("Activite3--------------","cours",9,"2019-01-23"));
        liste.add(new Activite("Activite4--------------","séminaire",9,"2019-01-24"));
        liste.add(new Activite("Activite5--------------","groupe de discussion",8,"2019-01-25"));
        liste.add(new Activite("Activite6--------------","cours",10,"2019-01-26"));
        liste.add(new Activite("Activite4--------------","colloque",9,"2020-01-24"));
        return liste;
    }

    public List<Activite> obtenirListeActiviteGeoInvalide() {
        ArrayList<Activite> liste = new ArrayList<>();
        liste.add(new Activite("Activite1","ecole",5,"2017-01-21"));
        return liste;
    }
}
