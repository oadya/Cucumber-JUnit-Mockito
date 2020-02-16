package fr.bpifrance.qua.qformation.api.test.ti;

import fr.bpifrance.qua.qformation.api.resources.FormationResource;
import fr.bpifrance.qua.qformation.dao.FormationRepositoryAdapter;
import fr.bpifrance.qua.qformation.dao.models.FormationEntity;
import fr.bpifrance.qua.qformation.domaine.Formation;
import fr.bpifrance.qua.qformation.domaine.FormationMetier;
import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
public class FormationTest {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    EntityManager entityManager;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    FormationRepositoryAdapter formationRepository;
    @LocalServerPort //permet d'utiliser le port local du serveur, sinon une erreur "Connection refused"
    private int port;

    @Before
    public void setUp() throws Exception {

    }
    @Test
    public void testObtenirFormationsVide() throws Exception {
        //Pas de formation normalement
        ResponseEntity<Object> responseEntity =
                restTemplate.getForEntity("/api/formations",  Object.class);
        List<Formation> lFormations = (List<Formation>) responseEntity.getBody();
        assertThat(lFormations).hasSize(0);

    }

    @Test
    @Sql(scripts ="/init1.sql" ) // Initialisation de la db de test
    @Sql(scripts ="/close1.sql",executionPhase = AFTER_TEST_METHOD ) // Vidage de la db à la fin du test
    public void testObtenirFormation1() throws Exception {
/*
        ResponseEntity<FormationEntity> responseEntity =
                restTemplate.getForEntity("/api/formations/1",  FormationEntity.class);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getFormateur()).isEqualTo("CHM");
*/
    }

    @Test
    @Sql(scripts ="/init1.sql" ) // Initialisation de la db de test
    @Sql(scripts ="/close1.sql",executionPhase = AFTER_TEST_METHOD ) // Vidage de la db à la fin du test
    public void testObtenirFormationsNonVide1() throws Exception {
/*
        ResponseEntity<Object> responseEntity =
                restTemplate.getForEntity("/api/formations",  Object.class);
        List<Formation> lFormations = (List<Formation>) responseEntity.getBody();
        assertThat(lFormations).hasSize(1);
   */
    }

    @Test
    @Sql(scripts ="/init2.sql" ) // Initialisation de la db de test
    @Sql(scripts ="/close1.sql",executionPhase = AFTER_TEST_METHOD ) // Vidage de la db à la fin du test
    public void testObtenirFormationsNonVide2() throws Exception {
        ResponseEntity<Object> responseEntity =
                restTemplate.getForEntity("/api/formations",  Object.class);
        List<Formation> lFormations = (List<Formation>) responseEntity.getBody();
        assertThat(lFormations).hasSize(4);
    }

    @AfterClass
    public static void tearDownClass(){

    }

}
