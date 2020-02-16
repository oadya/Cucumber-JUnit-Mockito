package fr.bpifrance.qua.qformation.api.test.ti;


import cucumber.api.java.Before;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Etantdonné;
import cucumber.api.java.fr.Quand;
import fr.bpifrance.qua.qformation.dao.models.FormationEntity;
import fr.bpifrance.qua.qformation.dao.models.SujetEntity;
import fr.bpifrance.qua.qformation.domaine.Formation;
import fr.bpifrance.qua.qformation.domaine.FormationMetier;
import fr.bpifrance.qua.qformation.domaine.FormationRepositoryPort;
import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class FormationDefinitions {
	@Autowired
	@Qualifier("FormationRepositoryAdapter")
	private FormationRepositoryPort formationRepositoryPort;
	Formation formation;
	private FormationMetier formationMetier;
	@Autowired
    private TestEntityManager testEntityManager;
	@Autowired
	DataSource ds;
	/* A faire
	@Before
	public void setup() throws SQLException {
		ScriptUtils.executeSqlScript(ds.getConnection(), new ClassPathResource("close1.sql"));
		formationMetier = new FormationMetier(formationRepositoryPort);
	}
	
	@Etantdonné("^je suis un administrateur$")
	public void je_suis_un_administrateur() throws Throwable {
	    System.out.println("je suis un admin");
	}

	@Etantdonné("^il n'existe pas un sujet de formation avec le code \"([^\"]*)\"$")
	public void il_n_existe_pas_un_sujet_de_formation_avec_le_code(String code) throws Throwable {
		assertThat(formationMetier.obtenirSujets()).hasSize(0);
	}

	@Quand("^je planifie la formation suivante$")
	public void je_planifie_la_formation_suivante(List<Formation> formations) throws Throwable {
		formation = formationMetier.planifierFormation(formations.get(0));
	}

	@Alors("^le système refuse la création de la formation de cette formation$")
	public void le_système_refuse_la_création_de_la_formation_de_cette_formation() throws Throwable {
		List<FormationEntity> listFormations = testEntityManager.getEntityManager().createQuery(
				"select f from FormationEntity f").getResultList();
		assertThat(listFormations).hasSize(0);
	}

	@Etantdonné("^il existe un sujet de formation avec le code \"([^\"]*)\"$")
	public void il_existe_un_sujet_de_formation_avec_le_code(String code) throws Throwable {
		SujetEntity sujet = new SujetEntity(); sujet.setCode(code);
		testEntityManager.persist(sujet);
	}

	@Etantdonné("^la salle \"([^\"]*)\" n'est pas disponible pour la date \"([^\"]*)\"$")
	public void la_salle_n_est_pas_disponible_pour_la_date(String salle, String date) throws Throwable {

	}

	@Etantdonné("^la salle \"([^\"]*)\" est disponible pour la date \"([^\"]*)\"$")
	public void la_salle_est_disponible_pour_la_date(String arg1, String arg2) throws Throwable {
		assertThat(true).isTrue();
	}

	@Alors("^le système accepte la création de la formation de cette formation$")
	public void le_système_accepte_la_création_de_la_formation_de_cette_formation() throws Throwable {
		List<FormationEntity> listFormations = testEntityManager.getEntityManager().createQuery(
				"select f from FormationEntity f").getResultList();
		assertThat(listFormations).hasSize(1);
	}

	@Alors("^il existe une formation avec les informations suivantes$")
	public void il_existe_une_formation_avec_les_informations_suivantes(List<FormationEntity> formations) throws Throwable {
		FormationEntity formationExpected = formations.get(0);
		FormationEntity formation = testEntityManager.getEntityManager().createQuery(
				"select f from FormationEntity f where f.code='" + formationExpected.getCode()+"'",FormationEntity.class).getSingleResult();
		assertThat(formation).isEqualToIgnoringGivenFields(formationExpected, "id", "date");
	}


	public  void setupTestData(final String sqlFile){
		//On initialise la base de données
		//assertThat(testEntityManager).isNotNull();
		Session session = this.testEntityManager.getEntityManager().unwrap(Session.class);
		session.doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				try {
					File script = new File(getClass().getResource("/" + sqlFile).getFile());
					RunScript.execute(connection, new FileReader(script));
				} catch (FileNotFoundException e) {
					throw new RuntimeException("could not initialize with script");
				}
			}
		});
	}
*/

}
