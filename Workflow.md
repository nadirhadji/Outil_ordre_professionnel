#Workflow

Pour notre projet, Nous avons séparé les fonctionnalités selon les différents ordres 
professionnels pour ensuite les développées dans des branche à part. Nous utilisons 
donc le **Feature Branch Workflow**.Aussi, nous avons créé des branches pour les
tests unitaires pour les entités et les services. Pour voir de façon graphique le 
workflow, visiter l'option **graph** du projet inf2050-a21-projet-equipe-17 sur GitLab.

## Branches

###*1. Master*
La branche principale sur laquelle on avait travaillé pour le TP1. Elle comporte 
toutes les validations initiales. Plus le TP2 avançait, plus que les fonctionnalités
se rajoutaient sur cette branche. 

###*2. serviceGeologue/validationGeologue*
La branche qui s'occupe de verifier les restrictions propre à l'ordre des géologues
et que le fichier reponse comprte les bons messages d'erreurs.


###*3. servicePsychologue*
La branche qui s'occupe de verifier les restrictions propre à l'ordre des psychologues
et que le fichier reponse comprte les bons messages d'erreurs.

###*4. testUnit*
Une branche créé pour les tests unitaires qui verifie les service de validation 
de l'ordre géologue et psychologue.

###*5. testeServiceArchitecte*
Étant l'ordre avec le plus de restrction, une branche separée de testUnit a été créé
pour faire les testes unitaires pour architecte.
