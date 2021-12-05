#Plan de Tests
###Exigences Fonctionnalités à tester

|Identifiants des fonctionnalités| Description des fonctionnalités |
|--------------------------------|---------------------------------|  
|             EF-001             |Saisir en entrée un fichier JSON |
|             EF-002             |     Valider la déclaration      |
|             EF-003             | Produire un document de sortie  |
|             EF-004             |    Produire les statistiques    |

###Suites et cas de Tests

|Identifiants des fonctionnalités|Identifiants des suites de tests|Description des suites de tests|Nombres de cas|
|--------------------------------|--------------------------------|-------------------------------|--------------|
|             EF-001             |             ST-001             |    Tester le fichier JSON     |     11       |
|             EF-002             |             ST-002             |     Tester la declaration     |
|             EF-003             |             ST-003             |
|             EF-004             |             ST-004             |

###Cas de Tests

|Identifiants des suites de tests|Identifiants des cas de tests| Description des cas de Tests  |          Préconditions          |                            Sortie attendue                                           |Priorité|
|--------------------------------|-----------------------------|-------------------------------|---------------------------------|--------------------------------------------------------------------------------------|--------|
|             ST-001             |            CT-001           |Le fichier entré n'est pas JSON|entrer un fichier txt            |                           fin execution                                              | haute  |
|             ST-001             |            CT-002           |     Le fichier est un JSON    |entrer un fichier JSON           |                                charger()                                             | haute  |
|             ST-001             |            CT-003           |     Pas de fichier entré      |n'entrer aucun fichier           |                             fin execution                                            | haute  |
|             ST-001             |            CT-004           |Le fichier JSON est invalide   |   champ nom absent              |-Message: "le champ nom n'existe pas dans le fichier JSON"   -fin execution           | haute  |
|             ST-001             |            CT-005           |Le fichier JSON est invalide   |   champ prenom absent           |-Message: "le champ prenom n'existe pas dans le fichier JSON"   -fin execution        | haute  |
|             ST-001             |            CT-006           |Le fichier JSON est invalide   |   champ sexe absent             |-Message: "le champ sexe n'existe pas dans le fichier JSON"   -fin execution          | haute  |
|             ST-001             |            CT-007           |Le fichier JSON est invalide   |   champ numeroDePermis absent   |-Message: "le champ numeroDePermis n'existe pas dans le fichier JSON"   -fin execution| haute  |
|             ST-001             |            CT-008           |Le fichier JSON est invalide   |   champ cycle absent            |-Message: "le champ cycle n'existe pas dans le fichier JSON"   -fin execution         | haute  |
|             ST-001             |            CT-009           |Le fichier JSON est invalide   |   champ ordre absent            |-Message: "le champ ordre n'existe pas dans le fichier JSON"   -fin execution         | haute  |
|             ST-001             |            CT-010           |Le fichier JSON est invalide   |   champ activites absent        |-Message: "La clé activites n'est pas existante"   -fin execution                     | haute  |
|             ST-001             |            CT-011           |Le fichier JSON est valide     |  tous les champs sont presents  |                      charger()                                                       | haute  |                                                         

