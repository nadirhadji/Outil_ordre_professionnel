package Service;

import Entite.Activite;
import Entite.Reponse;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import Utils.ConstantesGeologue;
import Utils.ConstantesPsychologues;
import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.Test;
import java.time.LocalDate;

public class ServiceValidationActiviteTest {

    ServiceValidationActivite service = null;

    @Before
    public void beforeAll() throws Exception {
        Constantes.ARG1="src/test/ressources/reponse.json";
        this.service = initialiserService(null,null);
        Reponse.supprimerInstance();
    }

    @After
    public void afterAll() {
        Constantes.ARG1 = "";
        Reponse.supprimerInstance();
    }

    public ServiceValidationActivite initialiserService(String ordre, String cycle) {
        return new ServiceValidationActivite(ordre,cycle);
    }

    public Activite obtenirActiviteDescriptionValide() {
        return new Activite("ceci est une description valide",
                "cours",5,"2020-06-02");
    }

    public Activite obtenirActiviteDescriptionInvalide() {
        return new Activite("description",
                "cours",5,"2020-06-02");
    }

    public Activite obtenirActivite(String description) {
        return new Activite("description de plus de 20 carac",description,5,"2020-06-02");
    }

    @Test
    public void testVerifierDescriptionValide() throws Exception {
        service.verifierDescription(obtenirActiviteDescriptionValide());
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty() &&
                Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierDescriptionInvalide() throws Exception {
        int status = SystemLambda.catchSystemExit(() ->
                service.verifierDescription(obtenirActiviteDescriptionInvalide()));
        Assertions.assertEquals(status,7);
    }

    @Test
    public void testVerifierCategorieCours() throws Exception {
        this.service.verifierCategorie(obtenirActivite("cours"));
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieAtelier() throws Exception {
        this.service.verifierCategorie(obtenirActivite("atelier"));
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieSeminaire() throws Exception {
        this.service.verifierCategorie(obtenirActivite("s??minaire"));
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieColloque() throws Exception {
        this.service.verifierCategorie(obtenirActivite("colloque"));
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieConference() throws Exception {
        this.service.verifierCategorie(obtenirActivite("conf??rence"));
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieLecture() throws Exception {
        this.service.verifierCategorie(obtenirActivite("lecture dirig??e"));
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategoriePresentation() throws Exception {
        this.service.verifierCategorie(obtenirActivite("pr??sentation"));
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieGroupe() throws Exception {
        this.service.verifierCategorie(obtenirActivite("groupe de discussion"));
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieProjet() throws Exception {
        this.service.verifierCategorie(obtenirActivite("projet de recherche"));
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieRedaction() throws Exception {
        this.service.verifierCategorie(obtenirActivite("r??daction professionnelle"));
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieInvalide() throws Exception {
        this.service.verifierCategorie(obtenirActivite("categorie"));
        Assertions.assertEquals(112,Reponse.obtenirInstance().obtenirMessageInformation().get(0).getCode());
    }

    @Test
    public void testEstUneCategorieReconnueCours() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("cours"));
    }

    @Test
    public void testEstUneCategorieReconnueAtelier() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("atelier"));

    }

    @Test
    public void testEstUneCategorieReconnueSeminaire() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("s??minaire"));
    }

    @Test
    public void testEstUneCategorieReconnueColloque() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("colloque"));
    }

    @Test
    public void testEstUneCategorieReconnueConference() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("conf??rence"));
    }

    @Test
    public void testEstUneCategorieReconnueLecture() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("lecture dirig??e"));
    }

    @Test
    public void testEstUneCategorieReconnuePresentation() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("pr??sentation"));
    }

    @Test
    public void testEstUneCategorieReconnueGroupe() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("groupe de discussion"));
    }

    @Test
    public void testEstUneCategorieReconnueProjet() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("projet de recherche"));
    }

    @Test
    public void testEstUneCategorieReconnueRedaction() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("r??daction professionnelle"));
    }

    @Test
    public void testVerifierNombreHeureNegatifVrai() throws Exception {
        Activite a = new Activite("description","cours",-1,"2020-04-12");
        int status = SystemLambda.catchSystemExit(() ->
                service.verifierNombreHeureNegatif(a));
        Assertions.assertEquals(5,status);
    }

    @Test
    public void testVerifierNombreHeureNegatifFaux() throws Exception {
        Activite a = new Activite("description","cours",2,"2020-04-12");
        service.verifierNombreHeureNegatif(a);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierNombreHeuresMaximumVrai() throws Exception {
        Activite a = new Activite("description","cours",5,"2020-04-12");
        service.verifierNombreHeuresMaximum(a);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierNombreHeuresMaximumFaux() throws Exception {
        Activite a = new Activite("description","cours",12,"2020-04-12");
        service.verifierNombreHeuresMaximum(a);
        Assertions.assertEquals(118,Reponse.obtenirInstance().obtenirMessageInformation().get(0).getCode());
    }

    @Test
    public void testANombreHeuresSuperieurAuMaximumVrai() throws Exception {
    Assertions.assertTrue(service.aNombreHeuresSuperieurAuMaximum(11));
    }

    @Test
    public void testANombreHeuresSuperieurAuMaximumFaux() throws Exception {
        Assertions.assertFalse(service.aNombreHeuresSuperieurAuMaximum(6));
    }

    @Test
    public void testVerifierDateActiviteValide2018a2020Architecte() throws Exception {
        service = new ServiceValidationActivite("architectes","2018-2020");
        Activite a = new Activite("description","cours",3,"2020-02-10");
        service.verifierDateActivite(a);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierDateActiviteInvalide2018a2020Architecte() throws Exception {
        service = new ServiceValidationActivite("architectes","2018-2020");
        Activite a = new Activite("description","cours",3,"2020-04-02");
        service.verifierDateActivite(a);
        Assertions.assertEquals(107,Reponse.obtenirInstance().obtenirMessageInformation().get(0).getCode());
    }

    @Test
    public void testVerifierDateActiviteValide2020a2022Architecte() throws Exception {
        service = new ServiceValidationActivite("architectes","2020-2022");
        Activite a = new Activite("description","cours",3,"2020-04-01");
        service.verifierDateActivite(a);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierDateActiviteValide2016a2018Architecte() throws Exception {
        service = new ServiceValidationActivite("architectes","2016-2018");
        Activite a = new Activite("description","cours",3,"2016-04-02");
        service.verifierDateActivite(a);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierDateActiviteInalide2016a2018Architecte() throws Exception {
        service = new ServiceValidationActivite("architectes","2016-2018");
        Activite a = new Activite("description","cours",3,"2016-03-02");
        service.verifierDateActivite(a);
        Assertions.assertEquals(106,Reponse.obtenirInstance().obtenirMessageInformation().get(0).getCode());
    }

    @Test
    public void testVerifierDateActiviteInvalide2018a2021Geologue() throws Exception {
        service = new ServiceValidationActivite(ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES,"2018-2021");
        Activite a = new Activite("description","cours",3,"2017-03-02");
        service.verifierDateActivite(a);
        Assertions.assertEquals(109,Reponse.obtenirInstance().obtenirMessageInformation().get(0).getCode());
    }

    @Test
    public void testVerifierDateActiviteValide2018a2021Geologue() throws Exception {
        service = new ServiceValidationActivite(ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES,"2018-2021");
        Activite a = new Activite("description","cours",3,"2018-07-02");
        service.verifierDateActivite(a);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierDateActiviteValide2018a2023Pshyco() throws Exception {
        service = new ServiceValidationActivite(ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES,"2018-2023");
        Activite a = new Activite("description","cours",3,"2018-02-02");
        service.verifierDateActivite(a);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierDateActiviteInvalide2018a2023Pshyco() throws Exception {
        service = new ServiceValidationActivite(ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES,"2018-2023");
        Activite a = new Activite("description","cours",3,"2017-02-02");
        service.verifierDateActivite(a);
        Assertions.assertEquals(109,Reponse.obtenirInstance().obtenirMessageInformation().get(0).getCode());
    }

    @Test
    public void testEstformatDateValide() throws Exception {
    Assertions.assertFalse(service.estformatDateInvalide("2020-02-28"));
    }

    @Test
    public void testEstformatDateInvalide() throws Exception {
        Assertions.assertTrue(service.estformatDateInvalide("20202020"));
    }

    @Test
    public void testVerifierSiDateCompriseEntreInvalide() throws Exception {
        Assertions.assertFalse(service.verifierSiDateCompriseEntre(
                "2022-01-01",
                LocalDate.parse("2019-12-01"),
                LocalDate.parse("2020-02-01")
        ));
    }

    @Test
    public void testVerifierSiDateCompriseEntreValide() throws Exception {
    Assertions.assertTrue(service.verifierSiDateCompriseEntre(
            "2020-01-01",
            LocalDate.parse("2019-12-01"),
            LocalDate.parse("2020-02-01")
            ));
    }

    @Test
    public void testVerifier2016a2018PourArchitecteInvalide() throws Exception {
        service = new ServiceValidationActivite(ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES,ConstantesArchitecte.CYCLE_2016_2018);
        Assertions.assertFalse(service.verifier2020a2022PourArchitecte("2019-01-01"));
    }

    @Test
    public void testVerifier2016a2018PourArchitecteValide() throws Exception {
        service = new ServiceValidationActivite(ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES,ConstantesArchitecte.CYCLE_2016_2018);
        Assertions.assertFalse(service.verifier2020a2022PourArchitecte("2017-01-01"));
    }

    @Test
    public void testVerifier2018a2020PourArchitecteInvalide() throws Exception {
        service = new ServiceValidationActivite(ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES,ConstantesArchitecte.CYCLE_2018_2020);
        Assertions.assertFalse(service.verifier2020a2022PourArchitecte("2023-01-01"));
    }

    @Test
    public void testVerifier2018a2020PourArchitecteValide() throws Exception {
        service = new ServiceValidationActivite(ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES,ConstantesArchitecte.CYCLE_2018_2020);
        Assertions.assertFalse(service.verifier2020a2022PourArchitecte("2019-01-01"));
    }

    @Test
    public void testVerifier2020a2022PourArchitecteInvalide() throws Exception {
        service = new ServiceValidationActivite(ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES,ConstantesArchitecte.CYCLE_2020_2022);
        Assertions.assertFalse(service.verifier2020a2022PourArchitecte("2019-01-01"));
    }

    @Test
    public void testVerifier2020a2022PourArchitecteValide() throws Exception {
        service = new ServiceValidationActivite(ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES,ConstantesArchitecte.CYCLE_2020_2022);
        Assertions.assertTrue(service.verifier2020a2022PourArchitecte("2021-01-01"));
    }

    @Test
    public void testEstDateGeologueInvalide() throws Exception {
        service = new ServiceValidationActivite(ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES,
                "2018-2021");
        Assertions.assertFalse(service.estDateGeologueValide("2027-01-01"));
    }

    @Test
    public void testEstDateGeologueValide() throws Exception {
        service = new ServiceValidationActivite(ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES,
                "2018-2021");
        Assertions.assertTrue(service.estDateGeologueValide("2021-01-01"));
    }

    @Test
    public void testVerifier2018a2021PourGeologueInvalide() throws Exception {
        Assertions.assertFalse(service.verifier2018a2021PourGeologue("2017-01-01"));
    }

    @Test
    public void testVerifier2018a2021PourGeologueValide() throws Exception {
        Assertions.assertTrue(service.verifier2018a2021PourGeologue("2019-01-01"));
    }

    @Test
    public void testEstDatePshycologueInvalide() throws Exception {
        service = new ServiceValidationActivite(ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES,
                "2018-2023");
        Assertions.assertFalse(service.estDatePshycologueValide("2016-01-01"));
    }

    @Test
    public void testEstDatePshycologueValide() throws Exception {
        service = new ServiceValidationActivite(ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES,
                "2018-2023");
        Assertions.assertTrue(service.estDatePshycologueValide("2021-01-01"));
    }

    @Test
    public void testVerifier2018a2023PourPsychologueValide() throws Exception {
        Assertions.assertTrue(service.verifier2018a2023PourPsychologue("2020-01-01"));
    }


    @Test
    public void testVerifier2018a2023PourPsychologueInvalide() throws Exception {
        Assertions.assertFalse(service.verifier2018a2023PourPsychologue("2016-01-01"));
    }
} 
