package fr.bpifrance.qua.qformation.dao.test.tu;

import fr.bpifrance.qua.qformation.dao.FormationRepositoryAdapter;
import fr.bpifrance.qua.qformation.domaine.Formation;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
@DataJpaTest
public class FormationTest {
    @Autowired
    FormationRepositoryAdapter daoFormation;
    @Autowired
    private  TestEntityManager testEntityManager;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    @Test
    public void testObtenirFormationsVide(){
        assertThat(daoFormation.obtenirFormations()).isEmpty();
    }

    @Test
    @Sql(scripts ="/init1.sql" ) // Initialisation de la db de test
    @Sql(scripts ="/close1.sql",executionPhase = AFTER_TEST_METHOD ) // Vidage de la db à la fin du test
    public void testObtenirFormationsNonVide1() throws ParseException {
        assertThat(daoFormation.obtenirFormations()).isNotEmpty();
        assertThat(daoFormation.obtenirFormations()).hasSize(1);
    }
    @Test
    @Sql(scripts ="/init2.sql" ) // Initialisation de la db de test
    @Sql(scripts ="/close1.sql",executionPhase = AFTER_TEST_METHOD ) // Vidage de la db à la fin du test
    public void testObtenirFormationsNonVide2() throws ParseException {
        assertThat(daoFormation.obtenirFormations()).isNotEmpty();
        assertThat(daoFormation.obtenirFormations()).hasSize(4);
    }
    @Test
    @Sql(scripts ="/init1.sql" ) // Initialisation de la db de test
    @Sql(scripts ="/close1.sql",executionPhase = AFTER_TEST_METHOD ) // Vidage de la db à la fin du test
    public void testObtenirFormationParCode() throws ParseException {
        assertThat(daoFormation.obtenirFormations()).isNotEmpty();
        assertThat(daoFormation.obtenirFormation("CSLM")).isNotNull();
        assertThat(daoFormation.obtenirFormation("CSLM").getFormateur()).isEqualTo("CHM");
        assertThat(daoFormation.obtenirFormation("CSLM").getLibelle()).isEqualTo("Cucumber");
    }

    @Test
    @Sql(scripts ="/close1.sql",executionPhase = AFTER_TEST_METHOD ) // Vidage de la db à la fin du test
    public void testPlanifierUneFormation() throws ParseException {

        assertThat(daoFormation.formationRepository.count()).isEqualTo(0);
        Formation lFormation = new Formation("CSLM", "Cucumber",sdf.parse("01/07/2018"), "RDC1", "Claude-Henri", "planifié", true, 10, 0 );
        daoFormation.planifierFormation(lFormation);
        assertThat(daoFormation.formationRepository.count()).isEqualTo(1);
    }

    @Test
    @Ignore
    @Sql(scripts ="/init1.sql" ) // Initialisation de la db de test
    @Sql(scripts ="/close1.sql",executionPhase = AFTER_TEST_METHOD ) // Vidage de la db à la fin du test
    public void testModifierUneFormation() throws ParseException {
        assertThat(daoFormation.formationRepository.count()).isEqualTo(1);
        Formation f = daoFormation.formationRepository.getOne((long) 1);
        f.setFormateur("MARGUERITE");
        f.setMaximumInscrits(8);
        daoFormation.modifierFormation(f);
        assertThat(daoFormation.formationRepository.count()).isEqualTo(1);
        f = daoFormation.formationRepository.getOne((long) 1);
        assertThat(f.getFormateur()).isEqualTo("MARGUERITE");
        assertThat(f.getMaximumInscrits()).isEqualTo(8);
    }

    @AfterClass
    public static void tearDownClass(){

    }
}
