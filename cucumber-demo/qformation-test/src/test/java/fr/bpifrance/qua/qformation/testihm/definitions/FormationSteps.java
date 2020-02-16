package fr.bpifrance.qua.qformation.testihm.definitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.DataTable;
import cucumber.api.java.fr.Alors;
import cucumber.api.java.fr.Etantdonné;
import cucumber.api.java.fr.Quand;
import fr.bpifrance.qua.qformation.testihm.pageobjects.FormationPage;
import fr.bpifrance.qua.qformation.testihm.pageobjects.MenuPage;
import fr.bpifrance.qua.qformation.testihm.pageobjects.RessourcesPage;
import fr.bpifrance.qua.test.configuration.AbstractSeleniumTest;
import static fr.bpifrance.qua.test.configuration.AbstractSeleniumTest.getDriver;
import static fr.bpifrance.qua.test.configuration.AbstractSeleniumTest.cliquerSurLeLien;
import static fr.bpifrance.qua.test.configuration.AbstractSeleniumTest.saisirDansChamps;
import static fr.bpifrance.qua.test.configuration.AbstractSeleniumTest.cliquerSur;
import static fr.bpifrance.qua.test.configuration.AbstractSeleniumTest.choisirDansListe;

public class FormationSteps {

	String code;
	String libelle;
	String formateur;
	String statut;
	Properties prop;
	MenuPage ongletMenu;

	@Etantdonné("^je suis un administrateur$")
	public void je_suis_un_administrateur() throws IOException {
		getDriver().navigate().to(AbstractSeleniumTest.SELENIUM_FRONT_URL+"/formations");
	}

	@Etantdonné("^aucun sujet de formation ne soit disponible$")
	public void qu_un_sujet_de_formation_soit_disponible() throws Throwable {
		cliquerSurLeLien("Ressources");
		cliquerSurLeLien("Sujets");
		Assert.assertTrue(getDriver().findElement(By.xpath("//td[contains(.,'Aucun sujet')]")).isDisplayed());
	}

	@Etantdonné("^aucune salle ne soit enregistrée$")
	public void qu_aucune_salle_ne_soit_enregistrée() throws Throwable {
		cliquerSurLeLien("Ressources");
		cliquerSurLeLien("Salles");
		Assert.assertTrue(getDriver().findElement(By.xpath("//td[contains(.,'Aucune salle')]")).isDisplayed());
	}

	@Quand("^je veux planifier une formation$")
	public void je_veux_planifier_une_formation() throws Throwable {
		cliquerSurLeLien("Formations");
		cliquerSurLeLien("Planifier une formation");
	}

	@Alors("^l'opération ne se fait pas et le système me renvoie le message \"([^\"]*)\"$")
	public void l_opération_ne_se_fait_pas_et_le_système_me_renvoie_le_message(String message) throws Throwable {
		Assert.assertTrue(getDriver().findElement(By.cssSelector("div.alert.alert-danger")).getText().contains(message));
	}

	@Quand("^j'ajoute la salle \"([^\"]*)\" d'une capacité de (\\d+)$")
	public void j_ajoute_la_salle_d_une_capacité_de(String salle, int capacite) {
		cliquerSurLeLien("Ressources");
		cliquerSurLeLien("Salles");
		RessourcesPage ressourcesPage = PageFactory.initElements(getDriver(), RessourcesPage.class);
		saisirDansChamps(ressourcesPage.txtSalle, salle);
		saisirDansChamps(ressourcesPage.txtCapacite, String.valueOf(capacite));
		cliquerSur(ressourcesPage.btnAjouter);
	}

	@Alors("^la salle \"([^\"]*)\" est disponible avec sa capacité maximale de (\\d+)$")
	public void la_salle_est_disponible_avec_sa_capaicité_maximale_de(String salle, int capacite) throws Throwable {
		Assert.assertTrue(getDriver().findElement(By.xpath("//td[contains(.,'" + salle + "')]")).isDisplayed());
		Assert.assertTrue(
				getDriver().findElement(By.xpath("//td[contains(.,'" + String.valueOf(capacite) + "')]")).isDisplayed());
	}

	@Quand("^je planifie un sujet de formation$")
	public void je_planifie_un_sujet_de_formation(DataTable dt) throws Throwable {
		List<Map<String, String>> sujets = dt.asMaps(String.class, String.class);
		Map<String, String> sujet = sujets.get(0);
		cliquerSurLeLien("Ressources");
		cliquerSurLeLien("Sujets");
		RessourcesPage ressourcesPage = PageFactory.initElements(getDriver(), RessourcesPage.class);
		saisirDansChamps(ressourcesPage.txtCodeSujet, sujet.get("code"));
		saisirDansChamps(ressourcesPage.txtLibelleSujet, sujet.get("libellé"));
		saisirDansChamps(ressourcesPage.txtFormateurSujet, sujet.get("formateur"));
		saisirDansChamps(ressourcesPage.txtPrerequisSujet, sujet.get("prerequis"));
		cliquerSur(ressourcesPage.btnAjouter);
	}

	@Alors("^le sujet \"([^\"]*)\" est disponible dans la liste$")
	public void le_sujet_est_disponible_dans_la_liste(String code) throws Throwable {
		Assert.assertTrue(getDriver().findElement(By.xpath("//td[contains(.,'" + code + "')]")).isDisplayed());
	}

