#Workflow

Dans notre projet, on a séparé les fonctionnalités selon les différents ordres 
professionnels et chaque fonctionnalité est développée dans une branche à part
et non dans la branche master. Donc on utilise le **Feature Branch Workflow**.
Aussi on a une branche pour les tests.

## Branches

###*1. Master*
La ranche principale sur laquelle on avait travaillé avant. Elle comporte 
toutes les validations initiales. Quelques validations plus spécifiques ont eu 
lieu dans la branche validationArchitecte qui a été merge dans la 'master' pour 
que les autres membres puissent y avoir accès

###*2. serviceGeologue*
La branche qui s'occupe de verifier les informations de l'ordre des géologues

###*3. servicePsychologue*
La branche qui s'occupe de verifier les informations de l'ordre des psychologues

###*4. testUnit*
La branche qui fait les tests unitaires de tout le projet.