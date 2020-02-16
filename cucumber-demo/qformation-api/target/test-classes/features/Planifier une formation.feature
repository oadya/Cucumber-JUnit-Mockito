# language: fr
@Formation
Fonctionnalité: Planifier une formation
  En tant qu'administrateur des formations du service Qualification, je dois pouvoir gérer les différentes formations du services


  Scénario: Planifier une formation avec un code de formation inexistant
    Etant donné je suis un administrateur
    Et il n'existe pas un sujet de formation avec le code "CSLM"
    Quand je planifie la formation suivante
      |code	|libelle			|date			| salle	| formateur	| statut   | ouverte	|
      |CSLM	|Cucumber Selenium  | 18/06/2018	| RDC1	| CHM		| nouvelle | Oui		|
    Alors le système refuse la création de la formation de cette formation

  Scénario: Planifier une formation pour une salle non disponible
    Etant donné je suis un administrateur
    Et il existe un sujet de formation avec le code "CSLM"
    Et la salle "RDC1" n'est pas disponible pour la date "18/06/2018"
    Quand je planifie la formation suivante
      |code	|libelle			|date			| salle	| formateur	| statut   | ouverte	|
      |CSLM	|Cucumber Selenium  | 18/06/2018	| RDC2	| CHM		| nouvelle | Oui		|
    Alors le système refuse la création de la formation de cette formation

  Scénario: Planifier une formation avec succès
    Etant donné je suis un administrateur
    Et il existe un sujet de formation avec le code "CSLM"
    Et la salle "RDC1" est disponible pour la date "18/06/2018"
    Quand je planifie la formation suivante
      | code	| libelle			  | date			| salle	| formateur	|
      | CSLM	| Cucumber Selenium   | 18/06/2018	    | RDC1	| CHM		|
    Alors le système accepte la création de la formation de cette formation
    Et il existe une formation avec les informations suivantes
      | code	| libelle			  | date			| salle	| formateur	| statut    | ouverte |
      | CSLM	| Cucumber Selenium   | 18/06/2018	    | RDC1	| CHM		| Nouvelle  | Oui     |


