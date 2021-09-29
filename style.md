
# Convention de style 

@session : Automne 2021

@cours : INF2050 – outils et pratiques de développement logiciel

@équipe : Beyram, Steffie, Félix et Nadir

@date : 19/09/2021


------------------

## Table des matières

+ 1 - Introduction
  + 1.1 - Description du document
  + 1.2 - Choix de langue
+ 2 - Un code qui respire 
  + 2.1 - Séparateurs
+ 3 - Un nommage éclairé
  + 3.1 - Cas de chameau 
  + 3.2 - Règles par type d'identifiant
+ 4 - Réference
  
  
------------------

## **1 - Introduction**

### 1.1 - *Description du document*
Ce document décrit la convention de style qui sera adoptée par notre équipe pour le TP1 du cours INF2050 – outils et pratiques de développement logiciel.

### 1.2 - *Choix de langue*
La langue qui sera utilisée sera le **français** et ce dans tous les composants du logiciel. Dans le code, cela implique que les noms de classes, attributs, méthodes ainsi que toute variable devront être écrits dans celle-ci . Il en va de même pour les commentaires, les documents liés au projet ainsi que les messages de commit sur git.

## **2 - Un code qui respire**

### 2.1 - *Séparateurs*
Il faudra utiliser des séparateurs lors de la rédaction du code tel que les retours à la ligne, les espaces, les lignes blanches ... afin d'**aérer** le code et le rendre plus lisible. Nous allons définir quelques règles de base pour nous mettre en contexte.

+ Le nombre de caractères maximum par ligne sera 80

+ Utiliser 2 lignes blanches pour séparer les éléments d'un fichier (ex : constructeur, méthodes, attributs)

+ Utiliser 1 ligne blanche 
  + entre les blocs logiques de chaque méthode
  + avant une ligne de commentaire
  + entre la déclaration des variables locales et le code.


+ Utiliser une indentation convenable et propre soit un retour a la ligne et 4 espaces après les structures de contrôle (ex : if else).
  ``` java
  if ( age > 18 ) {
      laisserRentrer();
      servir();
  }
  else {
      recaler();
  }
  ```
  est mieux que 
  ``` java
  if (age>18){
      laisserRentrer();
      servir();
      } else {
         recaler();
  }
  ```

  
+ Mettre un espace entre un mot clé et une parenthèse 
``` java
  while (i < 10)
```
  
+ Couper les lignes trop longues après une virgule 
``` Java
  maMethode(parametre1, parametre2, parametre3,
	    parametre4, parametre5);
```
+ Toujours utiliser des accolades pour les structures de contrôle 

``` java
if (probleme) {
    verifier();
    corriger();
    sauvegarder();
}
```

## **3 - Un nommage éclairé**

### 3.1 *Cas de chameau*

La règle de nommage principale sera le cas de chameau ( CamelCase en anglais ) pour tous les noms de classe Java, méthode ou variable. Les mots devront être collés ensemble dans le cas d'un nom composé et chaque première lettre de chaque mot aura une majuscule.

exemple : **voiciUnExempleDeCamelCase**

### 3.2 *Règles par type d'identifiant*

Tout d'abord, il est important de souligner qu'un programme qui compile et qui fait ce qu'on lui demande n'est pas une garantie de qualité. 

Il va être important de suivre quelques règles, entre autres de nommage, afin d'assurer une uniformité et plus de clarté dans notre code.

Les 3 points du chapitre 3 sur le nommage du livre *Coder Proprement* de **Robert C. Martin** vont devoir être respectés afin que chacun puisse lire ou reprendre le code d'un collègue.

+ 1 - Choisir des noms révélateurs des intentions
+ 2 - Choisir des noms prononçables
+ 3 - Éviter la codification


#### 3.2.1 *Classes et interfaces*

+ La première lettre doit être en majuscule 
+ Utiliser un nom 
+ Ne pas mettre de caractère spécial
+ Les classes définissant des exceptions devront finir par Exception.

Exemple : *MaClasse, MonInterface*

#### 3.2.2 *Méthodes*

+ La première lettre doit être en minuscule
+ Le nom de chaque méthode doit contenir un verbe
  
Example : *estNul() , obtenirAge(), enregistrerNom(), calculerAire()*

#### 3.2.3 *Variables*

+ La première lettre doit être en minuscule
+ Nommer en Cas de Chameau
+ Éviter les noms de variable a une lettre sauf pour les variables provisoires des boucles (i , j, k)

#### 3.2.4 *Constantes*

+ Ces noms devront être écrits en majuscule
+ Si la constante est un nom composé, séparer les mots avec des '_'
+ Toute constante doit être initialisée et final.  

Example : *MESSAGE_INTRODUCTIF, CONTANTE_LAMBDA*

## **4 - Réference**

Le document suivant s'inspire du guide de style Java de l'université de Cornell qu'on peut retrouver sur : https://www.cs.cornell.edu/courses/JavaAndDS/JavaStyle.html#Indentation

Ainsi que du site : https://www.jmdoudoux.fr/java/dej/chap-normes-dev.htm#normes-dev-4





