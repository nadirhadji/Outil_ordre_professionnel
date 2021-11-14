package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.Declaration;
import Entite.Reponse;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

/** 
* ServiceValidationArchitecte Tester. 
* 
* @author <Authors name> 
* @since <pre>nov. 11, 2021</pre> 
* @version 1.0 
*/ 
public class ServiceValidationArchitecteTest {

    ServiceValidationArchitecte service;
    Declaration declaration;
    Reponse reponse;

    public List<Activite> obtenirListeActivite() {
        ArrayList<Activite> liste = new ArrayList<>();
        liste.add(new Activite("Activite1","présentation",5,"2017-01-21"));
        liste.add(new Activite("Activite2","conférence",4,"2017-01-22"));
        liste.add(new Activite("Activite3","colloque",3,"2017-01-23"));
        liste.add(new Activite("Activite4","séminaire",2,"2017-01-24"));
        liste.add(new Activite("Activite5","atelier",1,"2017-01-25"));
        liste.add(new Activite("Activite6","cours",6,"2017-01-26"));
        return liste;
    }

    public Declaration obtenirDeclarationSansActivite() {
        List<Activite> liste = new ArrayList<>();
        return new Declaration("A0001",
                "2016-2018",
                "architectes",
                5,
                liste);
    }

    @Before
    public void init() {
        service = new ServiceValidationArchitecte();
        declaration = new Declaration("A0001",
                "2016-2018",
                "architectes",
                5,
                obtenirListeActivite());
        reponse = new Reponse();
    }

    @After
    public void detruire() throws Exception {
        reponse = null;
    }

    @Test
    public void testVerifier() throws Exception {

    }

    @Test
    public void testVerifierCycle() {
        Assertions.assertTrue(service.verifierCycle(declaration));
    }

    @Test
    public void testEstCycleValide() {
        String cycle = declaration.obtenirCycle();
        Assertions.assertTrue(service.estCycleValide(cycle));
    }

    @Test
    public void testEstCycleValide2016() {
        String cycle = "2016-2018";
        Assertions.assertTrue(service.estCycleValide(cycle));
    }

    @Test
    public void testEstCycleValide2018() {
        String cycle = "2018-2020";
        Assertions.assertTrue(service.estCycleValide(cycle));
    }

    @Test
    public void testEstCycleValide2020() {
        String cycle = "2020-2022";
        Assertions.assertTrue(service.estCycleValide(cycle));
    }

    @Test
    public void testEstCycleInvalide1() {
        String cycle = "2022-2024";
        Assertions.assertFalse(service.estCycleValide(cycle));
    }

    @Test
    public void testEstCycleInvalide2() {
        String cycle = "blabla";
        Assertions.assertFalse(service.estCycleValide(cycle));
    }

    @Test
    public void testEstCycle2016a2020pour2016a1018() {
        String cycle = "2016-2018";
        Assertions.assertTrue(service.estCycle2016a2020(cycle));
    }

    @Test
    public void testEstCycle2016a2020pour2018a2020() {
        String cycle = "2018-2020";
        Assertions.assertTrue(service.estCycle2016a2020(cycle));
    }

    @Test
    public void testEstCycle2016a2020pourInvalide() {
        String cycle = "2020-2022";
        Assertions.assertFalse(service.estCycle2016a2020(cycle));
    }


    @Test
    public void testEstCycle2020a2022() {
        String cycle = "2020-2022";
        Assertions.assertTrue(service.estCycle2020a2022(cycle));
    }

    @Test
    public void testEstCycle2020a2022Invalide() {
        String cycle = "2022-2024";
        Assertions.assertFalse(service.estCycle2020a2022(cycle));
    }

