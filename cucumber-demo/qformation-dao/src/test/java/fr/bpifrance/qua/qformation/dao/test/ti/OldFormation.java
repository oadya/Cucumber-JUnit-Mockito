package fr.bpifrance.qua.qformation.dao.test.ti;


import fr.bpifrance.qua.qformation.domaine.*;
import fr.bpifrance.qua.qformation.domaine.exception.FormationFermeeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
@DataJpaTest
public class OldFormation {
	
	@Autowired
	@Qualifier("FormationRepositoryAdapter")
	private FormationRepositoryPort formationRepositoryPort;
	
	@Test
	public void test() throws ParseException, FormationFermeeException{
	/*	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		final FormationMetier formationMetier = new FormationMetier(formationRepositoryPort);
		System.out.println("Début du test des fonctionnalités de gestion dues formations");
		
		//Test de la liste vide des transporteurs 
		assertThat(formationMetier.obtenirFormations()).hasSize(0);
		//Test de l'ajout d'un transporteur
		Formation lFormation = new Formation("CSLM", "Cucumber",sdf.parse("01/07/2018"), "RDC1", "Claude-Henri", "planifié", true, 10, 0 );
		formationMetier.planifierFormation(lFormation);
		assertThat(formationMetier.obtenirFormations()).hasSize(1);
		lFormation=formationMetier.consulterFormation(1);
		assertThat(lFormation.getFormateur()).isEqualTo("Claude-Henri");
		assertThat(lFormation.getMaximumInscrits()).isEqualTo(10);
		assertThat(lFormation.getNombreInscrits()).isEqualTo(0);
		lFormation.setFormateur("Gilles");
		formationMetier.modifierFormation(lFormation);
		lFormation=formationMetier.consulterFormation(1);
		assertThat(lFormation.getFormateur()).isEqualTo("Gilles");
		assertThat(formationMetier.obtenirFormations()).hasSize(1);
		formationMetier.planifierFormation(new Formation("ABDD", "Atelier BDD",sdf.parse("02/07/2018"), "RDC1", "Claude-Henri", "planifié", true,10,0 )	);
		formationMetier.planifierFormation(new Formation("GHER", "Atelier Gherkin",sdf.parse("02/07/2018"), "RDC1", "Ahmed", "planifié", true,10,0 )	);
		assertThat(formationMetier.obtenirFormations()).hasSize(3);

		formationMetier.annulerFormation(3);
		assertThat(formationMetier.obtenirFormations()).hasSize(2);
		Collaborateur c1= new Collaborateur();
		c1.setNom("MARGUERITE Claude");
		c1.setMatricule("m10657");
		Collaborateur c2= new Collaborateur();
		c2.setNom("MARGUERITE Laura");
		c2.setMatricule("m20657");
		formationMetier.inscrireFormation(1, c1);
		formationMetier.inscrireFormation(1, c2);
		//On doit vérifier stagiaire
		lFormation=formationMetier.consulterFormation(1);
		assertThat(lFormation.getNombreInscrits()).isEqualTo(2);
		formationMetier.inscrireFormation(2, c1);
		assertThat(formationMetier.obtenirStagiairesFormation(1)).hasSize(2);
		assertThat(formationMetier.obtenirStagiairesFormation(2)).hasSize(1);
		formationMetier.desinscrireFormation(1, "m10657");
		lFormation=formationMetier.consulterFormation(1);
		assertThat(lFormation.getNombreInscrits()).isEqualTo(1);
		assertThat(formationMetier.obtenirStagiairesFormation(1)).hasSize(1);
		assertThat(formationMetier.obtenirStagiairesFormation(1).get(0).getMatricule()).isEqualTo("m20657");
		
		//tester limite
		formationMetier.planifierFormation(new Formation("ABDD", "Atelier BDD",sdf.parse("02/07/2018"), "RDCA", "Paul", "planifi�", true,2,0 )	);
		assertThat(formationMetier.consulterFormation(4).isOuverte()).isTrue();
		formationMetier.inscrireFormation(4, c1);
		formationMetier.inscrireFormation(4, c2);
		assertThat(formationMetier.consulterFormation(4).isOuverte()).isFalse();
		assertThat(formationMetier.consulterFormation(4).getNombreInscrits()).isEqualTo(2);
		Collaborateur c3= new Collaborateur();
		c3.setNom("BOB Yumi");
		c3.setMatricule("m20657");
//		formationMetier.inscrireFormation(4, c3);
		assertThat(formationMetier.consulterFormation(4).getNombreInscrits()).isEqualTo(2);
		assertThat(formationMetier.obtenirSujets()).hasSize(0);
		formationMetier.ajouterSujet(new Sujet("ABDD","Atelier BDD","MARGUERITE Claude",""));
		assertThat(formationMetier.obtenirSujets()).hasSize(1);
		
		assertThat(formationMetier.obtenirSalles()).hasSize(0);
		formationMetier.ajouterSalle(new Salle("RDC-1.2",10));
		assertThat(formationMetier.obtenirSalles()).hasSize(1);
		assertThat(formationMetier.obtenirSalles().get(0).getCapacite()).isEqualTo(10);
		*/
	}
}
