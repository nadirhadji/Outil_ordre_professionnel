package Service;

import Entite.Activite;
import Entite.Categorie;
import Entite.Declaration;
import Entite.Reponse;
import Utils.Constantes;
import Utils.ConstantesArchitecte;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class ServiceValidationArchitecteTest {

    ServiceValidationArchitecte service;
    Declaration declaration;

    @Before
    public void init() {
        Constantes.ARG1 = "src/test/ressources/reponse.json";
        ServiceRedondanceDate serviceDate = new ServiceRedondanceDate();
        ServiceValidationActivite serviceActivite = new ServiceValidationActivite(
                ConstantesArchitecte.VALEUR_ORDRE_ARCHITECTES,
                "2016-2018"
        );
        service = new ServiceValidationArchitecte(serviceDate,serviceActivite);
        declaration = creerDecladation(obtenirListeActiviteComplete(),5);
    }

    @BeforeEach
    public void initMethode() {
        service.serviceRedondanceDate.destructeur();
    }

    @AfterEach
    public void detruireMethode() {
        Reponse.supprimerInstance();
    }

    @After
    public void detruire() throws Exception {
        declaration = null;
        Reponse.supprimerInstance();
        Constantes.ARG1 = "";
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

    //Teste un nombre d'heure transfere valide
    @Test
    public void testVerifierHeureTransfereValide() throws Exception {
        declaration = creerDecladation(obtenirListeActiviteComplete(),5);
        service.verifierHeureTransfere(declaration);
        Reponse.obtenirInstance();
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    //Teste un nombre d'heure transfere Negatif
    @Test
    public void testVerifierHeureTransfereNegatif() throws Exception {
        declaration = creerDecladation(obtenirListeActiviteComplete(),-5);
        service.verifierHeureTransfere(declaration);
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    //Teste un nombre d'heure transfere superieur a 7
    @Test
    public void testVerifierHeureTransfereSuperieurA7() throws Exception {
        declaration = creerDecladation(obtenirListeActiviteComplete(),9);
        service.verifierHeureTransfere(declaration);
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierSiHeuresTransfereSuperieurA7Vrai() throws Exception {
        declaration = creerDecladation(obtenirListeActiviteComplete(),9);
        service.verifierSiHeuresTransfereSuperieurA7(declaration);
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierSiHeuresTransfereSuperieurA7Faux() throws Exception {
        declaration = creerDecladation(obtenirListeActiviteComplete(),6);
        service.verifierSiHeuresTransfereSuperieurA7(declaration);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessageInformation().isEmpty());
    }

    @Test
    public void testVerifierSiHeuresTransfereNegatifVrai() throws Exception {
        declaration = creerDecladation(obtenirListeActiviteComplete(),-1);
        service.verifierSiHeuresTransfereNegatif(declaration);
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierSiHeuresTransfereNegatifFaux() throws Exception {
        declaration = creerDecladation(obtenirListeActiviteComplete(),3);
        service.verifierSiHeuresTransfereNegatif(declaration);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    /*
    @Test
    public void testVerifierActivites() throws Exception {
        service.verifierActivites(declaration);
        Assertions.assertTrue(service.("2017-01-21"));
        Assertions.assertTrue(service.dateMap.containsKey("2017-01-22"));
        Assertions.assertTrue(service.dateMap.containsKey("2017-01-23"));
        Assertions.assertTrue(service.dateMap.containsKey("2017-01-24"));
        Assertions.assertTrue(service.dateMap.containsKey("2017-01-25"));
        Assertions.assertTrue(service.dateMap.containsKey("2017-01-26"));
    }

    @Test
    public void testEstActiviteRedondante() throws Exception {
        ServiceRedondanceDate serviceRedondance = new ServiceRedondanceDate();
        service.verifierActivites(declaration);
        Activite redondante = new Activite("Activite1",
                "présentation",
                10,
                "2017-01-21");
        Assertions.assertTrue(serviceRedondance.estActiviteRedondante(redondante));
    }

    //Dans ce cas le nombre d'heure initiliaser pour cette date est de 1 , donc une activite de 1O heures
    //supplementaire ferait que pour cette date l'architecte a fait 11 heures au totale ce qui n'est pas accepté.$
    //Pour rappel le maximum d'heures par jour d'activité est de 10 heures.
    @Test
    public void testEstUneDateExistanteVrai() throws Exception {
        ServiceRedondanceDate serviceDeRedondance = new ServiceRedondanceDate();
        service.verifierActivites(declaration);
        Assertions.assertTrue(serviceDeRedondance.estUneDateExistante("2017-01-21",10));
    }

    @Test
    public void testEstUneDateExistanteFaux() throws Exception {
        ServiceRedondanceDate serviceDeRedondance = new ServiceRedondanceDate();
        service.verifierActivites(declaration);
        Assertions.assertFalse(serviceDeRedondance.estUneDateExistante("2017-01-29",5));
    }

    */

    //Cas ou une activite est de groupe.
    //Tout les compteur des nombres d'heures sont a 0.
    @Test
    public void testIncrementerCompteurHeuresPourActiviteDeGroupe() throws Exception {
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
        service.incrementerCompteurHeures(cours);
        service.incrementerCompteurHeures(atelier);
        service.incrementerCompteurHeures(seminaire);
        service.incrementerCompteurHeures(colloque);
        service.incrementerCompteurHeures(conference);
        service.incrementerCompteurHeures(lecture);
        Assertions.assertEquals(service.heuresArchitecte.obtenirActiviteDeGroupe(), 60);
        Assertions.assertEquals(service.heuresArchitecte.obtenirGroupeDeDiscussion(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirPresentation(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirProjetDeRecherche(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirRedactionProfessionel(), 0);
    }

    //Cas ou une activite est une présentation
    //Tout les compteur des nombres d'heures sont a 0.
    @Test
    public void testIncrementerCompteurHeuresPourPresentation() throws Exception {
        Activite presentation = new Activite("Ceci est la description de l'Activite1",
                "présentation",10,"2017-01-26");
        service.incrementerCompteurHeures(presentation);
        Assertions.assertEquals(service.heuresArchitecte.obtenirActiviteDeGroupe(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirGroupeDeDiscussion(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirPresentation(), 10);
        Assertions.assertEquals(service.heuresArchitecte.obtenirProjetDeRecherche(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirRedactionProfessionel(), 0);
    }

    //Cas ou une activite est un groupe de discussion
    //Tout les compteur des nombres d'heures sont a 0.
    @Test
    public void testIncrementerCompteurHeuresPourGroupeDeDiscussion() throws Exception {
        Activite groupe = new Activite("Ceci est la description de l'Activite1",
                "groupe de discussion",10,"2017-01-26");
        service.incrementerCompteurHeures(groupe);
        Assertions.assertEquals(service.heuresArchitecte.obtenirActiviteDeGroupe(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirGroupeDeDiscussion(), 10);
        Assertions.assertEquals(service.heuresArchitecte.obtenirPresentation(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirProjetDeRecherche(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirRedactionProfessionel(), 0);
    }

    //Cas ou une activite est un projet de recherche
    //Tout les compteur des nombres d'heures sont a 0.
    @Test
    public void testIncrementerCompteurHeuresPourProjetRecherche() throws Exception {
        Activite recherche = new Activite("Ceci est la description de l'Activite1",
                "projet de recherche",10,"2017-01-26");
        service.incrementerCompteurHeures(recherche);
        Assertions.assertEquals(service.heuresArchitecte.obtenirActiviteDeGroupe(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirGroupeDeDiscussion(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirPresentation(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirProjetDeRecherche(), 10);
        Assertions.assertEquals(service.heuresArchitecte.obtenirRedactionProfessionel(), 0);
    }

    //Cas ou une activite est une redaction professionnelle
    //Tout les compteurs des nombres d'heures sont a 0.
    @Test
    public void testIncrementerCompteurHeuresPourRedactionPro() throws Exception {
        Activite redaction = new Activite("Ceci est la description de l'Activite1",
                "rédaction professionnelle", 10,"2017-01-26");
        service.incrementerCompteurHeures(redaction);
        Assertions.assertEquals(service.heuresArchitecte.obtenirActiviteDeGroupe(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirGroupeDeDiscussion(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirPresentation(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirProjetDeRecherche(), 0);
        Assertions.assertEquals(service.heuresArchitecte.obtenirRedactionProfessionel(), 10);
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
        service.verifierSiCategorieGroupeDeDiscussion("groupe de discussion",5);
        Assertions.assertEquals(5,service.heuresArchitecte.obtenirGroupeDeDiscussion());
    }

    @Test
    public void testVerifierSiCategorieProjetDeRecherche() throws Exception {
        service.verifierSiCategorieProjetDeRecherche("projet de recherche",5);
        Assertions.assertEquals(5,service.heuresArchitecte.obtenirProjetDeRecherche());
    }

    @Test
    public void testVerifierSiCategorieRedactionProfessionnelle() throws Exception {
        service.verifierSiCategorieRedactionProfessionnelle("rédaction professionnelle",5);
        Assertions.assertEquals(5,service.heuresArchitecte.obtenirRedactionProfessionel());
    }

    @Test
    public void testVerifierNombreHeuresPourActiviteDeGroupeValide() throws Exception {
        declaration = creerDecladation(new ArrayList<Activite>(),5);
        service.heuresArchitecte.enregistrerActiviteDeGroupe(17);
        service.verifierNombreHeuresPourActiviteDeGroupe(declaration);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierNombreHeuresPourActiviteDeGroupeInvalide() {
        declaration = creerDecladation(new ArrayList<>(),5);
        service.verifierNombreHeuresPourActiviteDeGroupe(declaration);
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    //Teste dans le cas d'une declaration contenant le bon nombre d'heures pour les activite de groupe.
    @Test
    public void testEstNombreHeuresPourActiviteDeGroupeValide() {
        service.verifier(declaration);
        service.serviceValidationActivite.enregistrerCycle("2016-2018");
        Assertions.assertTrue(service.estNombreHeuresPourActiviteDeGroupeValide(declaration));
    }

    @Test
    public void testEstNombreHeuresPourActiviteDeGroupeInvalide() {
        declaration = creerDecladation(new ArrayList<>(),5);
        service.verifier(declaration);
        Assertions.assertFalse(service.estNombreHeuresPourActiviteDeGroupeValide(declaration));
    }

    //Cas ou le le minimum d'heures pour activite de groupe est 17
    // et le nombre totale est 21 ( selon intilialisation de declaration)
    @Test
    public void testVerifierTotalHeurePourActiviteValide() {
        service.verifier(declaration);
        int totaleHeures = service.heuresArchitecte.obtenirActiviteDeGroupe();
        Assertions.assertTrue(service.verifierTotalHeurePourActivite(declaration,
                totaleHeures,
                ConstantesArchitecte.MINIMUM_HEURE_ACTIVITE_DE_GROUPE_ARCHITECTE));
    }

    //cas d'une declaration
    // - sans activite de groupe
    // - avec un nombre d'heure transfere = 5 ( minimum 17 pour etre positif)
    // qui veux verifier le nombre totale d'heures en fonction du minimum requis.
    @Test
    public void testVerifierTotalHeurePourActiviteInvalide() {
        declaration = creerDecladation(new ArrayList<>(),5);
        int totaleHeures = service.heuresArchitecte.obtenirActiviteDeGroupe();
        Assertions.assertFalse(service.verifierTotalHeurePourActivite(declaration,
                totaleHeures,
                ConstantesArchitecte.MINIMUM_HEURE_ACTIVITE_DE_GROUPE_ARCHITECTE)
        );
    }

    //cas d'une declaration
    // - avec activite de groupe = 15 heures
    // - avec un nombre d'heure transfere = 3 ( minimum 17 pour etre positif)
    // qui veux verifier le nombre totale d'heures en fonction du minimum requis.
    @Test
    public void testVerifierTotalHeurePourActiviteValidableAvecCompensation() {
        declaration = creerDecladation(obtenirListeActiviteAvec15HeuresDeGroupe(),3);
        int totaleHeures = service.heuresArchitecte.obtenirActiviteDeGroupe();
        Assertions.assertFalse(service.verifierTotalHeurePourActivite(declaration,
                totaleHeures,
                ConstantesArchitecte.MINIMUM_HEURE_ACTIVITE_DE_GROUPE_ARCHITECTE)
        );
    }

    //cas d'une declaration
    // - avec activite de groupe = 15 heures
    // - avec un nombre d'heure transfere = 1 ( minimum 17 pour etre positif)
    // qui veux verifier le nombre totale d'heures en fonction du minimum requis.
    @Test
    public void testVerifierTotalHeurePourActiviteNonValidableAvecCompensation() {
        declaration = creerDecladation(obtenirListeActiviteAvec15HeuresDeGroupe(),1);
        int totaleHeures = service.heuresArchitecte.obtenirActiviteDeGroupe();
        Assertions.assertFalse(service.verifierTotalHeurePourActivite(declaration,
                totaleHeures,
                ConstantesArchitecte.MINIMUM_HEURE_ACTIVITE_DE_GROUPE_ARCHITECTE)
        );
    }

    //La methode verifierSiNombreHeureTransfereSuffisantePourValider est appeler uniquement lorsque
    // Le nombre d'heures pour activite de groupe est inferieur au minimum 17.
    //Dans ce cas obtenirListeActiviteCompensable() contient 10 heures d'activite de groupe
    //7 heures d'heures transfere sont suffisant pour atteindre le minimum requis.
    @Test
    public void testVerifierSiNombreHeureTransfereSuffisantePourValider() throws Exception {
        declaration = creerDecladation(obtenirListeActiviteCompensable(),7);
        Assertions.assertTrue(service.verifierSiNombreHeureTransfereSuffisantePourValider(declaration,10,
                ConstantesArchitecte.MINIMUM_HEURE_ACTIVITE_DE_GROUPE_ARCHITECTE));
    }

    //La methode verifierSiNombreHeureTransfereSuffisantePourValider est appeler uniquement lorsque
    // Le nombre d'heures pour activite de groupe est inferieur au minimum 17.
    //Dans ce cas obtenirListeActiviteCompensable() contient 10 heures d'activite de groupe
    //5 heures d'heures transfere ne sont pas suffisante pour atteindre le minimum de 17heures.
    @Test
    public void testVerifierSiNombreHeureTransfereSuffisantePourInvalider() throws Exception {
        declaration = creerDecladation(obtenirListeActiviteCompensable(),5);
        Assertions.assertFalse(service.verifierSiNombreHeureTransfereSuffisantePourValider(declaration,10,
                ConstantesArchitecte.MINIMUM_HEURE_ACTIVITE_DE_GROUPE_ARCHITECTE));
    }

    //Teste avec une liste d'activite contenant moin que le maximum d'heure de présentation voulue
    @Test
    public void testVerifierMaximumHeureDePresentationVrai() throws Exception {
        service.heuresArchitecte.enregistrerPresentation(10);
        service.verifierMaximumHeureDePresentation();
        Assertions.assertEquals(service.heuresArchitecte.obtenirPresentation(), 10);
    }

    //Teste avec une liste d'activite contenant plus que le maximum d'heure de présentation voulue
    @Test
    public void testVerifierMaximumHeureDePresentationFaux() throws Exception {
        service.heuresArchitecte.enregistrerPresentation(30);
        service.verifierMaximumHeureDePresentation();
        Assertions.assertEquals(service.heuresArchitecte.obtenirPresentation(), 23);
    }

    @Test
    public void testVerifierMaximumHeureGroupeDeDiscussionVrai() throws Exception {
        service.heuresArchitecte.enregistrerGroupeDeDiscussion(15);
        service.verifierMaximumHeureGroupeDeDiscussion();
        Assertions.assertEquals(service.heuresArchitecte.obtenirGroupeDeDiscussion(), 15);
    }

    @Test
    public void testVerifierMaximumHeureGroupeDeDiscussionFaux() throws Exception {
        service.heuresArchitecte.enregistrerGroupeDeDiscussion(20);
        service.verifierMaximumHeureGroupeDeDiscussion();
        Assertions.assertEquals(service.heuresArchitecte.obtenirGroupeDeDiscussion(), 17);
    }

    @Test
    public void testVerifierMaximumHeureProjetDeRechercheVrai() throws Exception {
        service.heuresArchitecte.enregistrerProjetDeRecherche(17);
        service.verifierMaximumHeureProjetDeRecherche();
        Assertions.assertEquals(service.heuresArchitecte.obtenirProjetDeRecherche(), 17);
    }

    @Test
    public void testVerifierMaximumHeureProjetDeRechercheFaux() throws Exception {
        service.heuresArchitecte.enregistrerProjetDeRecherche(35);
        service.verifierMaximumHeureProjetDeRecherche();
        Assertions.assertEquals(service.heuresArchitecte.obtenirProjetDeRecherche(), 23);
    }

    @Test
    public void testVerifierMaximumHeureRedactionProfessionnelleVrai() throws Exception {
        service.heuresArchitecte.enregistrerRedactionProfessionel(5);
        service.verifierMaximumHeuresHeuresRedactionProfessionel();
        Assertions.assertEquals(service.heuresArchitecte.obtenirRedactionProfessionel(), 5);
    }

    @Test
    public void testVerifierMaximumHeureRedactionProfessionnelleFaux() throws Exception {
        service.heuresArchitecte.enregistrerRedactionProfessionel(18);
        service.verifierMaximumHeuresHeuresRedactionProfessionel();
        Assertions.assertEquals(service.heuresArchitecte.obtenirRedactionProfessionel(), 17);
    }

    @Test
    public void testVerifierNombreHeuresTotaleDansDeclaration() throws Exception {
        service.heuresArchitecte.enregistrerMinimumAvantValidation(42);
        service.heuresArchitecte.enregistrerGroupeDeDiscussion(10);
        service.heuresArchitecte.enregistrerPresentation(10);
        service.heuresArchitecte.enregistrerActiviteDeGroupe(17);
        service.heuresArchitecte.enregistrerProjetDeRecherche(5);
        service.verifierNombreHeuresTotaleDansDeclaration(0);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testObtenirNombreHeuresManquanteEtHeuresTransfere() throws Exception {
        service.heuresArchitecte.enregistrerMinimumAvantValidation(40);
        service.heuresArchitecte.enregistrerGroupeDeDiscussion(10);
        service.heuresArchitecte.enregistrerActiviteDeGroupe(13);
        service.heuresArchitecte.enregistrerPresentation(13);
        Assertions.assertEquals(0,service.obtenirNombreHeuresManquante(5));
    }

    @Test
    public void testObtenirNombreHeuresManquanteFaux() throws Exception {
        service.heuresArchitecte.enregistrerMinimumAvantValidation(40);
        service.heuresArchitecte.enregistrerGroupeDeDiscussion(10);
        service.heuresArchitecte.enregistrerActiviteDeGroupe(17);
        service.heuresArchitecte.enregistrerPresentation(13);
        Assertions.assertEquals(0,service.obtenirNombreHeuresManquante(0));
    }

    @Test
    public void testObtenirNombreHeuresManquanteVrai() throws Exception {
        service.heuresArchitecte.enregistrerMinimumAvantValidation(40);
        service.heuresArchitecte.enregistrerGroupeDeDiscussion(10);
        Assertions.assertEquals(30,service.obtenirNombreHeuresManquante(0));
    }

    @Test
    public void testObtenirNombreTotalHeures() throws Exception {
    service.heuresArchitecte.enregistrerActiviteDeGroupe(10);
    service.heuresArchitecte.enregistrerRedactionProfessionel(10);
    service.heuresArchitecte.enregistrerGroupeDeDiscussion(10);
    service.heuresArchitecte.enregistrerPresentation(10);
    service.heuresArchitecte.enregistrerProjetDeRecherche(10);
        System.out.println(service.obtenirNombreTotalHeures());
    Assertions.assertEquals(50,service.obtenirNombreTotalHeures());
    }

    public Declaration creerDecladation(List<Activite> listeActivites, int nombreHeuresTransfere) {
        return new Declaration("A0001",
                "2016-2018",
                "architectes",
                nombreHeuresTransfere,
                listeActivites);
    }

    public List<Activite> obtenirListeActiviteAvec15HeuresDeGroupe() {
        ArrayList<Activite> liste = new ArrayList<>();
        liste.add(new Activite("Activite1","cours",10,"2017-01-21"));
        liste.add(new Activite("Activite2","atelier",5,"2017-01-22"));
        return liste;
    }

    public List<Activite> obtenirListeActiviteComplete() {
        ArrayList<Activite> liste = new ArrayList<>();
        liste.add(new Activite("Ceci est la description de l'Activite1","cours",10,"2017-01-21"));
        liste.add(new Activite("Ceci est la description de l'Activite2","atelier",10,"2017-01-22"));
        liste.add(new Activite("Ceci est la description de l'Activite3","colloque",5,"2017-01-23"));
        liste.add(new Activite("Ceci est la description de l'Activite4","présentation",10,"2017-01-24"));
        liste.add(new Activite("Ceci est la description de l'Activite5","présentation",10,"2017-01-25"));
        liste.add(new Activite("Ceci est la description de l'Activite6","groupe de discussion",10,"2017-01-26"));
        liste.add(new Activite("Ceci est la description de l'Activite7","groupe de discussion",10,"2017-01-27"));
        liste.add(new Activite("Ceci est la description de l'Activite8","projet de recherche",10,"2017-01-28"));
        liste.add(new Activite("Ceci est la description de l'Activite9","projet de recherche",10,"2017-01-29"));
        liste.add(new Activite("Ceci est la description de l'Activite10","rédaction professionnelle",10,"2017-01-30"));
        liste.add(new Activite("Ceci est la description de l'Activite11","rédaction professionnelle",10,"2017-01-31"));
        return liste;
    }

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

    //Liste etant a 5 heures d'etre valide
    public List<Activite> obtenirListeActiviteCompensable() {
        ArrayList<Activite> liste = new ArrayList<>();
        liste.add(new Activite("Ceci est la description de l'Activite","cours",5,"2017-01-21"));
        liste.add(new Activite("Ceci est la description de l'Activite","atelier",5,"2017-01-22"));
        liste.add(new Activite("Ceci est la description de l'Activite","projet de recherche",10,"2017-01-29"));
        liste.add(new Activite("Ceci est la description de l'Activite","présentation",10,"2017-01-25"));
        liste.add(new Activite("Ceci est la description de l'Activite","rédaction professionnelle",10,"2017-01-30"));
        return liste;
    }

    public List<Activite> obtenirListeActivitePresentationInferieurAuMax() {
        ArrayList<Activite> liste = new ArrayList<>();
        liste.add(new Activite("Ceci est la description de l'Activite","présentation",5,"2017-01-25"));
        liste.add(new Activite("Ceci est la description de l'Activite","présentation",5,"2017-01-26"));
        return liste;
    }

    public List<Activite> obtenirListeActivitePresentationSuperieurAuMax() {
        ArrayList<Activite> liste = new ArrayList<>();
        liste.add(new Activite("Ceci est la description de l'Activite","présentation",10,"2017-01-25"));
        liste.add(new Activite("Ceci est la description de l'Activite","présentation",10,"2017-01-26"));
        return liste;
    }
} 
