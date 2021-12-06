package Service;

import Entite.Activite;
import Entite.Declaration;
import Entite.Reponse;
import Utils.ConstantesPsychologues;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class TestServiceValidationPsychologues {
    Declaration declaration;
    ServiceValidationPsychologues service;

    @BeforeEach
    void initialiser() {
        ServiceRedondanceDate serviceDate = new ServiceRedondanceDate();
        ServiceValidationActivite serviceActivite = new ServiceValidationActivite(
                ConstantesPsychologues.VALEUR_ORDRE_PSHYCOLOGUES,
                null
        );
        service = new ServiceValidationPsychologues(serviceDate,serviceActivite);
        declaration = creerDeclaration(obtenirListeActivitePsychologue());
    }

    @AfterEach
    void detruire() throws Exception{
        declaration = null;
        Reponse.supprimerInstance();
        service.serviceRedondanceDate.destructeur();
    }

    public Declaration creerDeclaration(ArrayList<Activite> liste) {
        return new Declaration("nom","prenom",0,"A0001",
                "2018-2023",
                "psychologues",
                0,
                liste);
    }

    public Declaration creerDeclarationCycleInvalide(ArrayList<Activite> liste){
        return new Declaration("nom","prenom",0,"A0001",
                "2015-2018",
                "psychologues",
                0,
                liste);
    }

    @Test
    public void testVerifier(){
        service.verifier(declaration);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());

    }

    @Test
    public void testVerifierSiInvalide(){
        declaration = creerDeclarationCycleInvalide(obtenirListeActiviteInvalide());
        service.verifier(declaration);
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    public ArrayList<Activite> obtenirListeActivitePsychologue(){
        ArrayList<Activite> liste = new ArrayList<>();
        liste.add(new Activite("Ceci est la description de l'Activite1","atelier",10,"2018-05-21"));
        liste.add(new Activite("ceci est la description de l'activite2", "conférence",8,"2018-06-01"));
        liste.add(new Activite("Ceci est la description de l'Activite3","cours",10,"2018-06-22"));
        liste.add(new Activite("Ceci est la description de l'Activite3","cours",10,"2018-06-23"));
        liste.add(new Activite("Ceci est la description de l'Activite3","cours",10,"2018-06-24"));
        liste.add(new Activite("Ceci est la description de l'Activite4","colloque",5,"2018-05-23"));
        liste.add(new Activite("Ceci est la description de l'Activite5","présentation",10,"2019-01-24"));
        liste.add(new Activite("Ceci est la description de l'Activite6","groupe de discussion",10,"2019-02-26"));
        liste.add(new Activite("Ceci est la description de l'Activite7","projet de recherche",10,"2020-01-28"));
        liste.add(new Activite("Ceci est la description de l'Activite8","rédaction professionnelle",10,"2020-01-30"));
        return liste;

    }

    public ArrayList<Activite> obtenirListeActiviteInvalide(){
        ArrayList<Activite> listeInvalide = new ArrayList<>();
        listeInvalide.add(new Activite( "Revision du cours","revision",12, "2015-01-01"));
        return listeInvalide;
    }

    @Test
    public void testVerifierNombreHeuresCours(){
        Activite atelier = new Activite("Ceci est la description de l'Activite1","atelier",10,"2018-05-21");
        Activite conference = new Activite("ceci est la description de l'activite2", "conférence",8,"2018-06-01");
        Activite cours1 = new Activite("Ceci est la description de l'Activite3","cours",10,"2018-06-22");
        Activite cours2 = new Activite("Ceci est la description de l'Activite3","cours",10,"2018-06-23");
        Activite cours3 = new Activite("Ceci est la description de l'Activite3","cours",10,"2018-06-24");
        Activite colloque = new Activite("Ceci est la description de l'Activite4","colloque",5,"2018-05-23");
        Activite presentation  = new Activite("Ceci est la description de l'Activite5","présentation",10,"2019-01-24");
        Activite groupeDeDiscussion = new Activite("Ceci est la description de l'Activite6","groupe de discussion",10,"2019-02-26");
        Activite projetDeRecherche = new Activite("Ceci est la description de l'Activite7","projet de recherche",10,"2020-01-28");
        Activite redactionProfessionnelle= new Activite("Ceci est la description de l'Activite8","rédaction professionnelle",10,"2020-01-30");

        service.incrementerCompteurHeures(atelier);
        service.incrementerCompteurHeures(conference);
        service.incrementerCompteurHeures(cours1);
        service.incrementerCompteurHeures(cours2);
        service.incrementerCompteurHeures(cours3);
        service.incrementerCompteurHeures(colloque);
        service.incrementerCompteurHeures(presentation);
        service.incrementerCompteurHeures(groupeDeDiscussion);
        service.incrementerCompteurHeures(projetDeRecherche);
        service.incrementerCompteurHeures(redactionProfessionnelle);

        Assertions.assertEquals(30, service.obtenirHeuresCoursPsycho());

    }

    @Test
    public void testVerifierNombreHeuresConference(){
        service = new ServiceValidationPsychologues(25, 8, 35);
        service.verifierNombreHeuresConference("conférence", 6);
        Assertions.assertEquals(14, service.obtenirHeuresConference());
    }


    @Test
    public void testTropHeuresConference(){
        service = new ServiceValidationPsychologues(25,20,65);

        Assertions.assertEquals(15,
                service.verifierNombreHeuresPourConferenceSupA15(service.obtenirHeuresConference()));
    }

    @Test
    public void testVerifierCyclePsychologue() {
        service.verifierCyclePsychologue(declaration);
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierCycleInvalideMessage(){
        declaration = creerDeclarationCycleInvalide(obtenirListeActiviteInvalide());
        service.verifierCyclePsychologue(declaration);
        Assertions.assertEquals(Reponse.obtenirInstance().obtenirMessagesErreur().toString(),
                "[Le cycle entré n'est pas valide, Le cycle doit être "+
                        ConstantesPsychologues.CYCLE_POUR_PSYCHOLOGUES+"]");
    }

    @Test
    public void testEstCyclePsychologueValide() {
        String cycle = declaration.obtenirCycle();
        Assertions.assertTrue((service.estCyclePsychologueValide(cycle)));
    }

    @Test
    public void testEstCyclePsychologueInvalide(){
        declaration = creerDeclarationCycleInvalide(obtenirListeActiviteInvalide());
        String cycle = declaration.obtenirCycle();
        Assertions.assertFalse(service.estCyclePsychologueValide(cycle));
    }

    @Test
    public void testVerifierActivite(){
        service.verifierActivites(declaration);
        System.out.println(service.obtenirNombreTotalHeures());
        System.out.println(service.obtenirHeuresAutreActivitesPsy());
        System.out.println(service.obtenirHeuresCoursPsycho());
        System.out.println(service.obtenirHeuresConference());
        Assertions.assertEquals(93, service.obtenirNombreTotalHeures());
    }

    @Test
    public void testVerifierActiviteInvalide(){
        declaration = creerDeclaration(obtenirListeActiviteInvalide());
        service.verifierActivites(declaration);
        Assertions.assertEquals(0,service.obtenirNombreTotalHeures());
    }


    @Test
    public void testVerifierNombreHeuresTotaleDansDeclaration(){
        Activite atelier = new Activite("Ceci est la description de l'Activite1","atelier",10,"2018-05-21");
        Activite conference = new Activite("ceci est la description de l'activite2", "conférence",20,"2018-06-01");
        Activite cours1 = new Activite("Ceci est la description de l'Activite3","cours",10,"2018-06-22");
        Activite cours2 = new Activite("Ceci est la description de l'Activite3","cours",10,"2018-06-23");
        Activite cours3 = new Activite("Ceci est la description de l'Activite3","cours",10,"2018-06-24");
        Activite colloque = new Activite("Ceci est la description de l'Activite4","colloque",5,"2018-05-23");
        Activite presentation  = new Activite("Ceci est la description de l'Activite5","présentation",10,"2019-01-24");
        Activite groupeDeDiscussion = new Activite("Ceci est la description de l'Activite6","groupe de discussion",10,"2019-02-26");
        Activite projetDeRecherche = new Activite("Ceci est la description de l'Activite7","projet de recherche",10,"2020-01-28");
        Activite redactionProfessionnelle= new Activite("Ceci est la description de l'Activite8","rédaction professionnelle",10,"2020-01-30");

        service.incrementerCompteurHeures(atelier);
        service.incrementerCompteurHeures(conference);
        service.incrementerCompteurHeures(cours1);
        service.incrementerCompteurHeures(cours2);
        service.incrementerCompteurHeures(cours3);
        service.incrementerCompteurHeures(colloque);
        service.incrementerCompteurHeures(presentation);
        service.incrementerCompteurHeures(groupeDeDiscussion);
        service.incrementerCompteurHeures(projetDeRecherche);
        service.incrementerCompteurHeures(redactionProfessionnelle);
        Assertions.assertEquals(100,service.obtenirNombreTotalHeures());
    }

    @Test
    public void testVerifierHeuresIncomplet(){
        service = new ServiceValidationPsychologues(25,15,20);
        service.verifierNombreHeuresTotaleDansDeclaration();
        Assertions.assertFalse(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }

    @Test
    public void testVerifierHeuresComplet(){
        service = new ServiceValidationPsychologues(50,15,50);
        service.verifierNombreHeuresTotaleDansDeclaration();
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }
    @Test
    public void testVerifierHeuresComplet2(){
        service = new ServiceValidationPsychologues(25,15,50);
        service.verifierNombreHeuresTotaleDansDeclaration();
        Assertions.assertTrue(Reponse.obtenirInstance().obtenirMessagesErreur().isEmpty());
    }




}