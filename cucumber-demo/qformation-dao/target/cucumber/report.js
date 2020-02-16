$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/Planifier une formation.feature");
formatter.feature({
  "name": "Planifier une formation",
  "description": "  En tant qu\u0027administrateur des formations du service Qualification, je dois pouvoir gérer les différentes formations du services",
  "keyword": "Fonctionnalité",
  "tags": [
    {
      "name": "@Formation"
    }
  ]
});
formatter.scenario({
  "name": "Planifier une formation avec un code de formation inexistant",
  "description": "",
  "keyword": "Scénario",
  "tags": [
    {
      "name": "@Formation"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "je suis un administrateur",
  "keyword": "Etant donné "
});
formatter.match({
  "location": "FormationDefinitions.je_suis_un_administrateur()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "il n\u0027existe pas un sujet de formation avec le code \"CSLM\"",
  "keyword": "Et "
});
formatter.match({
  "location": "FormationDefinitions.il_n_existe_pas_un_sujet_de_formation_avec_le_code(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "je planifie la formation suivante",
  "rows": [
    {
      "cells": [
        "code",
        "libelle",
        "date",
        "salle",
        "formateur",
        "statut",
        "ouverte"
      ]
    },
    {
      "cells": [
        "CSLM",
        "Cucumber Selenium",
        "18/06/2018",
        "RDC1",
        "CHM",
        "nouvelle",
        "Oui"
      ]
    }
  ],
  "keyword": "Quand "
});
formatter.match({
  "location": "FormationDefinitions.je_planifie_la_formation_suivante(Formation\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "le système refuse la création de la formation de cette formation",
  "keyword": "Alors "
});
formatter.match({
  "location": "FormationDefinitions.le_système_refuse_la_création_de_la_formation_de_cette_formation()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Planifier une formation pour une salle non disponible",
  "description": "",
  "keyword": "Scénario",
  "tags": [
    {
      "name": "@Formation"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "je suis un administrateur",
  "keyword": "Etant donné "
});
formatter.match({
  "location": "FormationDefinitions.je_suis_un_administrateur()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "il existe un sujet de formation avec le code \"CSLM\"",
  "keyword": "Et "
});
formatter.match({
  "location": "FormationDefinitions.il_existe_un_sujet_de_formation_avec_le_code(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "la salle \"RDC1\" n\u0027est pas disponible pour la date \"18/06/2018\"",
  "keyword": "Et "
});
formatter.match({
  "location": "FormationDefinitions.la_salle_n_est_pas_disponible_pour_la_date(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "je planifie la formation suivante",
  "rows": [
    {
      "cells": [
        "code",
        "libelle",
        "date",
        "salle",
        "formateur",
        "statut",
        "ouverte"
      ]
    },
    {
      "cells": [
        "CSLM",
        "Cucumber Selenium",
        "18/06/2018",
        "RDC2",
        "CHM",
        "nouvelle",
        "Oui"
      ]
    }
  ],
  "keyword": "Quand "
});
formatter.match({
  "location": "FormationDefinitions.je_planifie_la_formation_suivante(Formation\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "le système refuse la création de la formation de cette formation",
  "keyword": "Alors "
});
formatter.match({
  "location": "FormationDefinitions.le_système_refuse_la_création_de_la_formation_de_cette_formation()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Planifier une formation avec succès",
  "description": "",
  "keyword": "Scénario",
  "tags": [
    {
      "name": "@Formation"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "je suis un administrateur",
  "keyword": "Etant donné "
});
formatter.match({
  "location": "FormationDefinitions.je_suis_un_administrateur()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "il existe un sujet de formation avec le code \"CSLM\"",
  "keyword": "Et "
});
formatter.match({
  "location": "FormationDefinitions.il_existe_un_sujet_de_formation_avec_le_code(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "la salle \"RDC1\" est disponible pour la date \"18/06/2018\"",
  "keyword": "Et "
});
formatter.match({
  "location": "FormationDefinitions.la_salle_est_disponible_pour_la_date(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "je planifie la formation suivante",
  "rows": [
    {
      "cells": [
        "code",
        "libelle",
        "date",
        "salle",
        "formateur"
      ]
    },
    {
      "cells": [
        "CSLM",
        "Cucumber Selenium",
        "18/06/2018",
        "RDC1",
        "CHM"
      ]
    }
  ],
  "keyword": "Quand "
});
formatter.match({
  "location": "FormationDefinitions.je_planifie_la_formation_suivante(Formation\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "le système accepte la création de la formation de cette formation",
  "keyword": "Alors "
});
formatter.match({
  "location": "FormationDefinitions.le_système_accepte_la_création_de_la_formation_de_cette_formation()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "il existe une formation avec les informations suivantes",
  "rows": [
    {
      "cells": [
        "code",
        "libelle",
        "date",
        "salle",
        "formateur",
        "statut",
        "ouverte"
      ]
    },
    {
      "cells": [
        "CSLM",
        "Cucumber Selenium",
        "18/06/2018",
        "RDC1",
        "CHM",
        "Nouvelle",
        "Oui"
      ]
    }
  ],
  "keyword": "Et "
});
formatter.match({
  "location": "FormationDefinitions.il_existe_une_formation_avec_les_informations_suivantes(FormationEntity\u003e)"
});
formatter.result({
  "status": "passed"
});
});