	@Quand("^je planifie une formation$")
	public void je_planifie_une_formation(DataTable dt) throws Throwable {
		List<Map<String, String>> formations = dt.asMaps(String.class, String.class);
		Map<String, String> formation = formations.get(0);
		cliquerSurLeLien("Formations");
		cliquerSurLeLien("Planifier une formation");
		FormationPage formationPage = PageFactory.initElements(getDriver(), FormationPage.class);
		choisirDansListe(formationPage.listeCodeFormation, formation.get("code"));
		saisirDansChamps(formationPage.txtDateFormation, formation.get("date"));
		choisirDansListe(formationPage.listeSalleFormation, formation.get("salle"));
		if (!formation.get("maximum").isEmpty()) {
			formationPage.txtMaximumStagiaires.clear();
			saisirDansChamps(formationPage.txtMaximumStagiaires, formation.get("maximum"));
		}
		cliquerSur(formationPage.btnAjouterFormation);
	}

	@Alors("^la formation (\\d+) est disponible pour inscription$")
	public void cette_formation_est_disponible_pour_inscription(int nLigne) throws Throwable {
		cliquerSurLeLien("Formations");
		FormationPage formationPage = PageFactory.initElements(getDriver(), FormationPage.class);
		cliquerSurLeLien("Liste des formations");
		WebElement ligne = formationPage.tableauFormations.findElements(By.tagName("tr")).get(nLigne);
		//A corriger plus tard
		//Assert.assertEquals("Disponible", ligne.findElements(By.tagName("td")).get(8).getText());
	}

	@Alors("^la formation (\\d+) est fermée pour inscription$")
	public void cette_formation_est_fermee_pour_inscription(int nLigne) throws Throwable {
		cliquerSurLeLien("Formations");
		FormationPage formationPage = PageFactory.initElements(getDriver(), FormationPage.class);
		cliquerSurLeLien("Liste des formations");
		WebElement ligne = formationPage.tableauFormations.findElements(By.tagName("tr")).get(nLigne);
		Assert.assertEquals("Complet", ligne.findElements(By.tagName("td")).get(8).getText());
	}

	@Alors("^les informations suivantes sont affichées$")
	public void les_informations_suivantes_sont_affichées(DataTable dt) throws Throwable {
		List<Map<String, String>> formations = dt.asMaps(String.class, String.class);
		Map<String, String> formation = formations.get(0);
		FormationPage formationPage = PageFactory.initElements(getDriver(), FormationPage.class);

		int nbLignes = formationPage.tableauFormations.findElements(By.tagName("tr")).size();
		WebElement ligne = formationPage.tableauFormations.findElements(By.tagName("tr")).get(nbLignes - 1);
		Assert.assertEquals(formation.get("nombreInscrits"), ligne.findElements(By.tagName("td")).get(5).getText());
		Assert.assertEquals(formation.get("maximumInscrits"), ligne.findElements(By.tagName("td")).get(6).getText());
		Assert.assertEquals("Nouvelle", ligne.findElements(By.tagName("td")).get(7).getText());
		//A corriger
		//Assert.assertEquals(formation.get("ouvert"), ligne.findElements(By.tagName("td")).get(8).getText());
	}

	@Etantdonné("^que la formation (\\d+) est disponible$")
	public void que_la_formation_est_disponible(int idFormation) throws Throwable {
		cliquerSurLeLien("Formations");
		cliquerSurLeLien("Liste des formations");
		FormationPage formationPage = PageFactory.initElements(getDriver(), FormationPage.class);
		WebElement ligne = formationPage.tableauFormations.findElements(By.tagName("tr")).get(1);
		Assert.assertTrue(ligne.findElements(By.tagName("td")).get(0).getText().contains(String.valueOf(idFormation)));

	}

	@Quand("^j'inscris le stagiaire \"([^\"]*)\":\"([^\"]*)\" à la formation (\\d+)$")
	public void j_inscris_le_stagiaire(String matricule, String nom, int idFormation) throws Throwable {
		getDriver().findElement(By.xpath("//a[@href='modification?id=" + idFormation + "']")).click();
		FormationPage formationPage = PageFactory.initElements(getDriver(), FormationPage.class);
		saisirDansChamps(formationPage.txtMatriculeStagiaire, matricule);
		saisirDansChamps(formationPage.txtNomStagiaire, nom);
		cliquerSur(formationPage.btnInscrireStagiaire);
	}

	@Alors("^la formation (\\d+) est disponible et le nombre d'inscrits est de (\\d+)$")
	public void la_formation_est_disponible_et_le_nombre_d_inscrit_est_de(int idFormation, int nombre)
			throws Throwable {
		cliquerSurLeLien("Formations");
		cliquerSurLeLien("Liste des formations");
		FormationPage formationPage = PageFactory.initElements(getDriver(), FormationPage.class);
		WebElement ligne = formationPage.tableauFormations.findElements(By.tagName("tr")).get(1);
		Assert.assertTrue(ligne.findElements(By.tagName("td")).get(0).getText().contains(String.valueOf(idFormation)));
		Assert.assertTrue(ligne.findElements(By.tagName("td")).get(5).getText().equals(String.valueOf(nombre)));
	}

}
