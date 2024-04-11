Merci pour ce bon moment.

- Mower-core pour les "entités" et "services" metier.
- Mower-batch pour l'application spring boot / spring batch.
  
Le même test demandé dans le kata est réalisé dans les deux modules,
en mode TI dans le module batch pour vérifier la bonne injection et orchestration des beans déclarés.

Autocritique,
Ce qui allait devenir le module mower-core a été fait avant de voir la contrainte d'utiliser spring-batch.
J'ai gardé la maille de traitement des batchs à Lawn parce que déjà faite ainsi et qu'il me semble que la démonstration de connaissance spring-batch est faite.
Une meilleure utilisation de l'esprit de spring-batch ici serait de profiter de sa capacité à fractionner les lectures d'entrants et écritures de sortants.
Utiliser FlatFileItemReader / FlatFileItemWriter et passer la maille de traitement du batch aux items Mower, plutot que l'unique Lawn.
Ce qui permettrait de traiter de façon optimale des explorations d'une pelouse avec un gros volume de tondeuses.


J'ajoute un exemple de Dockerfile et un appel au plugin maven de packaging spring boot, testé localement.
Je ne dispose pas de pipeline CI/CD, je ne met donc pas de fichier .gitlab-ci.yml ou équivalent Github dont les modeles sont disponibles dans les Github Actions. 

