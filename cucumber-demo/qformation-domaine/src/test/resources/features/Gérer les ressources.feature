# language: fr
@Formation
Fonctionnalité: Gérer les ressources de formations
En tant qu'administrateur des formations du service Qualification,
je dois pouvoir gérer les différentes ressources du services

Scénario: Ajouter un sujet de formation
Etant donné je suis un administrateur
Et il n'existe pas un sujet de formation avec le code "CSLM"
Quand je crée le sujet de formation suivant
| code	|libelle			| formateur	| prerequis   |
| CSLM	|Cucumber Selenium  | CHM		| Lol         |
Alors le système accepte la création du sujet

Scénario: Ajouter un sujet de formation existant
Etant donné je suis un administrateur
Et il existe un sujet de formation avec le code "SEL"
Quand je crée le sujet de formation suivant
| code	|libelle			| formateur	| prerequis   |
| SEL	|Cucumber Selenium  | CHM		| Lol         |
Alors le système refuse la création du sujet