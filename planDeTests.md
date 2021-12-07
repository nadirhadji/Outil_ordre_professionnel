#Plan de Tests
###Exigences Fonctionnalités à tester

|Identifiants des fonctionnalités| Description des fonctionnalités |
|:------------------------------:|---------------------------------|  
|             EF-001             |Saisir en entrée un fichier JSON |
|             EF-002             |Valider chaque activité déclarée |
|             EF-003             | Valider la declaration pour l'ordre Architecte |
|             EF-004             | Valider la declaration pour l'ordre Géologue   |
|             EF-005             | Valider la declaration pour l'ordre Psychologue|
|             EF-006             | Valider la declaration pour l'ordre Podiatre   |
|             EF-007             |    Produire les statistiques    |

###Suites et cas de Tests

|Identifiants des fonctionnalités|Identifiants des suites de tests|Description des suites de tests|Nombres de cas|
|:------------------------------:|:------------------------------:|:-----------------------------:|:------------:|
|             EF-001             |             ST-001             |    Tester le fichier JSON     |     13       |
|             EF-002             |             ST-002             |     Tester l'activité         |     8        |
|             EF-003             |             ST-003             | Tester le serviceArchitecte   |     21       |
|             EF-004             |             ST-004             |  Tester le serviceGeologue    |     20       |
|             EF-005             |             ST-005             |  Tester le servicePsychologue |     15       | 
|             EF-006             |             ST-006             |  Tester le servicePodiatre    |     20       |
###Cas de Tests

