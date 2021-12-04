package Service;

import Entite.Activite;
import Entite.Reponse;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import Utils.ConstantesGeologue;
import Utils.ConstantesPsychologues;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ServiceValidationActiviteTest {

    ServiceValidationActivite service;

    @Before
    public void before() throws Exception {
        initialiserService(null,null);
        Reponse.obtenirInstance();
    }

    @After
    public void after() throws Exception {
        Reponse.supprimerInstance();
    }

    public void initialiserService(String ordre, String cycle) {
        service = new ServiceValidationActivite(ordre,cycle);
    }

    public Activite obtenirActiviteDescriptionValide() {
        return new Activite("ceci est une description valide",
                "cours",5,"2020-06-02");
    }

    public Activite obtenirActiviteDescriptionInvalide() {
        return new Activite("description",
                "cours",5,"2020-06-02");
    }

    public void verifierCategorie(String description) {
        Activite a = new Activite("description",description,5,"2020-06-02");
        service.verifierCategorie(a);
    }

    @Test
    public void testVerifierDescriptionValide() throws Exception {
        initialiserService(null,null);
        service.verifierDescription(obtenirActiviteDescriptionValide());
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierDescriptionInvalide() throws Exception {
        initialiserService(null,null);
        service.verifierDescription(obtenirActiviteDescriptionInvalide());
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierCategorieCours() throws Exception {
        verifierCategorie("cours");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieAtelier() throws Exception {
        verifierCategorie("atelier");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieSeminaire() throws Exception {
        verifierCategorie("séminaire");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieColloque() throws Exception {
        verifierCategorie("colloque");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieConference() throws Exception {
        verifierCategorie("conférence");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieLecture() throws Exception {
        verifierCategorie("lecture dirigée");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategoriePresentation() throws Exception {
        verifierCategorie("présentation");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieGroupe() throws Exception {
        verifierCategorie("groupe de discussion");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieProjet() throws Exception {
        verifierCategorie("projet de recherche");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieRedaction() throws Exception {
        verifierCategorie("rédaction professionnelle");
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCategorieInvalide() throws Exception {
        verifierCategorie("categorie");
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
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
        Assertions.assertTrue(service.estUneCategorieReconnue("séminaire"));
    }

    @Test
    public void testEstUneCategorieReconnueColloque() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("colloque"));
    }

    @Test
    public void testEstUneCategorieReconnueConference() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("conférence"));
    }

    @Test
    public void testEstUneCategorieReconnueLecture() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("lecture dirigée"));
    }

    @Test
    public void testEstUneCategorieReconnuePresentation() throws Exception {
        Assertions.assertTrue(service.estUneCategorieReconnue("présentation"));
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
        Assertions.assertTrue(service.estUneCategorieReconnue("rédaction professionnelle"));
    }

    @Test
    public void testVerifierNombreHeureNegatifVrai() throws Exception {
        Activite a = new Activite("description","cours",-1,"2020-04-12");
        service.verifierNombreHeureNegatif(a);
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
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
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
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
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
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
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierDateActiviteInvalide2018a2021Geologue() throws Exception {
        service = new ServiceValidationActivite(ConstantesGeologue.VALEUR_ORDRE_GEOLOGUES,"2018-2021");
        Activite a = new Activite("description","cours",3,"2017-03-02");
        service.verifierDateActivite(a);
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
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
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
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
