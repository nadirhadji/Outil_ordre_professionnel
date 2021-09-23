# Formation Continue 

Ce projet permet de faire la validation des déclarations d'activités de
formation continue pour les membres d'un ordre professionnel. 

-------------------------
## Table des matières

+ Introduction
+ Format de *declaration.json*
+ Format de *reponse.json*
+ Comment Installer
+ Comment Utiliser
+ Technologies

-------------------------

## Introduction

L'application **FormationContinue.jar** va lire un fichier JSON nommé  **declaration.json** et vérifier : 
+ Le format du document
+ La validité des données
+ Le respect des règles d'affaires
  
Après le traitement, un fichier **resultat.json** sera créé et contiendra un compte rendu de la déclaration.

## 🔣 Format de *declaration.json*

Ceci représente un exemple de déclaration valide 

```json
{
    "numero_de_permis": "A0001",
    "cycle": "2020-2022",
    "heures_transferees_du_cycle_precedent": 2,
    "activites": [
        {
        "description": "Cours sur la déontologie",
        "categorie": "cours",
        "heures": 14,
        "date": "2021-03-20"
        },
        {
        "description": "Séminaire sur   l'architecture contemporaine",
        "categorie": "séminaire",
        "heures": 10,
        "date": "2021-01-07"
        }
    ]
}
```

## 🏁 Format de *reponse.json*

Voici un exemple de réponse dans le cas d'une déclaration invalide :

```json
{
 "complet": false,
 "erreurs": [
 "L'activité Visite d'établissements architecturaux est dans une
 catégorie non reconnue. Elle sera ignorée.",
 "Il manque 2 heures de formation pour compléter le cycle."
 ]
}
```

Dans le cas d'une déclaration valide : 

```json
{
 "complet": true
}
```

## 📦 Comment Installer

TODO

## 🚀 Comment Utiliser

Tout d'abord s'assurer d'avoir Java installé sur ça machine. 

Placer dans un même répertoire le fichier *FormationContinue.jar* ainsi que les deux fichiers JSON *declaration.json* et *resultat.json*.

Le fichier *declaration.json* doit respecter le format d'écriture JSON. Dans le cas contraire, une exception sera levée et le traitement ne pourra pas avoir lieu.

Le fichier *resultat.json* peut être vide au moment de l'exécution.

Pour lancer l'application, ouvrir un terminal et ce déplacer vers le répertoire ou ce trouve notre application.

Lancer ensuite la commande : 

```
java -jar FormationContinue.jar declaration.json resultat.json
```

Une fois terminée, ouvrir le fichier *resultat.json* pour connaitre le résultat du traitement.


## 🔨 Technologies ( librairies et versions )

+ Language : Java DK11 
+ IDE : IntelliJ
+ Format de fichier : JSON
+ Encodage de fichier : UTF-8