|Identifiants des suites de tests|Identifiants des cas de tests| Description des cas de Tests  |          Préconditions          |                            Sortie attendue                                           |Priorité|
|:------------------------------:|:---------------------------:|-------------------------------|---------------------------------|--------------------------------------------------------------------------------------|:------:|
|             ST-001             |            CT-001           |Le fichier entré n'est pas JSON|entrer un fichier txt            |                           fin execution                                              | haute  |
|             ST-001             |            CT-002           |     Le fichier est un JSON    |entrer un fichier JSON           |                                charger()                                             | haute  |
|             ST-001             |            CT-003           |     Pas de fichier entré      |n'entrer aucun fichier           |                             fin execution                                            | haute  |
|             ST-001             |            CT-004           |Le fichier JSON est invalide   |   champ nom absent              |-Message: "le champ nom n'existe pas dans le fichier JSON"<br>-fin execution           | haute  |
|             ST-001             |            CT-005           |Le fichier JSON est invalide   |   champ prenom absent           |-Message: "le champ prenom n'existe pas dans le fichier JSON"<br>-fin execution        | haute  |
|             ST-001             |            CT-006           |Le fichier JSON est invalide   |   champ sexe absent             |-Message: "le champ sexe n'existe pas dans le fichier JSON"<br>-fin execution          | haute  |
|             ST-001             |            CT-007           |Le fichier JSON est invalide   |   champ numeroDePermis absent   |-Message: "le champ numeroDePermis n'existe pas dans le fichier JSON"<br>-fin execution| haute  |
|             ST-001             |            CT-008           |Le fichier JSON est invalide   |   champ cycle absent            |-Message: "le champ cycle n'existe pas dans le fichier JSON"<br>-fin execution         | haute  |
|             ST-001             |            CT-009           |Le fichier JSON est invalide   |   champ ordre absent            |-Message: "le champ ordre n'existe pas dans le fichier JSON"<br>-fin execution         | haute  |
|             ST-001             |            CT-010           |Le fichier JSON est invalide   |   champ activites absent        |-Message: "La clé activites n'est pas existante"<br>-fin execution                     | haute  |
|             ST-001             |            CT-011           |Le sexe entré est valide       | declaration.obtenirSexe = 2     |                charger()                                                              | haute  |
|             ST-001             |            CT-012           |Le sexe entré est invalide     | declaration.obtenirSexe = 5     |-Message: "Le champ sexe doit avoir seulement les valeurs 0,1,2"                      | haute  |
|             ST-001             |            CT-013           |Le fichier JSON est valide     |  tous les champs sont presents  |                      charger()                                                       | haute  | 
|             ST-002             |            CT-001           | la date est dans le bon format| activite.obtenirDate() = "2018-05-01"|  VerifierCategorie                                                              | moyenne|
|             ST-002             |            CT-002           |mauvais format de la date      | activite.obtenirDate() = "01-05-2018"| -Message: " L'activité n'a pas la Date en format ISO8601 (AAAA-MM-JJ)"<br> L'activité est ignorée| moyenne|
|             ST-002             |            CT-003           |la catégorie de l'activité est reconnue| activite.obtenircategorie() = "cours"| verifierDescription()                                                  | moyenne|
|             ST-002             |            CT-004           |categorie de l'activité non reconnue| activite.obtenircategorie() = "Integration"|-Message:L'activité n'est pas dans une catégorie reconnue. Elle sera ignorée"|moyenne|
|             ST-002             |            CT-005           |description complète de l'activite  |activite.obtenirDescription() = "Information sur l'examen"| verifierNombreHeure()
|             ST-002             |            CT-006           |description incomplète de l'activite|activite.obtenirDescription() = "Information"|-Message: "Erreur : L'activite " " contient une description inferieur a 20 caracteres."<br>-fin execution|haute|
|             ST-002             |            CT-007           |L'activité a un nb d'heures entier positif|verifierNombreHeurePourActivite = 5    |   verifierHeuresMaximumActivite                                     | haute  |
|             ST-002             |            CT-008           |L'activité a un nb d'heures négatif       |verifierNombreHeurePourActivite = -1   |-Message: "Le nombre d'heures entré pour " " est invalide, il doit être supérieur ou égal à 1. L'activité sera ignorée"<br>-fin execution|haute  |
|             ST-003             |            CT-001           |cycle architecte est valide    |cycle =="2016-2018" ou "2018-2020" ou "2020-2022"|  verifierNumeroDePermis                                              |haute   |
|             ST-003             |            CT-002           |cycle architecte est invalide  | cycle == "2022-2024"            | -Message: "Le cycle entrée n'est pas valide, Le cycle doit être "2016-2018" ou "2018-2020" ou "2020-2022""| haute|
|             ST-003             |            CT-003           |numeroDePermis architecte valide|  numeroDePermis == "A1234"     | verifierHeureTransfere                                                               | haute  |
|             ST-003             |            CT-004           |numeroDePermis architecte invalid| numeroDePermis == "A123"      |-Message: "Le numero de permis n'est pas valide"<br>-fin execution                    |haute   |
|             ST-003             |            CT-005           |numeroDePemris acrhitecte invalid2| numerDePermis == "C2365"     |-Message: "Le numero de permis n'est pas valide"<br>-fin execution                    |haute   |
|             ST-003             |            CT-006           |date Activités valide Architecte |date inclus dans"2018-04-01 - 2020-04-01" ou "2016-04-01 - 2018-07-01" ou "2020-04-01 - 2022-04-01"|  verifierHeureMaximumActivite| moyenne |
|             ST-003             |            CT-007           |date Activités Invalid Architecte| date non inclus dans"2018-04-01 - 2020-04-01" ou "2016-04-01 - 2018-07-01" ou "2020-04-01 - 2022-04-01"|Message: " La date de l'activité n'est pas valide. Verifier votre declaration"| moyenne|
|             ST-003             |            CT-008           | heures_transferees_du_cycle_precedent valide  | Hheures_transferees_du_cycle_precedent <= 7| Incrementer Heure pr groupactivites                                |moyenne|
|             ST-003             |            CT-009           | heures_transferees_du_cycle_precedent Invalide| heures_transferees_du_cycle_precedent > 7 |-heures_transferees_du_cycle_precedent = 7<br>"Les heures transférées du cycle précédent dépassent 7. Uniquement 7 heures seront considérées"|moyenne|
|             ST-003             |            CT-010           | Heure pr groupactivites valide     |Heures (cours+atelier+séminaire+coloque+conférence+lecture dirigée+heures_transferees_du_cycle_precedent) >= 17| incrementerCompteurHeures  | moyenne|
|             ST-003             |            CT-011           | Heure pr groupactivites Invalide   | Heures (cours+atelier+séminaire+coloque+conférence+lecture dirigée+heures_transferees_du_cycle_precedent) < 17| Message: "Le nombre d'Heures déclarées dans les catégories: cours, atelier, séminaire, colloque, conférence, lecture dirigée n'atteint pas 17"|moyenne|
|             ST-003             |            CT-012           | Heure pr activite valide      | Heures presentation <= 23      |       IncrementerCompteurHeures                       | moyenne|                                               |moyenne|
|             ST-003             |            CT-013           | Heure pr activite Invalide    | Heures presentation > 23       |   Heures Presentation = 23                            | moyenne|                                                |moyenne|
|             ST-003             |            CT-014           | Heure pr activite valide      | Heures groupeDeDiscussion <= 17|  IncrementerCompteurHeures                            | moyenne|
|             ST-003             |            CT-015           | Heure pr activite Invalide    |Heures groupeDeDiscussion > 17  |  Heures groupeDeDiscussion = 17                       | moyenne|
|             ST-003             |            CT-016           | Heure pr activite valide      |Heures projetDeRecherche <= 23  |  IncrementerCompteurHeures                            | moyenne|
|             ST-003             |            CT-017           | Heure pr activite Invalide    |Heures projetDeRecherche > 23   | Heures projetDeRecherche = 23                         | moyenne|
|             ST-003             |            CT-018           | Heure pr activite valide      |Heures redactionProfessionnelle <= 17 | IncrementerCompteurHeures                       | moyenne|
|             ST-003             |            CT-019           | Heure pr activite Invalide    |Heures redactionProfessionnelle > 17  | Heures redactionProfessionnelle = 17            | moyenne|
|             ST-003             |            CT-020           |Heures totales formationContinue valide  |Heures formationContinue >=42| reponse : complète                             | haute  |
|             ST-003             |            CT-021           |Heures totales formationContinue Invalide| Heures formationContinue <42| reponse : Incomplète                           | haute  |
|             ST-004             |            CT-001           |cycle Géologue est valide      | cycle == "2018-2021"            |     verifierNumeroDePermis()                                                         |haute   |
|             ST-004             |            CT-002           |cycle Géologue est Invalide    | cycle == "2018-2020"            | -Message:"Le cycle entrée n'est pas valide, Le cycle doit être "2018-2021""          | haute  |
|             ST-004             |            CT-003           |numeroDePermis geologue valide | numeroDePermis == "AB1234"       |verifierLettreNumPermis()                                                             | haute  |
|             ST-004             |            CT-004           |numeroDePermis geologue valide |  declaration.obtenirNom().charAt(0) = 'A'<br>declaration.obtenirPrenom().charAt(0)= 'B'<br>numeroDePermis = "AB1234"| verifierActivites      | haute  |
|             ST-004             |            CT-005           |numeroDePermis geologue Invalid|  declaration.obtenirNom().charAt(0) = 'A'<br>declaration.obtenirPrenom().charAt(0)= 'B'<br>numeroDePermis = "AC1234"|-message= "Le numero de Permis est invalide car les deux premieres lettres ne correspondent pas aux premieres lettres du nom et prenom"<br>-fin execution| haute|
|             ST-004             |            CT-006           |numeroDePermis geologue Invalid1|  declaration.obtenirNom().charAt(0) = 'A'<br>declaration.obtenirPrenom().charAt(0)= 'B'<br>numeroDePermis = "BC1234"|-message= "Le numero de Permis est invalide car les deux premieres lettres ne correspondent pas aux premieres lettres du nom et prenom"<br>-fin execution| haute|
|             ST-004             |            CT-007           |numeroDePermis geologue Invalid2| numeroDePermis == "AB123"      |-Message: "Le numero de permis n'est pas valide"<br>-fin execution                    | haute  |
|             ST-004             |            CT-008           |numeroDePermis geologue Invalid3| numeroDePermis =="X123"       |-Message: "Le numero de permis n'est pas valide"<br>-fin execution                    | haute  |
|             ST-004             |            CT-009           | date Activités valide geologue |date inclus dans "2018-06-01 - 2021-06-01|         verifierActivite                                                   | moyenne |
|             ST-004             |            CT-010           | date Activités Invalide gologue|date non inclus dans "2018-06-01 - 2021-06-01|Message: " La date de l'activité n'est pas valide. Verifier votre declaration"| moyene |
|             ST-004             |            CT-011           |Heure pr activite valide        | Heures cours >= 22             |          IncrementerCompteurHeures                   | moyenne|
|             ST-004             |            CT-012           |Heure pr activite Invalide      | Heures cours < 22              |Message: "Le nombre d'heures entré pour Cours est invalide, il doit être supérieur ou egale a 22."| moyenne|
|             ST-004             |            CT-013           |Heure pr activite valide        | Heures projetDeRecherche >= 3  |          IncrementerCompteurHeures                   | moyenne|
|             ST-004             |            CT-014           |Heure pr activite Invalide      | Heures projetDeRecherche < 3   |Message: "Le nombre d'heures entré pour Projet de Recherche est invalide, il doit être supérieur ou egale a 3."|moyenne|
|             ST-004             |            CT-015           |Heure pr activite valide        | Heures groupeDeDiscussion >= 1 |          IncrementerCompteurHeures                   | moyenne|
|             ST-004             |            CT-016           |Heure pr activite Invalide      | Heures groupeDeDiscussion < 1  |Message: "Le nombre d'heures entré pour Groupe De Discussion est invalide, il doit être supérieur ou egale a 1." |moyenne|
|             ST-004             |            CT-017           | heures_transferees_du_cycle_precedent n'existe pas |heures_transferees_du_cycle_precedent n.est pas dans activité | verifierAutreActivite                                         |moyenne|
|             ST-004             |            CT-018           | heures_transferees_du_cycle_precedent existe |heures_transferees_du_cycle_precedent == 2                          |Message: "Les heures transfere ne sont pas supporté par l'ordre des géologues"|moyenne|
|             ST-004             |            CT-019           |Heures totales formationContinue valide  |Heures formationContinue >=55| reponse : complète                             | haute  |
|             ST-004             |            CT-020           |Heures totales formationContinue Invalide| Heures formationContinue <55| reponse : Incomplète                           | haute  |
|             ST-005             |            CT-001           |cycle Psychologue est valide   |  cycle == "2018-2023"           | verifierNumeroDePermis()                                                             | haute  |
|             ST-005             |            CT-002           |cycle Psychologue est Invalid  |  cycle == "2015-2018"           |  -Message: "Le cycle entrée n'est pas valide, Le cycle doit être "2018-2023"         | haute  |    
|             ST-005             |            CT-003           |numeroDePermis psycho valide   | numeroDePermis =="12345-12"     |   verifierActivites()                                                                | haute  |
|             ST-005             |            CT-004           |numeroDePermis psycho Invalid  | numeroDePermis == "abc34-56"    |-Message: "Le numero de permis n'est pas valide"<br>-fin execution                    | haute  |
|             ST-005             |            CT-005           |numeroDePermis psycho Invalid2 | numeroDePermis == "1234567"     |-Message: "Le numero de permis n'est pas valide"<br>-fin execution                    | haute  |
|             ST-005             |            CT-006           | date Activités valide psychologue |date inclus dans "2018-01-01 - 2023-01-01|         verifierActivite                                                   | moyenne |
|             ST-005             |            CT-007           | date Activités Invalide psychologue|date non inclus dans "2018-01-01 - 2023-01-01|Message: " La date de l'activité n'est pas valide. Verifier votre declaration"| moyene |
|             ST-005             |            CT-008           |Heure pr activite valide       | Heures cours >= 25              |          IncrementerCompteurHeures                                                   |moyenne |
|             ST-005             |            CT-009           |Heure pr activite Invalide     | Heures cours < 25               |Message: "Un minimum de 25 heures par cycle sont nécessaires dans la catégorie cours" |moyenne |
|             ST-005             |            CT-010           |Heure pr activite valide       | Heure conference <= 15          |          IncrementerCompteurHeures                                                   |moyenne |
|             ST-005             |            CT-011           |Heure pr activite Invalide     | Heure conference > 15           | Heure conference == 15                                                               |moyenne |
|             ST-005             |            CT-012           | heures_transferees_du_cycle_precedent n'existe pas |heures_transferees_du_cycle_precedent n.est pas dans activité | verifierAutreActivite              |moyenne |
|             ST-005             |            CT-013           | heures_transferees_du_cycle_precedent existe |heures_transferees_du_cycle_precedent = 2                           |Message: "Les heures transfere ne sont pas supporté par l'ordre des psychologues"|moyenne|
|             ST-005             |            CT-014           |Heures totales formationContinue valide       |Heures formationContinue >= 90                                      | reponse : complète                 | haute  |
|             ST-005             |            CT-015           |Heures totales formationContinue Invalide     | Heures formationContinue < 90                                      |reponse : Incomplète                | haute  |
|             ST-006             |            CT-001           |cycle podiatre est valide      | cycle == "2018-2021"            |     verifierNumeroDePermis()                                                         |haute   |
|             ST-006             |            CT-002           |cycle podiatre est Invalide    | cycle == "blabla"               | -Message:"Le cycle entrée n'est pas valide, Le cycle doit être "2018-2021""          | haute  |
|             ST-006             |            CT-003           |numeroDePermis podiatre valide | numeroDePermis == "12345"       |     verifierActivites()                                                              | haute  |
|             ST-006             |            CT-007           |numeroDePermis podiatre Invalid1| numeroDePermis == "AB123"      |-Message: "Le numero de permis n'est pas valide"<br>-fin execution                    | haute  |
|             ST-006             |            CT-008           |numeroDePermis podiatre Invalid2| numeroDePermis =="123"         |-Message: "Le numero de permis n'est pas valide"<br>-fin execution                    | haute  |
|             ST-005             |            CT-009           | date Activités valide podiatre |date inclus dans "2018-06-01 - 2021-06-01|         verifierActivite                                                   | moyenne |
|             ST-006             |            CT-010           | date Activités Invalide podiatre|date non inclus dans "2018-06-01 - 2021-06-01|Message: " La date de l'activité n'est pas valide. Verifier votre declaration"| moyene |
|             ST-006             |            CT-011           |Heure pr activite valide        | Heures cours >= 22             |          IncrementerCompteurHeures                   | moyenne|
|             ST-006             |            CT-012           |Heure pr activite Invalide      | Heures cours < 22              |Message: "Le nombre d'heures entré pour Cours est invalide, il doit être supérieur ou egale a 22."| moyenne|
|             ST-006             |            CT-013           |Heure pr activite valide        | Heures projetDeRecherche >= 3  |          IncrementerCompteurHeures                   | moyenne|
|             ST-006             |            CT-014           |Heure pr activite Invalide      | Heures projetDeRecherche < 3   |Message: "Le nombre d'heures entré pour Projet de Recherche est invalide, il doit être supérieur ou egale a 3."|moyenne|
|             ST-006             |            CT-015           |Heure pr activite valide        | Heures groupeDeDiscussion >= 1 |          IncrementerCompteurHeures                   | moyenne|
|             ST-006             |            CT-016           |Heure pr activite Invalide      | Heures groupeDeDiscussion < 1  |Message: "Le nombre d'heures entré pour Groupe De Discussion est invalide, il doit être supérieur ou egale a 1." |moyenne|
|             ST-006             |            CT-017           | heures_transferees_du_cycle_precedent n'existe pas |heures_transferees_du_cycle_precedent n.est pas dans activité | verifierAutreActivite                                         |moyenne|
|             ST-006             |            CT-018           | heures_transferees_du_cycle_precedent existe |heures_transferees_du_cycle_precedent == 2                          |Message: "Les heures transfere ne sont pas supporté par l'ordre des podiatres"|moyenne|
|             ST-006             |            CT-019           |Heures totales formationContinue valide  |Heures formationContinue >=60| reponse : complète                             | haute  |
|             ST-006             |            CT-020           |Heures totales formationContinue Invalide| Heures formationContinue <60| reponse : Incomplète                           | haute  |





