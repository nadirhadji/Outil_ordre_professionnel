package Entite;

import Service.ServiceJSON;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

/** 
* Declaration Tester. 
* 
* @author <Authors name> 
* @since <pre>nov. 10, 2021</pre> 
* @version 1.0 
*/ 
public class DeclarationTest {

    ServiceJSON json;
    Declaration declaration;

@Before
public void before() throws Exception {
    json = new ServiceJSON("src/test/ressourcesTeste/declaration.json");
    declaration = new Declaration(json);
}

/** 
* 
* Method: obtenirNumeroDePermis() 
* 
*/ 
@Test
public void testObtenirNumeroDePermis() throws Exception {
    Assertions.assertEquals("AOOO1",declaration.obtenirNumeroDePermis());
} 

/** 
* 
* Method: obtenirCycle() 
* 
*/ 
@Test
public void testObtenirCycle() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: obtenirHeurestransfere() 
* 
*/ 
@Test
public void testObtenirHeurestransfere() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: obtenirOrdre() 
* 
*/ 
@Test
public void testObtenirOrdre() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: obtenirActivites() 
* 
*/ 
@Test
public void testObtenirActivites() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: soustraireAuNombreHeuresTransfere(int nombre) 
* 
*/ 
@Test
public void testSoustraireAuNombreHeuresTransfere() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: modifierNombreHeuresTransfereA7() 
* 
*/ 
@Test
public void testModifierNombreHeuresTransfereA7() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: modifierNombreHeuresTransfereA0() 
* 
*/ 
@Test
public void testModifierNombreHeuresTransfereA0() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: termierExecution(Exception e) 
* 
*/ 
@Test
public void testTermierExecution() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Declaration.getClass().getMethod("termierExecution", Exception.class); 
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
* Method: initialiserNumeroDePermis(DeclarationJSON declarationJson) 
* 
*/ 
@Test
public void testInitialiserNumeroDePermis() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Declaration.getClass().getMethod("initialiserNumeroDePermis", DeclarationJSON.class); 
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
* Method: initiliserCycle(DeclarationJSON declarationJSON) 
* 
*/ 
@Test
public void testInitiliserCycle() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Declaration.getClass().getMethod("initiliserCycle", DeclarationJSON.class); 
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
* Method: initialiserOrdre(DeclarationJSON declarationJSON) 
* 
*/ 
@Test
public void testInitialiserOrdre() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Declaration.getClass().getMethod("initialiserOrdre", DeclarationJSON.class); 
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
* Method: initialiserHeuresTransfere(DeclarationJSON declarationJSON) 
* 
*/ 
@Test
public void testInitialiserHeuresTransfere() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Declaration.getClass().getMethod("initialiserHeuresTransfere", DeclarationJSON.class); 
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
* Method: verifierHeureTransfereExistante(DeclarationJSON declarationJSON) 
* 
*/ 
@Test
public void testVerifierHeureTransfereExistante() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Declaration.getClass().getMethod("verifierHeureTransfereExistante", DeclarationJSON.class); 
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
* Method: verifierHeuresTransfereInexistante(DeclarationJSON declarationJSON) 
* 
*/ 
@Test
public void testVerifierHeuresTransfereInexistante() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Declaration.getClass().getMethod("verifierHeuresTransfereInexistante", DeclarationJSON.class); 
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
* Method: initialiserActivites(DeclarationJSON declarationJSON) 
* 
*/ 
@Test
public void testInitialiserActivites() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = Declaration.getClass().getMethod("initialiserActivites", DeclarationJSON.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