    @Test
    public void testVerifierHeureTransfere() throws Exception {
        service.verifierHeureTransfere(declaration);
        Assertions.assertTrue(reponse.obtenirMessageInformation().isEmpty());
        Assertions.assertTrue(reponse.obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierSiHeuresTransfereSuperieurA7() throws Exception {
        service.verifierSiHeuresTransfereSuperieurA7(declaration);
        Assertions.assertTrue(reponse.obtenirMessagesErreur().isEmpty());
        Assertions.assertTrue(reponse.obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierSiHeuresTransfereSuperieurA7Positif() throws Exception {
        declaration = obtenirDeclarationSansActivite();
        service.verifierSiHeuresTransfereSuperieurA7(declaration);
        Assertions.assertTrue(reponse.obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierSiHeuresTransfereNegatif() throws Exception {
        service.verifierSiHeuresTransfereNegatif(declaration);
        Assertions.assertTrue(reponse.obtenirMessageInformation().isEmpty());
        Assertions.assertTrue(reponse.obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierActivites() throws Exception {
        service.verifierActivites(declaration);
        Assertions.assertTrue(service.dateMap.containsKey("2017-01-21"));
        Assertions.assertTrue(service.dateMap.containsKey("2017-01-22"));
        Assertions.assertTrue(service.dateMap.containsKey("2017-01-23"));
        Assertions.assertTrue(service.dateMap.containsKey("2017-01-24"));
        Assertions.assertTrue(service.dateMap.containsKey("2017-01-25"));
        Assertions.assertTrue(service.dateMap.containsKey("2017-01-26"));
    }

    @Test
    public void testEstActiviteRedondante() throws Exception {
        service.verifierActivites(declaration);
        Activite redondante = new Activite("Activite1",
                "présentation",
                10,
                "2017-01-21");
        Assertions.assertTrue(service.estActiviteRedondante(redondante));
    }

    //Dans ce cas le nombre d'heure initiliaser pour cette date est de 1 , donc une activite de 1O heures
    //supplementaire ferait que pour cette date l'architecte a fait 11 heures au totale ce qui n'est pas accepté.$
    //Pour rappel le maximum d'heures par jour d'activité est de 10 heures.
    @Test
    public void testEstUneDateExistanteVrai() throws Exception {
        service.verifierActivites(declaration);
        Assertions.assertTrue(service.estUneDateExistante("2017-01-21",10));
    }

    @Test
    public void testEstUneDateExistanteFaux() throws Exception {
        service.verifierActivites(declaration);
        Assertions.assertFalse(service.estUneDateExistante("2017-01-29",5));
    }

    @Test
    public void testIncrementerCompteurHeures() throws Exception {
    }

    @Test
    public void testVerifierSiCategorieDeGroupePourCour() throws Exception {
        service.verifierSiCategorieDeGroupe("cours",3);
        Assertions.assertEquals(3,service.heuresArchitecte.obtenirActiviteDeGroupe());
    }

    @Test
    public void testVerifierSiCategorieDeGroupePourAtelier() throws Exception {
        service.verifierSiCategorieDeGroupe("atelier",6);
        Assertions.assertEquals(6,service.heuresArchitecte.obtenirActiviteDeGroupe());
    }

    @Test
    public void testVerifierSiCategorieDeGroupePourSeminaire() throws Exception {
        service.verifierSiCategorieDeGroupe("séminaire",8);
        Assertions.assertEquals(8,service.heuresArchitecte.obtenirActiviteDeGroupe());
    }

    @Test
    public void testVerifierSiCategorieDeGroupePourColloque () throws Exception {
        service.verifierSiCategorieDeGroupe("colloque",9);
        Assertions.assertEquals(9,service.heuresArchitecte.obtenirActiviteDeGroupe());
    }

    @Test
    public void testVerifierSiCategorieDeGroupePourLectureDirige () throws Exception {
        service.verifierSiCategorieDeGroupe("lecture dirigée",12);
        Assertions.assertEquals(12,service.heuresArchitecte.obtenirActiviteDeGroupe());
    }

    @Test
    public void testVerifierSiCategorieDeGroupePourConference () throws Exception {
        service.verifierSiCategorieDeGroupe("conférence",10);
        Assertions.assertEquals(10,service.heuresArchitecte.obtenirActiviteDeGroupe());
    }

    @Test
    public void testObtenirListeDesActivitesDeGroupe() throws Exception {
        List<String> activateDeGroup = new ArrayList<>();
        activateDeGroup.add(Categorie.COURS.toString());
        activateDeGroup.add(Categorie.ATELIER.toString());
        activateDeGroup.add(Categorie.SEMINAIRE.toString());
        activateDeGroup.add(Categorie.COLLOQUE.toString());
        activateDeGroup.add(Categorie.CONFERENCE.toString());
        activateDeGroup.add(Categorie.LECTURE_DIRIGEE.toString());
        Assertions.assertEquals(activateDeGroup,service.obtenirListeDesActivitesDeGroupe());
    }

    @Test
    public void testVerifierSiCategoriePresentation() throws Exception {
            service.verifierSiCategoriePresentation("présentation",5);
            Assertions.assertEquals(5,service.heuresArchitecte.obtenirPresentation());
    }

    @Test
    public void testVerifierSiCategorieGroupeDeDiscussion() throws Exception {
        service.verifierSiCategoriePresentation("groupe de discussion",5);
        Assertions.assertEquals(5,service.heuresArchitecte.obtenirGroupeDeDiscussion());
    }

    @Test
    public void testVerifierSiCategorieProjetDeRecherche() throws Exception {
        service.verifierSiCategoriePresentation("projet de recherche",5);
        Assertions.assertEquals(5,service.heuresArchitecte.obtenirProjetDeRecherche());
    }

    @Test
    public void testVerifierSiCategorieRedactionProfessionnelle() throws Exception {
        service.verifierSiCategoriePresentation("rédaction professionnelle",5);
        Assertions.assertEquals(5,service.heuresArchitecte.obtenirRedactionProfessionel());
    }

    @Test
    public void testVerifierNombreHeuresPourActiviteDeGroupeValide() throws Exception {
        service.verifierNombreHeuresPourActiviteDeGroupe(declaration);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierNombreHeuresPourActiviteDeGroupeInvalide() {
        List<Activite> liste = new ArrayList<>();
        Declaration declaration1 = new Declaration("A0001",
                "2016-2018",
                "architectes",
                5,
                liste);
        service.verifierNombreHeuresPourActiviteDeGroupe(declaration1);
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    //Teste dans le cas d'une declaration contenant le bon nombre d'heures pour les activite de groupe.
    @Test
    public void testEstNombreHeuresPourActiviteDeGroupeValide() {
        service.verifier(declaration);
        Assertions.assertTrue(service.estNombreHeuresPourActiviteDeGroupeValide(declaration));
    }

    @Test
    public void testVerifierTotalHeurePourActivite() {
    //TODO: Test goes here...
    }

    @Test
    public void testVerifierSiNombreHeureTransfereSuffisantePourValider() throws Exception {
    //TODO: Test goes here...
    }

    @Test
    public void testVerifierMaximumHeureParGroupeDeCategorie() throws Exception {
    //TODO: Test goes here...
    }

    @Test
    public void testVerifierMaximumHeureDePresentation() throws Exception {
    //TODO: Test goes here...
    }

    @Test
    public void testVerifierMaximumHeureGroupeDeDiscussion() throws Exception {
    //TODO: Test goes here...
    }

    @Test
    public void testVerifierMaximumHeureProjetDeRecherche() throws Exception {
    //TODO: Test goes here...
    }

    @Test
    public void testVerifierMaximumHeuresHeuresRedactionProfessionel() throws Exception {
    //TODO: Test goes here...
    }

    @Test
    public void testVerifierNombreHeuresTotaleDansDeclaration() throws Exception {
    //TODO: Test goes here...
    }

    @Test
    public void testObtenirNombreHeuresManquante() throws Exception {
    //TODO: Test goes here...
    }

    @Test
    public void testObtenirNombreTotalHeures() throws Exception {
    //TODO: Test goes here...
    }

} 
