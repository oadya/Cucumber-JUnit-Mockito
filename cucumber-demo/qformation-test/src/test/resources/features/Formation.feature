# language: fr
@Formation @TNR
Fonctionnalité: Formation
En tant qu'administrateur des formations du service Qualification, je dois pouvoir gérer les différentes formations du services

@XXX
Scénario: Planifier une formation sans sujet
Etant donné je suis un administrateur
Et qu'aucun sujet de formation ne soit disponible
Quand je veux planifier une formation
Alors l'opération ne se fait pas et le système me renvoie le message "Il n'y a aucun sujet de formation"

Scénario: Ajouter un sujet de formation
Etant donné je suis un administrateur
Quand je planifie un sujet de formation
|code	|libellé			| formateur			| prerequis	|
|SELE	|Cucumber Selenium  | Marguerite Claude	|  			| 
Alors le sujet "SELE" est disponible dans la liste

Scénario: Planifier une formation sans salle
Etant donné je suis un administrateur
Et qu'aucune salle ne soit enregistrée
Quand je veux planifier une formation
Alors l'opération ne se fait pas et le système me renvoie le message "Il n'y a aucune salle"

Plan du scénario: Ajouter une salle
Etant donné je suis un administrateur
Quand j'ajoute la salle "<salle>" d'une capacité de <capacité>
Alors la salle "<salle>" est disponible avec sa capacité maximale de <capacité>
Exemples:
|	salle	|	capacité	|
|	RDC1	|	8			|
|	ETA1.9	|	8			|

Scénario: Planifier une formation
Etant donné je suis un administrateur
Quand je planifie une formation
|code	                 |date			|salle	| statut   |	maximum	|
|SELE-Cucumber Selenium  | 18/06/2018	|RDC1	| Nouvelle |			|
Alors la formation 1 est disponible pour inscription
Et les informations suivantes sont affichées
|maximumInscrits	|nombreInscrits			|  ouvert		|     
|8					|0  					|  Disponible	|

Plan du scénario: inscrire un stagiaire à une formation
Etant donné je suis un administrateur
Et que la formation 1 est disponible pour inscription
Quand j'inscris le stagiaire "<matricule>":"<nom>" à la formation 1
Alors la formation 1 est disponible et le nombre d'inscrits est de <nombre>
Exemples:
|matricule	|nom			|  nombre	|
|m10001		|MARGUERITE A 	|  1		|	
|m10002		|MARGUERITE B 	|  2		|
|m10003		|MARGUERITE C 	|  3		|
|m10004		|MARGUERITE C 	|  4		|

Scénario: inscrire un dernier stagiaire
Etant donné je suis un administrateur
Quand je planifie une formation
|code	                |date			|salle	| statut   |	maximum	|
|SELE-Cucumber Selenium | 18/06/2018	|RDC1	| Nouvelle |	1		|
Et j'inscris le stagiaire "m10657":"BOUBOURLE Lens" à la formation 2
Alors la formation 2 est fermée pour inscription
Et les informations suivantes sont affichées
|maximumInscrits	|nombreInscrits			|  ouvert		|     
|1					|1  					|  Complet		|