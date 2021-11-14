package Service;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

public class ServiceValidationActiviteTest {

    ServiceValidationActivite service;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    public void obtenirService(String ordre, String cycle) {
        service = new ServiceValidationActivite(ordre,cycle);
    }

    @Test
    public void testEstUneCategorieReconnue() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: verifierNombreHeureNegatif(Activite activite)
    *
    */
    @Test
    public void testVerifierNombreHeureNegatif() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: verifierNombreHeuresMaximum(Activite activite)
    *
    */
    @Test
    public void testVerifierNombreHeuresMaximum() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: aNombreHeuresSuperieurAuMaximum(int nombreHeures)
    *
    */
    @Test
    public void testANombreHeuresSuperieurAuMaximum() throws Exception {
    //TODO: Test goes here...
    }


    /**
    *
    * Method: verifierDescription(Activite activite)
    *
    */
    @Test
    public void testVerifierDescription() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("verifierDescription", Activite.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: verifierCategorie(Activite activite)
    *
    */
    @Test
    public void testVerifierCategorie() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("verifierCategorie", Activite.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: verifierNombreHeurePourActivite(Activite activite)
    *
    */
    @Test
    public void testVerifierNombreHeurePourActivite() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("verifierNombreHeurePourActivite", Activite.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: verifierDateActivite(Activite activite)
    *
    */
    @Test
    public void testVerifierDateActivite() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("verifierDateActivite", Activite.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: estformatDateValide(String date)
    *
    */
    @Test
    public void testEstformatDateValide() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("estformatDateValide", String.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: estDateValide(String date)
    *
    */
    @Test
    public void testEstDateValide() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("estDateValide", String.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: estDateArchitecteValide(String date)
    *
    */
    @Test
    public void testEstDateArchitecteValide() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("estDateArchitecteValide", String.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: verifierSiDateCompriseEntre(String date, LocalDate debut, LocalDate fin)
    *
    */
    @Test
    public void testVerifierSiDateCompriseEntre() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("verifierSiDateCompriseEntre", String.class, LocalDate.class, LocalDate.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: verifier2016a2018PourArchitecte(String date)
    *
    */
    @Test
    public void testVerifier2016a2018PourArchitecte() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("verifier2016a2018PourArchitecte", String.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: verifier2018a2020PourArchitecte(String date)
    *
    */
    @Test
    public void testVerifier2018a2020PourArchitecte() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("verifier2018a2020PourArchitecte", String.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: verifier2020a2022PourArchitecte(String date)
    *
    */
    @Test
    public void testVerifier2020a2022PourArchitecte() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("verifier2020a2022PourArchitecte", String.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: estDateGeologueValide(String date)
    *
    */
    @Test
    public void testEstDateGeologueValide() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("estDateGeologueValide", String.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: verifier2018a2021PourGeologue(String date)
    *
    */
    @Test
    public void testVerifier2018a2021PourGeologue() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("verifier2018a2021PourGeologue", String.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: estDatePshycologueValide(String date)
    *
    */
    @Test
    public void testEstDatePshycologueValide() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("estDatePshycologueValide", String.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

    /**
    *
    * Method: verifier2018a2023PourPsychologue(String date)
    *
    */
    @Test
    public void testVerifier2018a2023PourPsychologue() throws Exception {
    //TODO: Test goes here...
    /*
    try {
       Method method = ServiceValidationActivite.getClass().getMethod("verifier2018a2023PourPsychologue", String.class);
       method.setAccessible(true);
       method.invoke(<Object>, <Parameters>);
    } catch(NoSuchMethodException e) {
    } catch(IllegalAccessException e) {
    } catch(InvocationTargetException e) {
    }
    */
    }

} 
