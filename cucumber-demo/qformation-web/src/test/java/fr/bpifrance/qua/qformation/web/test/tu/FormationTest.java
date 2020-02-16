package fr.bpifrance.qua.qformation.web.test.tu;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableFeignClients(basePackages = { "fr.bpifrance.qua.qformation.web.restclient" })
@TestPropertySource(locations = "classpath:application-it.properties")
public class FormationTest extends AbstractUnitSeleniumTest {
    @Test
    public void vide(){
        assertThat(true).isTrue();
    }

	@Autowired
	WebApplicationContext wac;
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8899);
	@LocalServerPort
	public int port;

	@Autowired
	private MockMvc mockMvc;

	private String getRemoteUrlOrLocalfallback()  {
		String hostname="localhost";
		try {
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			 hostname = addr.getHostName();}
		catch (UnknownHostException ex)
			{
				hostname="localhost";
			}
        return "http://" + hostname + ":" + Integer.toString(port);
	}

	@Test
	public void testObtenirFormationsVide() throws Exception {
		stubFor(get(urlEqualTo("/api/formations"))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody("[]")));
		
		getDriver().get(getRemoteUrlOrLocalfallback() + "/formations/liste");
		Thread.sleep(5000);
		assertThat(getDriver().findElement(By.xpath("//*[text()='Aucune formation']")).isDisplayed()).isTrue();

	}

	@Test
	public void testObtenirSallesAucune() throws Exception {
		stubFor(get(urlEqualTo("/api/salles"))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody("[]")));
		getDriver().get(getRemoteUrlOrLocalfallback() + "/salles");
		assertThat(getDriver().findElement(By.xpath("//td[text()='Aucune salle']")).isDisplayed()).isTrue();

	}

	@Test
	public void testObtenirSallesPlusieurs() throws Exception {
		stubFor(get(urlEqualTo("/api/salles")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBodyFile("salles.json")));
		getDriver().get(getRemoteUrlOrLocalfallback() + "/salles");
		WebElement table = getDriver().findElement(By.id("sallesTable"));
		String salle = table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(0).getText();
		String capacite = table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1).getText();
		assertThat(salle).isEqualTo("RDC1");
		assertThat(capacite).isEqualTo("10");
		assertThat(getDriver().findElement(By.xpath("//*[text()='RDC2']")).isDisplayed()).isTrue();
		assertThat(getDriver().findElement(By.xpath("//*[text()='7']")).isDisplayed()).isTrue();

	}

	@Test
	public void testAjouterSalle() throws Exception {
		stubFor(post(urlEqualTo("/api/salles")).withRequestBody(equalToJson("{\"salle\":\"RDC1\",\"capacite\":5}"))
				.willReturn(aResponse().withBody("[{\"salle\":\"RDC1\",\"capacite\":5}]").withStatus(200)
						.withHeader("Content-Type", "application/json")));

		stubFor(get(urlEqualTo("/api/salles"))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody("[]")));
		getDriver().get(getRemoteUrlOrLocalfallback() + "/salles");
		System.out.print(getDriver().getPageSource());
		getDriver().findElement(By.name("salle")).sendKeys("RDC1");
		getDriver().findElement(By.name("capacite")).sendKeys("5");
		getDriver().findElement(By.xpath("//input[@value='Ajouter']")).click();

		verify(postRequestedFor(urlEqualTo("/api/salles"))
				.withRequestBody(equalToJson("{\"salle\":\"RDC1\",\"capacite\":5}")));
		// assertThat(getDriver().findElement(By.xpath("//*[text()='Aucune
		// formation']")).isDisplayed()).isTrue();
	}

	@Test
	public void testObtenirFormationsNonVide1() throws Exception {
		stubFor(get(urlEqualTo("/api/formations")).willReturn(aResponse().withStatus(200)
				.withHeader("Content-Type", "application/json").withBodyFile("input2.json")));
		getDriver().get(getRemoteUrlOrLocalfallback() + "/formations/liste");
		WebElement table = getDriver().findElement(By.id("formationsTable"));
		String id = table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(0).getText();
		String code = table.findElements(By.tagName("tr")).get(1).findElements(By.tagName("td")).get(1).getText();
		assertThat(id).isEqualTo("1");
		assertThat(code).isEqualTo("CSLM");
		assertThat(getDriver().findElement(By.xpath("//*[text()='CSLM']")).isDisplayed()).isTrue();
		assertThat(getDriver().findElement(By.xpath("//*[text()='RDC1']")).isDisplayed()).isTrue();

	}

	@Test
	public void testFormationAvecSujetSansSalle() throws Exception {
		stubFor(get(urlEqualTo("/api/sujets")).willReturn(aResponse().withBody(
				"[{\"code\":\"SLNM\",\"libelle\":\"Selenium\",\"formateur\":\"MARGUERITE\",\"prerequis\":\"\"}]")
				.withStatus(200).withHeader("Content-Type", "application/json")));
		stubFor(get(urlEqualTo("/api/salles"))
				.willReturn(aResponse().withBody("[]").withStatus(200).withHeader("Content-Type", "application/json")));
		getDriver().get(getRemoteUrlOrLocalfallback() + "/formations/ajout");
		assertThat(getDriver().getPageSource()
				.contains("Il n'y a aucune salle de formations. Merci de contacter un administrateur!")).isTrue();
		System.out.println(getDriver().getPageSource());
	}

	@Test
	public void testFormationSansSujet() throws Exception {
		stubFor(get(urlEqualTo("/api/sujets"))
				.willReturn(aResponse().withBody("[]").withStatus(200).withHeader("Content-Type", "application/json")));
		getDriver().get(getRemoteUrlOrLocalfallback() + "/formations/ajout");
		System.out.println(getDriver().getPageSource());
		assertThat(getDriver().getPageSource()
				.contains("Il n'y a aucun sujet de formations. Merci de contacter un administrateur!")).isTrue();

	}

	@Test
	public void testFormationAvecSujetAvecSalle() throws Exception {
		SoftAssertions verify = new SoftAssertions();
		stubFor(get(urlEqualTo("/api/sujets")).willReturn(aResponse().withBody(
				"[{\"code\":\"SLNM\",\"libelle\":\"Selenium\",\"formateur\":\"MARGUERITE\",\"prerequis\":\"\"}]")
				.withStatus(200).withHeader("Content-Type", "application/json")));
		stubFor(get(urlEqualTo("/api/salles")).willReturn(
				aResponse().withBody("[{\"salle\":\"RDC1\",\"capacite\":8},{\"salle\":\"RDC2\",\"capacite\":5}]")
						.withStatus(200).withHeader("Content-Type", "application/json")));
		getDriver().get(getRemoteUrlOrLocalfallback() + "/formations/ajout");
		verify.assertThat(new Select(getDriver().findElement(By.id("code"))).getOptions().get(1).getText())
				.isEqualTo("SLNM-Selenium");
		verify.assertThat(new Select(getDriver().findElement(By.id("salle"))).getOptions().get(1).getText())
				.isEqualTo("RDC1");
		verify.assertThat(new Select(getDriver().findElement(By.id("salle"))).getOptions().get(2).getText())
				.isEqualTo("RDC2");

		verify.assertAll();
	}

	/*
	 * @Test public void testListe(){ List<String> l1 =
	 * Arrays.asList("Buenos Aires", "Cordoba", "La Plata","tttest","yeyeysyu");
	 * List<String> l2 = Arrays.asList("Buenos Aires", "Cordoba",
	 * "La Plata","test"); assertThat(l1).asList().hasSameElementsAs(l2).
	 * as("Test de la comparaison de liste");
	 * 
	 * }
	 */

}
