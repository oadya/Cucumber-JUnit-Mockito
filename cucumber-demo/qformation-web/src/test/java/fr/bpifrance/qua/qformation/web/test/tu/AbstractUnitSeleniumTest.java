package fr.bpifrance.qua.qformation.web.test.tu;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Cette classe lié à l'énumeration Browser permet de découpler le paramétrage sélénium :
 * <ul>
 * 	<li>Si SELENIUM_HUB_URL est précisé, on utilise un selenium grid pour le browser précisé avec SELENIUM_BROWSER</li>
 *  <li>Sinon on instancie en local, un driver service avec les drivers récupérés par Maven et pour le browser précisé avec SELENIUM_BROWSER<li> 
 * <ul>
 * 
 */
public abstract class AbstractUnitSeleniumTest {

	private static final Logger logger = LoggerFactory.getLogger(AbstractUnitSeleniumTest.class);

	/**
	 * Le browser sur lequel on fait les tests précisé par une variable
	 * d'environnement 'BROWSER'.
	 */
	public static String SELENIUM_BROWSER = getSystemPropFallbackEnv("SELENIUM_BROWSER");

	/**
	 * Si on utilise Selenium grid, on founit l'url par une variable d'envionnement
	 * 'SELENIUM_HUB_URL' sinon un driver service sera instancié localement à
	 * l'éxécution des tests.
	 */
	public static String SELENIUM_HUB_URL = getSystemPropFallbackEnv("SELENIUM_HUB_URL");

	//seul présent par défaut sur l'usine logiciel (pour demo)
	public static Browser currentBrowser;

	/**
	 * Dans le cas d'un lancement local.
	 */
	public static DriverService localService;

	/**
	 * Le web driver local ou remote.
	 */
	public static WebDriver currentDriver;

	/**
	 * on recherche d'abord une propriété de JVM (-DXXX=...) sinon une variable d'environnement lié à l'OS
	 * @param name le nom de la propriété/variable recherchée.
	 * @return
	 */
	private static String getSystemPropFallbackEnv(String name) {
		String value = System.getProperty(name);
		if (isEmpty(value)) {
			value = System.getenv(name);
			if(!isEmpty(value)) {
				logger.info("Found property :" + name + " from OS ENV VAR");
			}
		} else {
			logger.info("Found property :" + name + " from JVM SYSTEM PROPERTIES");
		}
		if (isEmpty(value)) {
			logger.info("Cannot found property :" + name + " in SYSTEM PROPERTIES or OS ENV VAR");
		}
		return value;
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
		currentBrowser = Browser.valueOf("CHROME");

	}

	@AfterClass
	public static void createAndStopService() {
		if(isEmpty(SELENIUM_HUB_URL)) {
			logger.info("STOP LOCAL DRIVER SERVICE :" + currentBrowser.toString());
			localService.stop();
		}
	}
	
	@Before
	public void setUp() throws Exception {
		if (isEmpty(SELENIUM_HUB_URL)) {
			localService = currentBrowser.getDriverService();
			if (localService == null) {
				throw new RuntimeException(
						"Impossible d'obtenir un driver service pour le navigateur " + currentBrowser.toString());
			}
			logger.info("START LOCAL DRIVER SERVICE :"+currentBrowser.toString());
			localService.start();
			currentDriver = currentBrowser.getWebDriverFor(localService);
			logger.info("CREATE LOCAL WEB DRIVER for "+currentBrowser.toString()+" "+currentDriver.toString());
			if (currentDriver == null) {
				throw new RuntimeException(
						"Impossible d'obtenir un web driver pour le navigateur " + currentBrowser.toString());
			}
		} else {
			//On utilise le Bpi remote Web driver avec le hub selenium
			Capabilities cap= currentBrowser.getCapabilitiesBrowser();
			currentDriver = new Browser.BpiRemoteWebDriver(new URL(SELENIUM_HUB_URL),
					currentBrowser.getCapabilitiesBrowser());
			logger.info("CREATE REMOTE WEB DRIVER "+currentDriver.toString()+" WITH CAPABILITIES "+cap.toString());
		}
	}

	@After
	public void setDown() {
		logger.info("DELETE WEB DRIVER :" + currentDriver.toString());
		currentDriver.quit();
		currentDriver = null;
	}

	//Donne le driver courant instancité dans la classe du Hook à cause de sélénium.
	public static WebDriver getDriver() {
		return currentDriver;
	}

	public static boolean presenceOfElement(By by) {
		try {
			currentDriver.findElement(by);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public static boolean isEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}
}
