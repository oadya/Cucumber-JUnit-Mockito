package fr.bpifrance.qua.qformation.api.test.tu;

import fr.bpifrance.qua.qformation.api.resources.FormationResource;

import fr.bpifrance.qua.qformation.domaine.Formation;
import fr.bpifrance.qua.qformation.domaine.FormationMetier;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(FormationResource.class)// Classe de ressource REST à test
public class FormationTest {

    @MockBean
   private FormationMetier formationMetier;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
      //  MockitoAnnotations.initMocks(this);
     //   mockMvc = MockMvcBuilders.standaloneSetup(FormationResource.class).build();
    }
    @Test
    public void testObtenirFormationsVide() throws Exception {
        given(formationMetier.obtenirFormations()).willReturn(new ArrayList<>()); // On moque
        mockMvc.perform(get("/api/formations")).andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testObtenirFormationsNonVide() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Formation> lFormations=new ArrayList<Formation>();
        lFormations.add(new Formation("CSLM", "Cucumber",sdf.parse("01/07/2018"), "RDC1", "Claude-Henri", "planifié", true, 10, 0 ));
        lFormations.add(new Formation("CSLM", "Cucumber",sdf.parse("01/07/2018"), "RDC1", "Claude-Henri", "planifié", true, 10, 0 ));
        given(formationMetier.obtenirFormations()).willReturn(lFormations);
        mockMvc.perform(get("/api/formations")).andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)));
    }



    @AfterClass
    public static void tearDownClass(){

    }
}
